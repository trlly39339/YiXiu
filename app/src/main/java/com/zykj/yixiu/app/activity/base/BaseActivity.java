package com.zykj.yixiu.app.activity.base;

import android.app.Activity;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by zykj on 2017/4/8.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }



}
