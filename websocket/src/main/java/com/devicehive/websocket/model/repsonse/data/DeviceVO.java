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


package com.devicehive.websocket.model.repsonse.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * DeviceVO
 */
@Data
public class DeviceVO {
    @SerializedName("id")
    private String id = null;

    @SerializedName("name")
    private String name = null;

    @SerializedName("data")
    private Object data = null;

    @SerializedName("networkId")
    private Long networkId = null;

    @SerializedName("isBlocked")
    private Boolean isBlocked = false;

    @Override
    public String toString() {
        return "{\n\"DeviceVO\":{\n"
                + "\"id\":\"" + id + "\""
                + ",\n \"name\":\"" + name + "\""
                + ",\n \"data\":" + data
                + ",\n \"networkId\":\"" + networkId + "\""
                + ",\n \"isBlocked\":\"" + isBlocked + "\""
                + "}\n}";
    }
}

