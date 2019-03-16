package com.haokuo.rent.bean;

import lombok.Data;

/**
 * Created by zjf on 2019/2/22.
 */
@Data
public class HouseSearchBean {
    private Integer area;
    private String buildingUnit;
    private Integer entireRents;
    private Integer hall;
    private Integer id;
    private String images;
    private String toward;
    private TemplateBean template;

    @Data
    public static class TemplateBean {
        private Integer decorateType;
        private String feature;
        private String furniture;
    }
}
