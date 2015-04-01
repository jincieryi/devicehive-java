package com.devicehive.service;

import com.devicehive.dao.DeviceDAO;
import com.devicehive.messages.kafka.Notification;
import com.devicehive.model.Device;
import com.devicehive.model.DeviceNotification;
import com.devicehive.model.SpecialNotifications;
import com.devicehive.model.wrappers.DeviceNotificationWrapper;
import com.devicehive.util.LogExecutionTime;
import com.devicehive.util.ServerResponsesFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LogExecutionTime
public class DeviceNotificationService {

    @EJB
    private DeviceEquipmentService deviceEquipmentService;
    @EJB
    private TimestampService timestampService;
    @EJB
    private DeviceDAO deviceDAO;

    @Inject
    @Notification
    private Event<DeviceNotification> deviceNotificationMessageReceivedEvent;

    public void submitDeviceNotification(final DeviceNotification notification, final Device device) {
        List<DeviceNotification> proceedNotifications = processDeviceNotification(notification, device);
        for (DeviceNotification currentNotification : proceedNotifications) {
            deviceNotificationMessageReceivedEvent.fire(currentNotification);
        }
    }

    public void submitDeviceNotification(final DeviceNotification notification, final String deviceGuid) {
        notification.setTimestamp(timestampService.getTimestamp());
        notification.setId(notification.getTimestamp().getTime());
        notification.setDeviceGuid(deviceGuid);
        deviceNotificationMessageReceivedEvent.fire(notification);
    }

    public List<DeviceNotification> processDeviceNotification(DeviceNotification notificationMessage, Device device) {
        List<DeviceNotification> notificationsToCreate = new ArrayList<>();
        switch (notificationMessage.getNotification()) {
            case SpecialNotifications.EQUIPMENT:
                deviceEquipmentService.refreshDeviceEquipment(notificationMessage, device);
                break;
            case SpecialNotifications.DEVICE_STATUS:
                notificationsToCreate.add(refreshDeviceStatusCase(notificationMessage, device));
                break;
            default:
                break;

        }
        notificationMessage.setDeviceGuid(device.getGuid());
        notificationMessage.setTimestamp(timestampService.getTimestamp());
        notificationsToCreate.add(notificationMessage);
        return notificationsToCreate;

    }

    public DeviceNotification convertToMessage(DeviceNotificationWrapper notificationSubmit, Device device) {
        DeviceNotification message = new DeviceNotification();
        message.setId(System.nanoTime());
        message.setDeviceGuid(device.getGuid());
        message.setTimestamp(timestampService.getTimestamp());
        message.setNotification(notificationSubmit.getNotification());
        message.setParameters(notificationSubmit.getParameters());
        return message;
    }

    public DeviceNotification refreshDeviceStatusCase(DeviceNotification notificationMessage, Device device) {
        device = deviceDAO.findByUUIDWithNetworkAndDeviceClass(device.getGuid());
        String status = ServerResponsesFactory.parseNotificationStatus(notificationMessage);
        device.setStatus(status);
        return ServerResponsesFactory.createNotificationForDevice(device, SpecialNotifications.DEVICE_UPDATE);
    }
}
