package com.zykj.yixiu.app.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/8.
 */

public class MainActivity extends BaseActivity {
    @Bind(R.id.xianshi_chrngshi_tv)
    TextView xianshiChrngshiTv;
    @Bind(R.id.xuanze_chrngshi_ll)
    LinearLayout xuanzeChrngshiLl;
    @Bind(R.id.grzx_img)
    ImageView grzxImg;
    @Bind(R.id.banner_main)
    Banner bannerMain;
    @Bind(R.id.shoujiweixiu_ll)
    LinearLayout shoujiweixiuLl;
    @Bind(R.id.diannaoweixiu_ll)
    LinearLayout diannaoweixiuLl;
    @Bind(R.id.jiadianweixiu_ll)
    LinearLayout jiadianweixiuLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.xuanze_chrngshi_ll, R.id.grzx_img, R.id.shoujiweixiu_ll, R.id.diannaoweixiu_ll, R.id.jiadianweixiu_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xuanze_chrngshi_ll:
                break;
            case R.id.grzx_img:
                break;
            case R.id.shoujiweixiu_ll:
                Intent intent=new Intent(this,PhoneWeiXiuActivity.class);
                startActivity(intent);
                break;
            case R.id.diannaoweixiu_ll:
                Intent intent1=new Intent(this,DianNaoWeiXiu.class);
                startActivity(intent1);
                break;
            case R.id.jiadianweixiu_ll:
                Intent intent2=new Intent(this,JiaDianWeiXiuActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
