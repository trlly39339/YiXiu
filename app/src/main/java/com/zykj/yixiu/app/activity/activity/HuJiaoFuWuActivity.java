package com.zykj.yixiu.app.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.grzx_activity.BaiDudiZhiActivity;
import com.zykj.yixiu.app.activity.activity.grzx_activity.DiZhiGuanLiActivity;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.ChaXunAddress;
import com.zykj.yixiu.app.activity.bean.DianNaoBean;
import com.zykj.yixiu.app.activity.bean.JiaDianBean;
import com.zykj.yixiu.app.activity.bean.PhoneBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.http.RequestParams;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private ChaXunAddress dz;
    private String leiXing;
    private PhoneBean phoneBean;
    private DianNaoBean dianNaoBean;
    private JiaDianBean jiaDianBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_service);
        ButterKnife.bind(this);
//        左侧返回按键开始——————————————————————
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
//        左侧返回按键结束————————————————————————————————
        Intent intent1 = getIntent();
        if (intent1 != null){
            leiXing = intent1.getStringExtra("LeiXing");
            Y.i(leiXing+"类型");
        }
        if ("1".equals(leiXing)){
            phoneBean = (PhoneBean) intent1.getSerializableExtra("phoneBean");
        }
        if ("2".equals(leiXing)){
            dianNaoBean = (DianNaoBean) intent1.getSerializableExtra("dianNaoBean");
        }
        if ("3".equals(leiXing)){
            jiaDianBean = (JiaDianBean) intent1.getSerializableExtra("jiaDianBean");
        }

    }

    @OnClick({R.id.ll_shijian, R.id.ll_dizhi, R.id.qrfb_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shijian:
                TimePickerView   pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat simpleDateFormat  =new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
                        shijianTv.setText(simpleDateFormat.format(date));
                    }
                })
                        .build();
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();

                break;
            case R.id.ll_dizhi:
                Intent intent=new Intent(HuJiaoFuWuActivity.this,DiZhiGuanLiActivity.class);
                startActivityForResult(intent, 110);
                break;
            case R.id.qrfb_but:
                String shijian = shijianTv.getText().toString().trim();
                String dizhi = dizhiTv.getText().toString().trim();
//                order_type: 订单类型,1手机,2电脑,3家电
//                brand: 品牌
//                model:型号
//                fault:故障点
//                fault_desc:故障描述
//                category:类别 例如电脑(一体机,笔记本,台式机) 手机此参数为空
//                image1:图片一  必选 必须有一张图片
//                image2:图片二  可选
//                image3:图片一   可选
//                service_time:上门服务时间
//                service_address:服务地址
//                custom_phone:客户电话
//                custom_name:客户姓名
//                custom_id:客户ID
//                address_id:客户关联的地址ID

                if ("1".equals(leiXing)) {//手机
                    RequestParams params = new RequestParams(YURL.ADD_ORDER);
                    params.setMultipart(true);
                    params.addBodyParameter("order_type", leiXing);
                    params.addBodyParameter("brand",phoneBean.getTvPinpai());
                    params.addBodyParameter("model",phoneBean.getTvXinghao());
                    params.addBodyParameter("fault",phoneBean.getTvGuzhang());
                    params.addBodyParameter("fault_desc",phoneBean.getEvGuzhangMiaoshu());
                    params.addBodyParameter("category","");
                    params.addBodyParameter("image1", new File(phoneBean.getFile()));
                    params.addBodyParameter("service_time",shijian);
                    params.addBodyParameter("service_address",dizhi);
                    params.addBodyParameter("custom_phone", Y.USER.getPhone());
                    params.addBodyParameter("custom_name",Y.USER.getUsername());
                    params.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    params.addBodyParameter("address_id",dz.getAddress_id()+"");
                    Y.postFlie(params, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                                Y.t("下单成功");
                                Intent intent1=new Intent(HuJiaoFuWuActivity.this,MainActivity.class);
                                startActivity(intent1);

                            }else {
                                Y.t("下单失败");
                            }
                        }
                    });

                }
                if ("2".equals(leiXing)) {//电脑
                    RequestParams params = new RequestParams(YURL.ADD_ORDER);
                    params.setMultipart(true);
                    params.addBodyParameter("order_type", leiXing);
                    params.addBodyParameter("brand",dianNaoBean.getPinpai());
                    params.addBodyParameter("model",dianNaoBean.getXinghao());
                    params.addBodyParameter("fault",dianNaoBean.getGuzhang());
                    params.addBodyParameter("fault_desc",dianNaoBean.getGuzhangmiaoshu());
                    params.addBodyParameter("category",dianNaoBean.getLeixing());
                    params.addBodyParameter("image1", new File(dianNaoBean.getFileimg()));
                    params.addBodyParameter("service_time",shijian);
                    params.addBodyParameter("service_address",dizhi);
                    params.addBodyParameter("custom_phone", Y.USER.getPhone());
                    params.addBodyParameter("custom_name",Y.USER.getUsername());
                    params.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    params.addBodyParameter("address_id",dz.getAddress_id()+"");
                    Y.postFlie(params, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                                Y.t("下单成功");
                                Intent intent1=new Intent(HuJiaoFuWuActivity.this,MainActivity.class);
                                startActivity(intent1);

                            }else {
                                Y.t("下单失败");
                            }
                        }
                    });

                }
                if ("3".equals(leiXing)) {//家电
                    RequestParams params = new RequestParams(YURL.ADD_ORDER);
                    params.setMultipart(true);
                    params.addBodyParameter("order_type", leiXing);
                    params.addBodyParameter("brand",jiaDianBean.getPinpai());
                    params.addBodyParameter("model",jiaDianBean.getXinghao());
                    params.addBodyParameter("fault",jiaDianBean.getGuzhang());
                    params.addBodyParameter("fault_desc",jiaDianBean.getGuzhangmiaoshu());
                    params.addBodyParameter("category",jiaDianBean.getLeixing());
                    params.addBodyParameter("image1", new File(jiaDianBean.getFileimg()));
                    params.addBodyParameter("service_time",shijian);
                    params.addBodyParameter("service_address",dizhi);
                    params.addBodyParameter("custom_phone", Y.USER.getPhone());
                    params.addBodyParameter("custom_name",Y.USER.getUsername());
                    params.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    params.addBodyParameter("address_id",dz.getAddress_id()+"");
                    Y.postFlie(params, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                                Y.t("下单成功");
                                Intent intent1=new Intent(HuJiaoFuWuActivity.this,MainActivity.class);
                                startActivity(intent1);

                            }else {
                                Y.t("下单失败");
                            }
                        }
                    });

                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==110&&resultCode==110){
            dz = (ChaXunAddress) data.getSerializableExtra("address");
            dizhiTv.setText(dz.getAddress());
        }
    }
}
