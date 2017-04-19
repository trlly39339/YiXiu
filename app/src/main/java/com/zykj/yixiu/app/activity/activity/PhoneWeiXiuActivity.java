package com.zykj.yixiu.app.activity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.PhoneWeiXiuBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/16.
 */

public class PhoneWeiXiuActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.tv_pinpai)
    TextView tvPinpai;
    @Bind(R.id.ll_pinpai)
    LinearLayout llPinpai;
    @Bind(R.id.tv_xinghao)
    TextView tvXinghao;
    @Bind(R.id.ll_xinghao)
    LinearLayout llXinghao;
    @Bind(R.id.tv_guzhang)
    TextView tvGuzhang;
    @Bind(R.id.ll_guzhang)
    LinearLayout llGuzhang;
    @Bind(R.id.ev_guzhang_miaoshu)
    EditText evGuzhangMiaoshu;
    @Bind(R.id.fl_tianjiatupian)
    FrameLayout flTianjiatupian;
    @Bind(R.id.but_queren)
    Button butQueren;
    List<PhoneWeiXiuBean> lists; //品牌的数据源
    int mobileIndex = -1;  //用于检测是否选择了品牌
    @Bind(R.id.tv_pp)
    TextView tvPp;
    @Bind(R.id.tv_xh)
    TextView tvXh;
    @Bind(R.id.tv_gz)
    TextView tvGz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellphone_repairs);
        ButterKnife.bind(this);
        //        返回按钮
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_pinpai, R.id.ll_xinghao, R.id.ll_guzhang, R.id.fl_tianjiatupian, R.id.but_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_pinpai:
                //发起请求
                Y.get(YURL.FIND_PHONE_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), PhoneWeiXiuBean.class);
                            //创建选择器

                            OptionsPickerView opv = new OptionsPickerView.Builder(PhoneWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    tvPp.setVisibility(View.GONE);
                                    tvPinpai.setVisibility(View.VISIBLE);
                                    tvPinpai.setText(lists.get(options1).getName());
                                    if (mobileIndex !=options1){
                                        tvXh.setVisibility(View.VISIBLE);
                                        tvXinghao.setVisibility(View.GONE);
                                        mobileIndex = options1;// 当前选择的索引
                                    }
                                }
                            }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (PhoneWeiXiuBean mb : lists) {
                                strs.add(mb.getName());
                            }
                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器

                            opv.show();


                        } else {
                            //失败
                            Y.t("数据解析失败");

                        }
                    }
                });


                break;
            case R.id.ll_xinghao:
                //检测是否选择了品牌
                if (mobileIndex == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    HashMap<String, String> map = new HashMap<>();
                    map.put("pid",lists.get(mobileIndex).getId()+"");
                    Y.get(YURL.FIND_PHONE_MODEL,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), PhoneWeiXiuBean.class);
                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(PhoneWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        tvXh.setVisibility(View.GONE);
                                        tvXinghao.setVisibility(View.VISIBLE);
                                        tvXinghao.setText(lists.get(options1).getName());


                                    }
                                }).build();

                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (PhoneWeiXiuBean mb : lists) {
                                    strs.add(mb.getName());
                                }
                                //添加数据
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                opv.show();
                            } else {
                                //失败
                                Y.t("数据解析失败");
                            }
                        }
                    });
                }
                break;
            case R.id.ll_guzhang:
                //发起请求
                Y.get(YURL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), PhoneWeiXiuBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(PhoneWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    tvGz.setVisibility(View.GONE);
                                    tvGuzhang.setVisibility(View.VISIBLE);
                                    tvGuzhang.setText(lists.get(options1).getName());
                                }
                            }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (PhoneWeiXiuBean mb : lists) {
                                strs.add(mb.getName());
                            }
                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            opv.show();
                        } else {
                            //失败
                            Y.t("数据解析失败");
                        }
                    }
                });
                break;
            case R.id.fl_tianjiatupian:
                break;
            case R.id.but_queren:
                break;
        }
    }
}
