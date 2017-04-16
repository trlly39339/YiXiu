package com.zykj.yixiu.app.activity.app;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import org.xutils.x;

/**
 * Created by zykj on 2017/4/8.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        x.Ext.init(this);
        Y.Context=this;
    }



}
