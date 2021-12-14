package com.uwjx.geo.util;

import com.uwjx.geo.bean.LatLng;

import java.util.List;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/14 20:41
 */
public class GeoUtil {

    /**
     * 判断经纬度是否在范围类
     */
    public static boolean isInRegion(double longitudeCur, double latitudeCur, List<LatLng> pathList) {
        //点小于3无法构成多边形
        if (pathList.size() < 3) {
            return false;
        }
        int iSum = 0;
        int pathCount = pathList.size();
        double longStart = 0, latiStart = 0, longEnd = 0, latiEnd = 0;
        double dLong = 0;
        for (int i = 0; i < pathCount; i++) {
            int nextIndex = i + 1;
            if (i == pathCount - 1) {
                nextIndex = 0;
            }
            longStart = pathList.get(i).getLng();
            latiStart = pathList.get(i).getLat();
            longEnd = pathList.get(nextIndex).getLng();
            latiEnd = pathList.get(nextIndex).getLat();

            if ((latitudeCur >= latiStart && latitudeCur < latiEnd) ||
                    (latitudeCur >= latiEnd && latitudeCur < latiStart)) {
                if (Math.abs(latiStart - latiEnd) > 0) {
                    dLong = longStart - ((longStart - longEnd) * (latiStart - latitudeCur)) / (latiStart - latiEnd);
                    if (dLong < longitudeCur) {
                        iSum++;
                    }
                }
            }
        }

        if ((iSum % 2) != 0) {
            return true;
        }
        return false;
    }
}
