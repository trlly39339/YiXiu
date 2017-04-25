package com.zykj.yixiu.app.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.grzx_activity.BaiDudiZhiActivity;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class BianJiDiZhiActivity extends BaseActivity {


    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.xingming_et)
    EditText xingmingEt;
    @Bind(R.id.shojihao_tv)
    TextView shojihaoTv;
    @Bind(R.id.baidudizhi_et)
    TextView baidudizhiEt;
    @Bind(R.id.moren_sch)
    Switch morenSch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
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

    @OnClick(R.id.baidudizhi_et)
    public void onViewClicked() {
        startActivity(new Intent(BianJiDiZhiActivity.this,BaiDudiZhiActivity.class));
    }





}
