package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.denglu_zhuce.DengLuActivity;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class SheZhiActivity extends BaseActivity {
    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.ll_xgmm)
    LinearLayout llXgmm;
    @Bind(R.id.ll_xxtz)
    LinearLayout llXxtz;
    @Bind(R.id.tcdl_but)
    Button tcdlBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_shezhi);
        ButterKnife.bind(this);

        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_xgmm, R.id.ll_xxtz, R.id.tcdl_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_xgmm:
//                修改密码
                startActivity(new Intent(SheZhiActivity.this,XiuGaiMiMaActivity.class));
                break;
            case R.id.ll_xxtz:
//                消息通知
                startActivity(new Intent(SheZhiActivity.this,XiaoXiTongZhiActivity.class));
                break;
            case R.id.tcdl_but:
//               退出登录 //跳转到登录页面
                startActivity(new Intent(SheZhiActivity.this, DengLuActivity.class));
                break;
        }
    }
}
