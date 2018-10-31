package com.haokuo.rent.network;

/**
 * Created by zjf on 2018-07-21.
 */

public class UrlConfig {
    public static final String IMAGE_STRING_SPLIT = ",";
    public static final String BASE_URL = "http://115.231.122.38:8080/xfhsi/";

    public static final String IMAGE_BASE_URL = "http://115.231.122.38:8080/xfhs/upload/";

    public static String buildImageUrl(String url) {
        return IMAGE_BASE_URL + url;
    }

    public static String buildBaseImageUrl(String url) {
        return BASE_URL + url;
    }
}
