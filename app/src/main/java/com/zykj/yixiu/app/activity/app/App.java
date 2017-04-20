package com.zykj.yixiu.app.activity.app;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.hss01248.dialog.MyActyManager;
import com.hss01248.dialog.StyledDialog;
import com.umeng.analytics.MobclickAgent;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import org.xutils.x;

import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.widget.GFImageView;

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
        StyledDialog.init(this);

//    在activity生命周期callback中拿到顶层activity引用:
    registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}
        @Override
        public void onActivityStarted(Activity activity) {}
        @Override
        public void onActivityResumed(Activity activity) {
            //在这里保存顶层activity的引用(内部以软引用实现)
            MyActyManager.getInstance().setCurrentActivity(activity);}
        @Override
        public void onActivityPaused(Activity activity) {}
        @Override
        public void onActivityStopped(Activity activity) {}
        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
        @Override
        public void onActivityDestroyed(Activity activity) {}
    });
}
}