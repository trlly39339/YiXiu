package com.zykj.yixiu.app.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.grzx_activity.BaiDudiZhiActivity;
import com.zykj.yixiu.app.activity.activity.grzx_activity.GengGaiShouJiHaoActivity;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.ChaXunAddress;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import java.util.HashMap;
import java.util.Map;

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
    @Bind(R.id.fl_qr_but)
    FrameLayout flQrBut;
    private ChaXunAddress dz;

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
        if (!TextUtils.isEmpty(Y.USER.getPhone())) {
            shojihaoTv.setText(Y.USER.getPhone());
        }
//        上传姓名、电话、地址、是否默认
        flQrBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                name: 姓名
//                address: 地址
//                phone: 电话号码
//                user_id:用户ID
//                region:区
//                lat:纬度
//                lon:经度
//                city_name:城市名
//                city_code:城市编码
//                isdefault: 是否是默认 0 不默认  1 默认
                Map map=new HashMap();
                map.put("name",xingmingEt.getText().toString());
                map.put("phone",shojihaoTv.getText());
                map.put("address",dz.getAddress());
                map.put("user_id",Y.USER.getUser_id()+"");
                map.put("region",dz.getRegion());
                map.put("lat",dz.getLat());
                map.put("lon",dz.getLon());
                map.put("city_name",dz.getCity_name());
                map.put("city_code",dz.getCity_code());
                map.put("isdefault",1);
                Y.get(YURL.ADD_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        Y.t("上传成功");
                        Y.i(result);
                        finish();
                    }
                });
            }
        });
    }

    @OnClick({R.id.shojihao_tv, R.id.baidudizhi_et})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shojihao_tv:
//                跳转到更换手机号
                Intent intent = new Intent(BianJiDiZhiActivity.this, GengGaiShouJiHaoActivity.class);
                startActivityForResult(intent, 100);

                break;
            case R.id.baidudizhi_et:
//                跳转到百度地址
                Intent intent1 = new Intent(BianJiDiZhiActivity.this, BaiDudiZhiActivity.class);
                startActivityForResult(intent1, 101);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        接受传来的数据 设置到手机号控件上
        if (requestCode == 100 && resultCode == 100) {
            String phone = data.getStringExtra("phone");
            shojihaoTv.setText(phone);
        }
        if (requestCode == 101 && resultCode == 101) {

            dz = (ChaXunAddress) data.getSerializableExtra("address");
            baidudizhiEt.setText(dz.getAddress());
        }

    }
//

}
