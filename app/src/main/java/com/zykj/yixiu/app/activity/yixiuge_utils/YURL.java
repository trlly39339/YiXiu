package com.zykj.yixiu.app.activity.yixiuge_utils;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/16.
 */

public class YURL {
    // 服务器地址
    public static final String BASE_HOST="http://221.207.184.124:7071/";
    //项目名称
    public static final String HOST=BASE_HOST+"YiXiu/";

    //查找手机品牌
    public static final String FIND_PHONE_BRAND=HOST+"findPhoneBrand";
    //根据品牌查找型号
    public static final String FIND_PHONE_MODEL=FIND_PHONE_BRAND+"findPhoneModel";
    //查询手机故障
    public static final String FIND_PHONE_FAULT=FIND_PHONE_MODEL+"findPhoneFault";
}
