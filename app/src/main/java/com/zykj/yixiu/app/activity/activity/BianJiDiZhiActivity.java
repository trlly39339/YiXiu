package com.zykj.yixiu.app.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.grzx_activity.BaiDudiZhiActivity;
import com.zykj.yixiu.app.activity.activity.grzx_activity.GengGaiShouJiHaoActivity;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

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
        if (!TextUtils.isEmpty(Y.USER.getPhone())){
            shojihaoTv.setText(Y.USER.getPhone());
        }
    }

    @OnClick({R.id.shojihao_tv, R.id.baidudizhi_et})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shojihao_tv:
//                跳转到更换手机号
                Intent intent = new Intent(BianJiDiZhiActivity.this, GengGaiShouJiHaoActivity.class);
               startActivityForResult(intent,100);

                break;
            case R.id.baidudizhi_et:
//                跳转到百度地址
                Intent intent1 = new Intent(BianJiDiZhiActivity.this, BaiDudiZhiActivity.class);
                startActivityForResult(intent1,101);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        接受传来的数据 设置到手机号控件上
        if (requestCode==100&&resultCode==100){
            String phone = data.getStringExtra("phone");
            shojihaoTv.setText(phone);
        }
        if (requestCode==101&&resultCode==101){
            String phone = data.getStringExtra("phone");
            baidudizhiEt.setText(phone);
        }

    }
//

}
