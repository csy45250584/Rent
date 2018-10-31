package com.haokuo.rent.network.params.base;

import com.haokuo.rent.util.MySpUtil;

import lombok.Data;

/**
 * Created by zjf on 2018-08-15.
 */
@Data
public class UserIdTokenParams {
    private long userId;
    private String token;

    //    public UserIdTokenParams(long userId, String token) {
    //        this.userId = userId;
    //        this.token = token;
    //    }

    public UserIdTokenParams() {
        this.userId = MySpUtil.getInstance().getUserId();
        this.token = MySpUtil.getInstance().getToken();
    }
}
