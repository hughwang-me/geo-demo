package com.uwjx.geo.controller;

import com.uwjx.geo.bean.GeoData;
import com.uwjx.geo.bean.RequestData;
import com.uwjx.geo.service.GeoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/14 20:14
 */
@RestController
@Slf4j
@RequestMapping(value = "geo")
public class GeoController {

    @Autowired
    GeoService geoService;

    @PostMapping(value = "calculate")
    public List<GeoData> calculate(@RequestBody RequestData requestData){
        log.warn("请求参数:{}" , requestData.toString());
        return geoService.calculate(requestData);
    }
}
