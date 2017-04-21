package com.zykj.yixiu.app.activity.activity.denglu_zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.ZhuCeBase;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class WangJiMiMaActivity extends BaseActivity {


    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_yzm)
    EditText etYzm;
    @Bind(R.id.but_hqyzm)
    FrameLayout butHqyzm;
    @Bind(R.id.but_xyb)
    FrameLayout butXyb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu_wangjimima);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.but_xyb)
    public void onViewClicked() {
        String phone = etPhone.getText().toString().trim();
        String yzm = etYzm.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            Y.t("请输入手机号");
            return;
        }
        if (!Y.isMobileNO(phone)){
            Y.t("请输入合法的手机号");
        }

        if (TextUtils.isEmpty(yzm)){
            Y.t("请输入验证码");
            return;
        }
        if (yzm.length()!=4){
            Y.t("您输入的验证码不合法");
        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("phone",phone);
        map.put("vcode",yzm);
        map.put("type","0");
        Y.get(YURL.REGISTER, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    ZhuCeBase zhuce = JSON.parseObject(Y.getData(result), ZhuCeBase.class);
                    Y.t("注册成功"+zhuce);
                    startActivity(new Intent(WangJiMiMaActivity.this,DengLuMiMaActivity.class).putExtra("data",Y.getData(result)));
                }else {
                    Y.t("注册失败");
                }
            }
        });

    }
}
