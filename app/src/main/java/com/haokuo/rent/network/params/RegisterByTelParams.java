package com.haokuo.rent.network.params;

import com.haokuo.rent.network.params.base.TelPhoneParams;

import lombok.Data;

/**
 * Created by zjf on 2018/11/5.
 */
@Data
public class RegisterByTelParams extends TelPhoneParams {
    private String code;

    public RegisterByTelParams(String phone, String code) {
        super(phone);
        this.code = code;
    }
}
