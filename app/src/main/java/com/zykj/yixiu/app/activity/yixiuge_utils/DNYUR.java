package com.zykj.yixiu.app.activity.yixiuge_utils;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/16.
 */

public class DNYUR {
    // 服务器地址http://221.207.184.124:7071/yxg/findComputerBrand
    public static final String BASE_HOST="http://221.207.184.124:7071/";
    //项目名称
    public static final String HOST=BASE_HOST+"yxg/";
    //查找电脑品牌
    public static final String FIND_COMPUTER_BRAND=HOST+"findComputerBrand";
    //根据品牌查找类型
    public static final String FIND_COMPUTER_CATEGORY=HOST+"findComputerCategory";
    //根据品牌查找型号
    public static final String FINDBY_COMPUTER_MODEL=HOST+"findByComputerModel";
    //查询手机故障
    public static final String FIND_PHONE_FAULT=HOST+"findPhoneFault";
}
