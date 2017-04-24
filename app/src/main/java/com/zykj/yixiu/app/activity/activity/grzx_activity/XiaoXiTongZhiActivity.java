package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.os.Bundle;
import android.widget.Switch;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/24.
 */

public class XiaoXiTongZhiActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.switch1_kg)
    Switch switch1Kg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_xxtz);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
        if (!switch1Kg.isChecked()){
            Y.t("将不再接收任何消息通知");
        }
    }
}
