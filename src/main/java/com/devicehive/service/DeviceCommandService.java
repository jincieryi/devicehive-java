package com.devicehive.service;

import com.devicehive.dao.DeviceCommandDAO;
import com.devicehive.exceptions.HiveException;
import com.devicehive.messages.bus.GlobalMessageBus;
import com.devicehive.messages.handler.WebsocketHandlerCreator;
import com.devicehive.messages.subscriptions.CommandUpdateSubscription;
import com.devicehive.messages.subscriptions.SubscriptionManager;
import com.devicehive.model.Device;
import com.devicehive.model.DeviceCommand;
import com.devicehive.model.User;
import com.devicehive.model.updates.DeviceCommandUpdate;
import com.devicehive.websockets.util.AsyncMessageSupplier;
import com.devicehive.websockets.util.WebsocketSession;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.constraints.NotNull;
import javax.websocket.Session;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author: Nikolay Loboda
 * @since 25.07.13
 */
@Stateless
public class DeviceCommandService {
    @EJB
    private DeviceCommandDAO commandDAO;

    @EJB
    private DeviceCommandService self;

    @EJB
    private GlobalMessageBus globalMessageBus;

    @EJB
    private AsyncMessageSupplier asyncMessageDeliverer;

    @EJB
    private SubscriptionManager subscriptionManager;

    public DeviceCommand getWithDevice(@NotNull long id) {
        return commandDAO.getWithDevice(id);
    }

    public DeviceCommand getWithDeviceAndUser(@NotNull long id) {
        return commandDAO.getWithDeviceAndUser(id);
    }

    public DeviceCommand getByGuidAndId(@NotNull UUID guid, @NotNull long id) {
        return commandDAO.getByDeviceGuidAndId(guid, id);
    }

    public DeviceCommand findById(Long id) {
        return commandDAO.findById(id);
    }

    public List<DeviceCommand> getNewerThan(UUID deviceId, Timestamp timestamp){
        return commandDAO.getNewerThan(deviceId, timestamp);
    }

    public List<DeviceCommand> queryDeviceCommand(Device device, Timestamp start, Timestamp end, String command,
                                                  String status, String sortField, Boolean sortOrderAsc,
                                                  Integer take, Integer skip) {
        return commandDAO.queryDeviceCommand(device, start, end, command, status, sortField, sortOrderAsc, take, skip);
    }

    public DeviceCommand getByDeviceGuidAndId(@NotNull UUID guid, @NotNull long id) {
        return commandDAO.getByDeviceGuidAndId(guid, id);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void submitDeviceCommandUpdate(DeviceCommandUpdate update, Device device) {
        DeviceCommand saved = self.saveDeviceCommandUpdate(update, device);
        globalMessageBus.publishDeviceCommandUpdate(saved);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void submitDeviceCommand(DeviceCommand command, Device device, User user, final Session session) {
        self.saveDeviceCommand(command, device, user, session);
        globalMessageBus.publishDeviceCommand(command);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveDeviceCommand(DeviceCommand command, Device device, User user, final Session session) {
        command.setDevice(device);
        command.setUser(user);
        commandDAO.createCommand(command);
        if (session != null) {
            CommandUpdateSubscription commandUpdateSubscription =
                    new CommandUpdateSubscription(command.getId(), session.getId(),
                            new WebsocketHandlerCreator(session, WebsocketSession.COMMAND_UPDATES_SUBSCRIPTION_LOCK,
                                    asyncMessageDeliverer));
            subscriptionManager.getCommandUpdateSubscriptionStorage().insert(commandUpdateSubscription);
        }
    }



    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DeviceCommand saveDeviceCommandUpdate(DeviceCommandUpdate update, Device device) {

        DeviceCommand cmd = commandDAO.findById(update.getId());

        if (cmd == null) {
            throw new HiveException("Command not found!", NOT_FOUND.getStatusCode());
        }

        if (!cmd.getDevice().getId().equals(device.getId())) {
            throw new HiveException("Device tries to update incorrect command");
        }


        if (update.getCommand() != null) {
            cmd.setCommand(update.getCommand().getValue());
        }
        if (update.getFlags() != null) {
            cmd.setFlags(update.getFlags().getValue());
        }
        if (update.getLifetime() != null) {
            cmd.setLifetime(update.getLifetime().getValue());
        }
        if (update.getParameters() != null) {
            cmd.setParameters(update.getParameters().getValue());
        }
        if (update.getResult() != null) {
            cmd.setResult(update.getResult().getValue());
        }
        if (update.getStatus() != null) {
            cmd.setStatus(update.getStatus().getValue());
        }
        if (update.getTimestamp() != null) {
            cmd.setTimestamp(update.getTimestamp().getValue());
        }
        return cmd;
    }

}
