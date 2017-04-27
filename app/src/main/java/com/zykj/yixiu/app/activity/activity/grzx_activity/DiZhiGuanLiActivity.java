package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.BianJiDiZhiActivity;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/25.
 */

public class DiZhiGuanLiActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.rlv)
    RecyclerView rlv;
    @Bind(R.id.xzdz_ll)
    LinearLayout xzdzLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_dlgl);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }
    @OnClick(R.id.xzdz_ll)
    public void onViewClicked() {
        startActivity(new Intent(DiZhiGuanLiActivity.this,BianJiDiZhiActivity.class));
    }
}
