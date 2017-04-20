package com.zykj.yixiu.app.activity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.DianNaoFenLeiBean;
import com.zykj.yixiu.app.activity.bean.PhoneWeiXiuBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.DNYUR;
import com.zykj.yixiu.app.activity.yixiuge_utils.JIADIANL;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class JiaDianWeiXiuActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.pinpai_xianshi)
    TextView pinpaiXianshi;
    @Bind(R.id.ll_pinpai)
    LinearLayout llPinpai;
    @Bind(R.id.ll_leixing)
    LinearLayout llLeixing;
    @Bind(R.id.xinghao_xianshi)
    TextView xinghaoXianshi;
    @Bind(R.id.ll_xinghao)
    LinearLayout llXinghao;
    @Bind(R.id.guzhang_xianshi)
    TextView guzhangXianshi;
    @Bind(R.id.ll_guzhang)
    LinearLayout llGuzhang;
    @Bind(R.id.ev_guzhang_xiangqing)
    EditText evGuzhangXiangqing;
    @Bind(R.id.fl_tianjiatupian)
    FrameLayout flTianjiatupian;
    @Bind(R.id.but_queren)
    Button butQueren;
    @Bind(R.id.pinpai_x)
    TextView pinpaiX;
    @Bind(R.id.leixing_xianshi)
    TextView leixingXianshi;
    @Bind(R.id.leixing_x)
    TextView leixingX;
    @Bind(R.id.xinghao_x)
    TextView xinghaoX;
    @Bind(R.id.guzhang_x)
    TextView guzhangX;
    private List<DianNaoFenLeiBean> lists;//品牌数据源
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_repair);
        ButterKnife.bind(this);
        //        返回按钮
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_pinpai, R.id.ll_leixing, R.id.ll_xinghao, R.id.ll_guzhang, R.id.fl_tianjiatupian, R.id.but_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_pinpai:
                Y.get(JIADIANL.FIND_BY_APPLIANCE_BRAND, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), DianNaoFenLeiBean.class);
                            OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(JiaDianWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    pinpaiXianshi.setVisibility(View.GONE);
                                    pinpaiX.setVisibility(View.VISIBLE);
                                    pinpaiX.setText(lists.get(options1).getName());
                                    if (index != options1) {
                                        leixingXianshi.setVisibility(View.VISIBLE);//类型提示
                                        leixingX.setVisibility(View.GONE);//类型显示
                                        xinghaoXianshi.setVisibility(View.VISIBLE);//型号提示
                                        xinghaoX.setVisibility(View.GONE);//型号显示
                                        index=options1;
                                    }

                                }
                            }).build();
                            List<String> list = new ArrayList<String>();
                            for (DianNaoFenLeiBean bm : lists) {
                                list.add(bm.getName());

                            }
//                        添加数据
                            optionsPickerView.setPicker(list, null, null);
//                        显示
                            optionsPickerView.show();
                        } else {
                            Y.t("数据解析失败");
                        }
                    }
                });

                break;
            case R.id.ll_leixing:
                //检测是否选择了品牌
                if (index == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取类型数据
                    //发起请求
                    HashMap<String, String> map = new HashMap<>();
                    map.put("pid",lists.get(index).getId()+"");
                    Y.get(JIADIANL.FIND_APPLIANCE_CATEGORY,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), DianNaoFenLeiBean.class);
                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(JiaDianWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        leixingXianshi.setVisibility(View.GONE);
                                        leixingX.setVisibility(View.VISIBLE);
                                        leixingX.setText(lists.get(options1).getName());
//                                        if (index!=1){
//                                            xinghaoXianshi.setVisibility(View.VISIBLE);//型号提示
//                                            xinghaoX.setVisibility(View.GONE);//型号显示
//                                            index=options1;
//                                        }
                                    }
                                }).build();
                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (DianNaoFenLeiBean mb : lists) {
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
            case R.id.ll_xinghao:
                if (index==-1){
                    Y.t("请先选择品牌");
                }else {
                    //                发起型号请求
                    HashMap<String, String> map = new HashMap<>();
                    map.put("category","1");
                    map.put("pid",lists.get(index).getId()+"");
                    Y.get(JIADIANL.FIND_BY_APPLIANCE_MODEL, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
//                        成功刷新进度条fl
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                lists = JSON.parseArray(Y.getData(result), DianNaoFenLeiBean.class);
//                            创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(JiaDianWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                        xinghaoX.setVisibility(View.VISIBLE);//显示品牌
                                        xinghaoXianshi.setVisibility(View.GONE);//提示信息
                                        xinghaoX.setText(lists.get(options1).getName());//把解析出来的数据设置到控件里
                                        index = options1;//当前的索引

                                    }
                                }).build();
//                            把list进行转换
                                List<String> strings = new ArrayList<String>();
                                for (DianNaoFenLeiBean mb : lists) {
                                    strings.add(mb.getName());
                                }
//                            添加数据
                                opv.setPicker(strings, null, null);
//                            显示选择器
                                opv.show();
                            } else {
//                            失败
                                Y.t("数据解析失败");
                            }
                        }
                    });
                }
                break;
            case R.id.ll_guzhang:
                //发起故障请求
                Y.get(JIADIANL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), DianNaoFenLeiBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(JiaDianWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    guzhangXianshi.setVisibility(View.GONE);
                                    guzhangX.setVisibility(View.VISIBLE);
                                    guzhangX.setText(lists.get(options1).getName());
                                }
                            }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (DianNaoFenLeiBean mb : lists) {
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
