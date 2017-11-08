/*
 *
 *
 *   NetworkApiTest.java
 *
 *   Copyright (C) 2017 DataArt
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.github.devicehive.rest;

import com.github.devicehive.rest.api.NetworkApi;
import com.github.devicehive.rest.model.Network;
import com.github.devicehive.rest.model.NetworkId;
import com.github.devicehive.rest.model.NetworkUpdate;
import com.github.devicehive.rest.model.NetworkVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class NetworkApiTest extends Helper {
    private static final String NETWORK_NAME = "TEZT N3TW0K W1Z UN1Q N4M3";
    private static final String UPDATED_NETWORK_NAME = "UPDATED TEZT N3TW0K W1Z UN1Q N4M3";

    private void authorise() throws IOException {
        boolean authenticated = authenticate();
        Assert.assertTrue(authenticated);
    }

    @Before
    public void preTest() throws IOException {
        authorise();
        cleanUpNetworks(NETWORK_NAME);
    }

    @Test
    public void insertNetwork() throws IOException {
        NetworkApi networkApi = client.createService(NetworkApi.class);

        NetworkUpdate networkUpdate = new NetworkUpdate();
        networkUpdate.setName(NETWORK_NAME);

        Response<NetworkId> insertResponse = networkApi.insert(networkUpdate).execute();
        Assert.assertTrue(insertResponse.isSuccessful());
        Long networkId = insertResponse.body().getId();
        Assert.assertNotNull(networkId);

        Assert.assertTrue(deleteNetworks(networkId));
    }

    @Test
    public void getNetwork() throws IOException {
        NetworkApi networkApi = client.createService(NetworkApi.class);

        NetworkUpdate networkUpdate = new NetworkUpdate();
        networkUpdate.setName(NETWORK_NAME);

        Response<NetworkId> insertResponse = networkApi.insert(networkUpdate).execute();
        Assert.assertTrue(insertResponse.isSuccessful());
        Long networkId = insertResponse.body().getId();
        Assert.assertNotNull(networkId);

        Response<NetworkVO> getResponse = networkApi.get(networkId).execute();
        Assert.assertTrue(getResponse.isSuccessful());
        Assert.assertEquals(networkId, getResponse.body().getId());

        Assert.assertTrue(deleteNetworks(networkId));
    }

    @Test
    public void deleteNetwork() throws IOException {
        NetworkApi networkApi = client.createService(NetworkApi.class);

        NetworkUpdate networkUpdate = new NetworkUpdate();
        networkUpdate.setName(NETWORK_NAME);

        Response<NetworkId> insertResponse = networkApi.insert(networkUpdate).execute();
        Assert.assertTrue(insertResponse.isSuccessful());
        Long networkId = insertResponse.body().getId();
        Assert.assertNotNull(networkId);

        Response<Void> deleteResponse = networkApi.delete(networkId).execute();
        Assert.assertTrue(deleteResponse.isSuccessful());
    }

    @Test
    public void updateNetwork() throws IOException {
        NetworkApi networkApi = client.createService(NetworkApi.class);
        NetworkUpdate networkUpdate = new NetworkUpdate();
        networkUpdate.setName(NETWORK_NAME);

        Response<NetworkId> insertResponse = networkApi.insert(networkUpdate).execute();
        Assert.assertTrue(insertResponse.isSuccessful());
        Long networkId = insertResponse.body().getId();
        Assert.assertNotNull(networkId);

        NetworkUpdate networkUpdate1 = new NetworkUpdate();
        networkUpdate1.setName(UPDATED_NETWORK_NAME);

        Response<Void> updateResponse = networkApi.update(networkId, networkUpdate1).execute();
        Assert.assertTrue(updateResponse.isSuccessful());

        Response<NetworkVO> getResponse = networkApi.get(networkId).execute();
        Assert.assertTrue(getResponse.isSuccessful());
        Assert.assertNotNull(getResponse.body().getName());
        Assert.assertEquals(UPDATED_NETWORK_NAME, getResponse.body().getName());

        Assert.assertTrue(deleteNetworks(networkId));
    }

    @Test
    public void listNetwork() throws IOException {
        NetworkApi networkApi = client.createService(NetworkApi.class);

        int networkAmount = 5;
        Long[] networkIds = new Long[networkAmount];
        for (int j = 0; j < networkAmount; ++j) {
            NetworkUpdate networkUpdate = new NetworkUpdate();
            String name = String.format("%s %d", NETWORK_NAME, j);
            networkUpdate.setName(name);

            Response<NetworkId> insertResponse = networkApi.insert(networkUpdate).execute();
            Assert.assertTrue(insertResponse.isSuccessful());

            Long networkId = insertResponse.body().getId();
            Assert.assertNotNull(networkId);
            networkIds[j] = networkId;
        }

        Response<List<Network>> listResponse = networkApi.list(null, NETWORK_NAME + "%",
                null, null, 2 * networkAmount, 0).execute();
        Assert.assertTrue(listResponse.isSuccessful());
        Assert.assertEquals(networkAmount, listResponse.body().size());

        Assert.assertTrue(deleteNetworks(networkIds));
    }

}
