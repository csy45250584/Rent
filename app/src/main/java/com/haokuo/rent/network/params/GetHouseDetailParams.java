package com.haokuo.rent.network.params;

import com.haokuo.rent.network.params.base.IdParams;

/**
 * Created by zjf on 2019/2/22.
 */
public class GetHouseDetailParams extends IdParams {
    private Integer rentType;

    public GetHouseDetailParams(Long id, Integer rentType) {
        super(id);
        this.rentType = rentType;
    }
}
