package com.haokuo.rent.network.params.base;

import lombok.Data;

/**
 * Created by zjf on 2018/9/7.
 */
@Data
public class IdParams extends UserIdTokenParams {
    private long id;

    public IdParams(long id) {
        super();
        this.id = id;
    }
}
