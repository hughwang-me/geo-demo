package com.uwjx.geo.bean;

import lombok.Data;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/14 19:35
 */
@Data
public class GeoData {

    private String type;
    private String id;
    private Geometry geometry;
    private String geometry_name;
    private Properties properties;
}
