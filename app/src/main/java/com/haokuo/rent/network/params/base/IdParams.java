package com.haokuo.rent.network.params.base;

import lombok.Data;

/**
 * Created by zjf on 2018/9/7.
 */
@Data
public class IdParams extends UserIdTokenParams {
    private Long id;

    public IdParams(Long id) {
        super();
        this.id = id;
    }
}
