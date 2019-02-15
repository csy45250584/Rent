package com.haokuo.rent.network;

/**
 * Created by zjf on 2018-07-21.
 */

public class UrlConfig {
    public static final String IMAGE_STRING_SPLIT = ",";
    public static final String BASE_URL = "http://192.168.1.89:8080/LLGY/IF/";

    public static final String IMAGE_BASE_URL = "http://115.231.122.38:8080/xfhs/upload/";

    //用户
    public static final String GET_REGISTER_CODE_URL = "/user/sendSmsByregistered.do";
    public static final String REGISETER_BY_TEL_URL = "/user/registeredByPhone.do";
    public static final String LOGIN_BY_TEL_URL = "/user/loginForValidationCode.do";
    public static final String LOGIN_BY_PASSWORD_URL = "/user/loginForPwd.do";
    public static final String GET_USER_INFO_URL = "/user/getUserInfo.do";
    public static final String UPDATE_PASSWORD_URL = "/user/updateUserPwd.do";
    public static final String UPDATE_USER_INFO_URL = "/user/updateUser.do";
    public static final String GET_LOGIN_CODE_URL = "/user/sendSmsByLogin.do";
    //用户下拉数据
    public static final String GET_FEATURE_LIST_URL = "/selectData/getFeatureListIF.do";
    public static final String GET_DIST_LIST_URL = "/selectData/getDistListIF.do";
    public static final String GET_FURNITURE_LIST_URL = "/selectData/getFurnitureListIF.do";
    public static final String GET_DIST_JSON_LIST_URL = "/selectData/getDistJsonIF.do";

    public static String buildImageUrl(String url) {
        return IMAGE_BASE_URL + url;
    }

    public static String buildBaseImageUrl(String url) {
        return BASE_URL + url;
    }
}
