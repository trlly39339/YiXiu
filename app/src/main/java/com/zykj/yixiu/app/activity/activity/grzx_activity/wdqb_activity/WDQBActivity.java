package com.zykj.yixiu.app.activity.activity.grzx_activity.wdqb_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/23.
 */

public class WDQBActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.tv_shengyu)
    TextView tvShengyu;
    @Bind(R.id.tv_mx)
    TextView tvMx;
    @Bind(R.id.but_chongzhi)
    Button butChongzhi;
    @Bind(R.id.but_tixian)
    Button butTixian;
    @Bind(R.id.weixin_wbd_tv)
    TextView weixinWbdTv;
    @Bind(R.id.ll_weixin)
    LinearLayout llWeixin;
    @Bind(R.id.zhifubao_wbd)
    TextView zhifubaoWbd;
    @Bind(R.id.ll_zhifubao)
    LinearLayout llZhifubao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdqb);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_mx, R.id.but_chongzhi, R.id.but_tixian, R.id.ll_weixin, R.id.ll_zhifubao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mx:
                break;
            case R.id.but_chongzhi:
                break;
            case R.id.but_tixian:
                break;
            case R.id.ll_weixin:
                break;
            case R.id.ll_zhifubao:
                startActivity(new Intent(WDQBActivity.this,ZhiFuBaoActivity.class));
                break;
        }
    }
}
