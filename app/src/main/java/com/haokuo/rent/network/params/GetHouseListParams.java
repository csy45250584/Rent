package com.haokuo.rent.network.params;

import com.haokuo.rent.network.params.base.PageParams;

import lombok.Data;

/**
 * Created by zjf on 2019/2/22.
 */
@Data
public class GetHouseListParams extends PageParams {
    private Integer decorateType; //装修类型 ,
    private Integer rentType; //出租类型 (必传)  1:整租 2:合租
    private Integer room;//几室
    private Integer minRent;//最小租金
    private Integer maxRent;//最大租金
    private Integer toward;//朝向
    private Integer feature;//特色, 如: 1,2...
    private Integer furniture;//家具 , 如: 1,2..
    private Integer sortWay;//排序方式 ,分别为1,2,3,4
    private String keyword;//关键词搜索

}
