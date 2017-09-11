package com.devicehive.websocket.model.request;

import com.devicehive.websocket.model.request.data.UserUpdate;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import static com.devicehive.websocket.model.ActionConstant.USER_UPDATE_CURRENT;

@Data
public class UserUpdateCurrentAction extends RequestAction {

    @SerializedName("user")
    private UserUpdate user;

    public UserUpdateCurrentAction() {
        super(USER_UPDATE_CURRENT);
    }
}
