package com.haokuo.rent.network.params.base;

import lombok.Data;

/**
 * Created by zjf on 2018/9/7.
 */
@Data
public class TelPhoneParams {
    private String telphone;

    public TelPhoneParams(String telphone) {
        this.telphone = telphone;
    }
}
