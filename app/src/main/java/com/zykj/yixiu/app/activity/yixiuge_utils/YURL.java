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
    //http://221.207.184.124:7071/yxg/robOrder?order_id=327&engineer_id=110
    //http://221.207.184.124:7071/yxg/finishOrder?order_id=339&engineer_id=110
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
//    查询地址
    public static final String SELECT_ADDRESS=HOST+"selectAddress";
//    添加地址
    public static final String ADD_ADDRESS=HOST+"addaddress";
//    发布订单
    public static final String ADD_ORDER=HOST+"addOrder";
//    根据状态查找订单
    public static final String FIND_ORDER_BYS_TATE=HOST+"findOrderByState";
//    设置默认地址
    public static final String DEF_ADDRESS=HOST+"defAddress";
//    取消订单
    public static final String CANCEL_ORDER=HOST+"cancelOrder";
//    删除订单
    public static final String DEL_ORDER=HOST+"delOrder";
//

}
