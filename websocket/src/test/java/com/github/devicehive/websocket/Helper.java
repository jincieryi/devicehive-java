package com.github.devicehive.websocket;

import com.github.devicehive.rest.model.Device;
import com.github.devicehive.rest.model.DeviceUpdate;
import com.github.devicehive.rest.model.NetworkUpdate;
import com.github.devicehive.websocket.api.*;
import com.github.devicehive.websocket.listener.ConfigurationListener;
import com.github.devicehive.websocket.listener.DeviceListener;
import com.github.devicehive.websocket.listener.NetworkListener;
import com.github.devicehive.websocket.listener.TokenListener;
import com.github.devicehive.websocket.model.repsonse.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

class Helper {
    private static final String URL = "ws://playground.dev.devicehive.com/api/websocket";
    private String accessToken = "***REMOVED***";
    private static final String REFRESH_TOKEN = "***REMOVED***";
    int awaitTimeout = 30;
    TimeUnit awaitTimeUnit = TimeUnit.SECONDS;

    WebSocketClient client = new WebSocketClient
            .Builder()
            .url(URL)
            .build();
    private TokenWS tokenWS = client.createTokenWS();
    private DeviceWS deviceWS = client.createDeviceWS();
    private NetworkWS networkWS = client.createNetworkWS();

    void authenticate() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        client.setListener(
                new AuthListener() {
                    @Override
                    public void onSuccess(ResponseAction responseAction) {
                        latch.countDown();
                    }

                    @Override
                    public void onError(ErrorResponse error) {
                        refresh(latch);
                    }
                }
        );

        if (accessToken != null && !accessToken.isEmpty()) {
            client.authenticate(accessToken);
        } else {
            refresh(latch);
        }
        latch.await(1, TimeUnit.SECONDS);
    }

    private void refresh(final CountDownLatch latch) {
        tokenWS.setListener(new TokenListener() {
            @Override
            public void onGet(TokenGetResponse response) {

            }

            @Override
            public void onCreate(TokenGetResponse response) {

            }

            @Override
            public void onRefresh(TokenRefreshResponse response) {
                latch.countDown();
                accessToken = response.getAccessToken();
                client.authenticate(accessToken);
            }

            @Override
            public void onError(ErrorResponse error) {
                latch.countDown();
            }
        });
        tokenWS.refresh(null, REFRESH_TOKEN);
    }

    boolean deleteConfigurations(String name) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        final Counter counter = new Counter();
        final ConfigurationWS configurationWS = client.createConfigurationWS();
        configurationWS.setListener(new ConfigurationListener() {
            @Override
            public void onGet(ConfigurationGetResponse response) {

            }

            @Override
            public void onPut(ConfigurationInsertResponse response) {

            }

            @Override
            public void onDelete(ResponseAction response) {
                if (response.getStatus().equals(ResponseAction.SUCCESS)) {
                    counter.increment();
                }
                latch.countDown();
            }

            @Override
            public void onError(ErrorResponse error) {

            }
        });

        configurationWS.delete(null, name);
        latch.await(awaitTimeout, awaitTimeUnit);
        return counter.getCount() == 1;
    }

    void registerDevice(String deviceId, DeviceWS deviceWS) {
        DeviceUpdate deviceUpdate = new DeviceUpdate();
        deviceUpdate.setName(deviceId);
        deviceWS.save(null, deviceId, deviceUpdate);
    }

    boolean registerDevice(String deviceId) throws InterruptedException {
        authenticate();
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        DeviceUpdate deviceUpdate = new DeviceUpdate();
        deviceUpdate.setName(deviceId);
        deviceWS.save(null, deviceId, deviceUpdate);
        deviceWS.setListener(new DeviceListener() {
            @Override
            public void onList(List<Device> response) {

            }

            @Override
            public void onGet(Device response) {

            }

            @Override
            public void onDelete(ResponseAction response) {

            }

            @Override
            public void onSave(ResponseAction response) {
                atomicBoolean.set(true);
                latch.countDown();
            }

            @Override
            public void onError(ErrorResponse error) {

            }
        });
        latch.await(awaitTimeout, TimeUnit.SECONDS);
        deviceWS.setListener(null);
        return atomicBoolean.get();
    }

    void deleteDevice(String id) {
        deviceWS.delete(null, id);
    }


    long registerNetwork(String networkName) throws InterruptedException {
        authenticate();
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicLong atomicLong = new AtomicLong(-1);
        NetworkUpdate networkUpdate = new NetworkUpdate();
        networkUpdate.setName(networkName);
        networkWS.insert(null, networkUpdate);
        networkWS.setListener(new NetworkListener() {
            @Override
            public void onList(NetworkListResponse response) {

            }

            @Override
            public void onGet(NetworkGetResponse response) {

            }

            @Override
            public void onInsert(NetworkInsertResponse response) {
                atomicLong.set(response.getNetwork().getId());
                latch.countDown();
            }

            @Override
            public void onDelete(ResponseAction response) {

            }

            @Override
            public void onUpdate(ResponseAction response) {

            }

            @Override
            public void onError(ErrorResponse error) {
                latch.countDown();
            }
        });
        latch.await(awaitTimeout, TimeUnit.SECONDS);
        networkWS.setListener(null);
        return atomicLong.get();
    }

    void deleteNetwork(long id) {
        networkWS.delete(null, id);
    }

}
