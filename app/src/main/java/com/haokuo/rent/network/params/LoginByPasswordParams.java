package com.haokuo.rent.network.params;

import com.haokuo.rent.network.params.base.TelPhoneParams;

import lombok.Data;

/**
 * Created by zjf on 2018/11/5.
 */
@Data
public class LoginByPasswordParams extends TelPhoneParams {
    private String pwd;

    public LoginByPasswordParams(String phone, String pwd) {
        super(phone);
        this.pwd = pwd;
    }
}
