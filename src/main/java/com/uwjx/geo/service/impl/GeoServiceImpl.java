package com.uwjx.geo.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.uwjx.geo.bean.GeoData;
import com.uwjx.geo.bean.LatLng;
import com.uwjx.geo.bean.RequestData;
import com.uwjx.geo.bean.ResultData;
import com.uwjx.geo.service.GeoService;
import com.uwjx.geo.util.GeoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/14 19:10
 */
@Service
@Slf4j
public class GeoServiceImpl implements GeoService {

    private List<GeoData> geoDataList = new ArrayList<>();

    @PostConstruct
    public void run() {
        log.warn("开始处理");
        try {
            String filePath = "/Users/wanghuan/Documents/hugh_workspace/geo-demo/data/gisData.json";
            FileReader fileReader = new FileReader(filePath);
            JsonReader jsonReader = new JsonReader(fileReader);

            Gson gson = new Gson();
            Type type = new TypeToken<ResultData>(){}.getType();

            ResultData resultData = gson.fromJson(jsonReader,   type);
            log.warn("code : {}" , resultData.getCode());
            log.warn("msg : {}" , resultData.getMsg());
            log.warn("data size : {}" , resultData.getData().size());

            geoDataList.addAll(resultData.getData());

            log.warn("geoDataList size : {}" , geoDataList.size());
            jsonReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GeoData> calculate(RequestData requestData) {
        List<GeoData> geoDataResult = new ArrayList<>();
        log.warn("样本个数:{}" , geoDataList.size());

        LatLng latLngFrom = requestData.getLatLngFrom();
        LatLng latLngTo = requestData.getLatLngTo();

        List<LatLng> latLngList  = new ArrayList<>();
        latLngList.add(latLngFrom);
        latLngList.add(new LatLng(latLngFrom.getLat() , latLngTo.getLng()));
        latLngList.add(latLngTo);
        latLngList.add(new LatLng(latLngTo.getLat() , latLngFrom.getLng()));

        for (GeoData geoData : geoDataList) {
            if(GeoUtil.isInRegion(geoData.getProperties().getLon() , geoData.getProperties().getLat() , latLngList)){
                geoDataResult.add(geoData);
                log.warn("在范围内");
            }
        }

        return geoDataResult;
    }
}
