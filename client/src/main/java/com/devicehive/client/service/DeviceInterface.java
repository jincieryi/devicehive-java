package com.devicehive.client.service;

import com.devicehive.client.model.*;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;

/**
 * |-- save()  save current state to server
 * |-- getCommands(start_timestamp, end_timestamp, max_ number)
 * |     returns list of DeviceCommand objects
 * |-- getNotifications(start_timestamp, end_timestamp, max_ number)
 * |     returns list of DeviceNotification objects
 * |-- sendCommand(command, parameters, result_callback) returns DeviceCommand object
 * |-- sendNotification(name, parameters)
 * |-- subscribeCommands(callback, commandFilter), callback_proto(deviceCommand)
 * |-- subscribeNotifications(callback, nameFilter), callback_proto(deviceNotification)
 * |-- unsubscribeCommands(commandFilter)
 * -- unsubscribeNotifications(nameFilter)
 */
interface DeviceInterface {

    void save() throws IOException;

    List<DeviceCommand> getCommands(DateTime startTimestamp, DateTime endTimestamp, int maxNumber) throws IOException;

    List<DeviceNotification> getNotifications(DateTime startTimestamp, DateTime endTimestamp) throws IOException;

    DHResponse<DeviceCommand> sendCommand(String command, List<Parameter> parameters) throws IOException;

    DHResponse<DeviceNotification> sendNotification(String notification, List<Parameter> parameters) throws IOException;

    //TODO Find callback that is needed

    void subscribeCommands(CommandFilter commandFilter, DeviceCommandsCallback commandsCallback);

    void subscribeNotifications(NotificationFilter notificationFilter, DeviceNotificationsCallback notificationCallback);

    void unsubscribeCommands(CommandFilter commandFilter);

    void unsubscribeNotifications(NotificationFilter notificationFilter);
}
