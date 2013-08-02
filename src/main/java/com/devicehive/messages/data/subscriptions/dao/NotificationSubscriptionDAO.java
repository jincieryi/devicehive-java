package com.devicehive.messages.data.subscriptions.dao;

import java.util.Collection;
import java.util.List;

import com.devicehive.model.Device;

public interface NotificationSubscriptionDAO {

    public void insertSubscriptions(Collection<Long> deviceIds, String sessionId);

    public void insertSubscriptions(Long deviceId, String sessionId);
    
    public void insertSubscriptions(String sessionId);

    public void deleteBySession(String sessionId);

    public void deleteByDeviceAndSession(Long deviceId, String sessionId);

    public void deleteByDeviceAndSession(Device device, String sessionId);

    public void deleteByDevice(Long deviceId);

    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<String> getSessionIdSubscribedForAll();

    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<String> getSessionIdSubscribedByDevice(Long deviceId);

}