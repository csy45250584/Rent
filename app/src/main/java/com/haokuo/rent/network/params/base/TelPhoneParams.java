package com.haokuo.rent.network.params.base;

import lombok.Data;

/**
 * Created by zjf on 2018/9/7.
 */
@Data
public class TelPhoneParams {
    private String phone;

    public TelPhoneParams(String phone) {
        this.phone = phone;
    }
}
