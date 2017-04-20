package com.zykj.yixiu.app.activity.activity;

import android.os.Bundle;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/4/18.
 */

public class BaiDuDiZhiActivity extends BaseActivity {


    @Bind(R.id.biaoti)
    MyTopBer biaoti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_management);
        ButterKnife.bind(this);
//        左侧返回按键开始——————————————————————
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
//        左侧返回按键结束————————————————————————————————

    }


}
