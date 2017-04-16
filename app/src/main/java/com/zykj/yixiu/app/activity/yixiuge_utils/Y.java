package com.zykj.yixiu.app.activity.yixiuge_utils;

import android.content.Context;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

/**
 * 工具类
 * Created by Administrator on 2017/4/16.
 */

public class Y {
    public static Context Context;//全局上下文
    public static boolean isLog=true;//控制打印日志的开关

    /**
     * 吐司功能 只需要传入一个 字符串
     * @param str
     */
    public static void t(String str){Toast.makeText(Context, str, Toast.LENGTH_SHORT).show();}

    /**
     * 输出log日志
     * @param str
     */
    public static void i(String str){if (isLog) Logger.i(str); }
}
