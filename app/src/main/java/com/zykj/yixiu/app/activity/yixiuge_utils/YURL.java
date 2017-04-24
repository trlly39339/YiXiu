package com.zykj.yixiu.app.activity.yixiuge_utils;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/16.
 */

public class YURL {
    // 服务器地址
    public static final String BASE_HOST="http://221.207.184.124:7071/";
    //项目名称
    public static final String HOST=BASE_HOST+"yxg/";

    //查找手机品牌
    public static final String FIND_PHONE_BRAND=HOST+"findPhoneBrand";
    //根据品牌查找型号
    public static final String FIND_PHONE_MODEL=HOST+"findPhoneModel";
    //查询手机故障
    public static final String FIND_PHONE_FAULT=HOST+"findPhoneFault";
//   注册对接
    public static final String REGISTER=HOST+"register";
//    密码对接
    public static final String SETPASSWORD=HOST+"setpassword";
//    登录对接
    public static final String LOGIN=HOST+"login";
//    上传头像接口
    public static final String UP_LOAD_ICON=HOST+"uploadIcon";
//    用户信息
    public static final String SET_USER_INFO=HOST+"setUserInfo";
//    上传身份证
    public static final String UPLOAD_ID_CARD=HOST+"uploadIdCard";
//    绑定支付宝
    public static final String BIND_ALI_PAY=HOST+"bindAliPay";
}
