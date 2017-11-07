package com.carapp.utils;

import android.content.ActivityNotFoundException;
import android.content.Intent;

import com.corelibrary.application.AppContext;
import com.corelibrary.utils.DialogUtils;
import com.corelibrary.utils.OSUtils;

import java.net.URISyntaxException;

/**
 * Created by Administrator on 2017/11/6.
 */

public class NavUtils {

    private static final String GAODEAPP = "com.autonavi.minimap";
    private static final String BAIDUAPP = "com.baidu.BaiduMap";




    /**
     * 确定起终点坐标BY高德
     */
    public static void setUpGaodeAppByLoca(String endAddr){
        Intent intent = new Intent("android.intent.action.VIEW",
                android.net.Uri
                        .parse("androidamap://navi?sourceApplication=haoshun&sname=我的位置"
                                + "&dname=" + endAddr
                                + "&dev=0"));
        intent.setPackage(GAODEAPP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            AppContext.get().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            DialogUtils.showToastMessage("未安装高德地图");
        }
    }

    /**
     * 通过起终点名字使用百度地图
     */
    public static void setUpBaiduAPPByName(String endAddr){
        try {
            Intent intent = Intent.getIntent("intent://map/direction?origin=我的位置&destination=" + endAddr + "&mode=driving&src=haoshun#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            AppContext.get().startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            DialogUtils.showToastMessage("未安装baidu地图");
        }
    }
}
