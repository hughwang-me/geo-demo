package com.uwjx.geo.bean;

import lombok.Data;

import java.util.List;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/14 20:10
 */
@Data
public class ResultData {
    private int code;
    private String msg;
    private List<GeoData> data;
}
