package com.zykj.yixiu.app.activity.activity;

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
 * Created by zykj on 2017/4/18.
 */

public class HuJiaoFuWuActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.shijian_tv)
    TextView shijianTv;
    @Bind(R.id.ll_shijian)
    LinearLayout llShijian;
    @Bind(R.id.dizhi_tv)
    TextView dizhiTv;
    @Bind(R.id.ll_dizhi)
    LinearLayout llDizhi;
    @Bind(R.id.qrfb_but)
    Button qrfbBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_shijian, R.id.ll_dizhi, R.id.qrfb_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shijian:
                break;
            case R.id.ll_dizhi:
                break;
            case R.id.qrfb_but:
                break;
        }
    }
}
