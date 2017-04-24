package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class PingTaiFuWuActivity extends BaseActivity {


    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.yixiuzixun_shuliang_tv)
    TextView yixiuzixunShuliangTv;
    @Bind(R.id.yixiuzixun_shuliang_gon_fl)
    FrameLayout yixiuzixunShuliangGonFl;
    @Bind(R.id.ll_yixiuzixun)
    LinearLayout llYixiuzixun;
    @Bind(R.id.changjianwenti_ll)
    LinearLayout changjianwentiLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_fwpt);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_yixiuzixun, R.id.changjianwenti_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_yixiuzixun:
//                一修哥资讯
                startActivity(new Intent(PingTaiFuWuActivity.this,YiXiuGeZiXunActivity.class));
                break;
            case R.id.changjianwenti_ll:
//                常见问题
                startActivity(new Intent(PingTaiFuWuActivity.this,ChangJianWenTiActivity.class));
                break;
        }
    }
}
