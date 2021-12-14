package com.uwjx.geo.service;

import com.uwjx.geo.bean.GeoData;
import com.uwjx.geo.bean.RequestData;

import java.util.List;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/14 19:10
 */
public interface GeoService {

    List<GeoData> calculate(RequestData requestData);
}
