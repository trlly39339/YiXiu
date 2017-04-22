package com.zykj.yixiu.app.activity.activity.denglu_zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.User;
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

public class DengLuMiMaActivity extends BaseActivity {


    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.et_mm)
    EditText etMm;
    @Bind(R.id.et_zcmm)
    EditText etZcmm;
    @Bind(R.id.but_queren)
    FrameLayout butQueren;
    private String data;
    private String mm;
    private String zcmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu_mima);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent!=null){
            data = intent.getStringExtra("data");
        }
    }

    @OnClick(R.id.but_queren)
    public void onViewClicked() {
        mm = etMm.getText().toString().trim();
        zcmm = etZcmm.getText().toString().trim();
        if (TextUtils.isEmpty(mm)) {
            Y.t("密码不能为空");
            return;
        }
        //判断再次输入的密码是否为空
        if (TextUtils.isEmpty(zcmm)) {
            Y.t("请再次输入密码");
            return;
        }
        //判断俩次输入的密码是否一样
        if (!mm.equals(zcmm)) {
            Y.t("两次输入的密码不一致");
            return;
        }
        Map<String,String> map=new HashMap<String, String>();
        map.put("password", mm);
        map.put("token",data);
        Y.get(YURL.SETPASSWORD, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    Y.t("密码设置成功");
                    startActivity(new Intent(DengLuMiMaActivity.this,DengLuActivity.class).putExtra("data",data));
                }else {
                    Y.t("密码设置失败");
                }
            }
        });

    }
}