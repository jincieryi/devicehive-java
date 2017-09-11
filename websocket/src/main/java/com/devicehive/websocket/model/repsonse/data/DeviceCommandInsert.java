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
import org.joda.time.DateTime;

/**
 * DeviceCommand
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-07-20T15:03:42.016+03:00")
@Data
public class DeviceCommandInsert implements Comparable<DeviceCommandInsert> {
    @SerializedName("id")
    private Long id = null;

    @SerializedName("timestamp")
    private DateTime timestamp = null;

    @SerializedName("userId")
    private Long userId = null;

    @Override
    public String toString() {
        return "{\n\"DeviceCommand\":{\n"
                + "\"id\":\"" + id + "\""
                + ",\n \"timestamp\":" + timestamp
                + ",\n \"userId\":\"" + userId + "\""
                + "}\n}";
    }

    @Override
    public int compareTo(DeviceCommandInsert deviceCommand) {
        return getTimestamp().compareTo(deviceCommand.getTimestamp());
    }

}

