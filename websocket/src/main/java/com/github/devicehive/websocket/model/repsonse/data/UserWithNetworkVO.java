/*
 *
 *
 *   UserWithNetworkVO.java
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

/*
 * Device Hive REST API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 3.3.0-SNAPSHOT
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.github.devicehive.websocket.model.repsonse.data;

import com.github.devicehive.rest.model.JsonStringWrapper;
import com.github.devicehive.rest.model.NetworkVO;
import com.github.devicehive.rest.model.RoleEnum;
import com.github.devicehive.rest.model.StatusEnum;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

/**
 * UserWithNetworkVO
 */

public class UserWithNetworkVO {
    @SerializedName("id")
    private Long id = null;

    @SerializedName("login")
    private String login = null;

    @SerializedName("role")
    private RoleEnum role = null;

    @SerializedName("status")
    private StatusEnum status = null;

    @SerializedName("lastLogin")
    private DateTime lastLogin = null;

    @SerializedName("data")
    private JsonStringWrapper data = null;

    @SerializedName("introReviewed")
    private Boolean introReviewed = false;

    @SerializedName("networks")
    private List<NetworkVO> networks = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public JsonStringWrapper getData() {
        return data;
    }

    public void setData(JsonStringWrapper data) {
        this.data = data;
    }

    public Boolean getIntroReviewed() {
        return introReviewed;
    }

    public void setIntroReviewed(Boolean introReviewed) {
        this.introReviewed = introReviewed;
    }

    public List<NetworkVO> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworkVO> networks) {
        this.networks = networks;
    }
}

