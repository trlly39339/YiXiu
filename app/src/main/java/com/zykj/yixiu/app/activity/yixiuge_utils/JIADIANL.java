package com.zykj.yixiu.app.activity.yixiuge_utils;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/16.
 */

public class JIADIANL {
    // 服务器地址http://221.207.184.124:7071/yxg/findComputerBrand
    public static final String BASE_HOST="http://221.207.184.124:7071/";
    //项目名称
    public static final String HOST=BASE_HOST+"yxg/";
    //查找家电品牌
    public static final String FIND_BY_APPLIANCE_BRAND=HOST+"findByApplianceBrand";
    //根查找类型
    public static final String FIND_APPLIANCE_CATEGORY=HOST+"findApplianceCategory";
    //查找型号
    public static final String FIND_BY_APPLIANCE_MODEL=HOST+"findByApplianceModel";
    //查询手机故障
    public static final String FIND_PHONE_FAULT=HOST+"findPhoneFault";
}
