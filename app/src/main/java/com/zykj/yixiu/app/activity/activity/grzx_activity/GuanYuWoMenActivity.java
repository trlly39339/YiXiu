package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class GuanYuWoMenActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.tv_bbgx_zxb)
    TextView tvBbgxZxb;
    @Bind(R.id.ll_banbgx)
    LinearLayout llBanbgx;
    @Bind(R.id.ll_gongnjs)
    LinearLayout llGongnjs;
    @Bind(R.id.ll_bzyfk)
    LinearLayout llBzyfk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_gywm);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_banbgx, R.id.ll_gongnjs, R.id.ll_bzyfk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_banbgx:
                Y.t("已经是最新版本");
                break;
            case R.id.ll_gongnjs:
                Y.t("暂无介绍");
                break;
            case R.id.ll_bzyfk:
                Y.t("暂未开通");
                break;
        }
    }
}
