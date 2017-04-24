package com.zykj.yixiu.app.activity.activity.grzx_activity.wdqb_activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.PhoneWeiXiuActivity;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.PhoneWeiXiuBean;
import com.zykj.yixiu.app.activity.bean.User;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/23.
 */

public class ZhiFuBaoActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.zhifubaozhanghao_et)
    EditText zhifubaozhanghaoEt;
    @Bind(R.id.zhifubaoname_et)
    EditText zhifubaonameEt;
    @Bind(R.id.but_queren)
    FrameLayout butQueren;

    private OptionsPickerView opv;
    private String xingming;
    private String zhanghao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdqb_zfb);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
        if (!TextUtils.isEmpty(Y.USER.getAli_account())){
            zhifubaozhanghaoEt.setText(Y.USER.getAli_account());
        }
        if (!TextUtils.isEmpty(Y.USER.getAli_name())){
            zhifubaonameEt.setText(Y.USER.getAli_name());
        }
    }

    @OnClick(R.id.but_queren)
    public void onViewClicked() {
        xingming = zhifubaonameEt.getText().toString().trim();
        zhanghao = zhifubaozhanghaoEt.getText().toString().trim();
        //发起请求
        Map<String, String> map = new HashMap<>();
        map.put("ali_account", zhanghao);
        map.put("ali_name", xingming);
        map.put("user_id",Y.USER.getUser_id()+"");
        Y.get(YURL.BIND_ALI_PAY,map,   new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)) {
                    //成功
                       Y.t("上传成功"+Y.USER.getAli_account());
                } else {
                    //失败
                    Y.t("数据解析失败");
                }
            }
        });
    }
}
