package com.uwjx.geo.bean;

import lombok.Data;

import java.util.List;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/14 19:35
 */
@Data
public class Geometry {

    private String type;
    private List<Double> coordinates;
}
