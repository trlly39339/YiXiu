package com.zykj.yixiu.app.activity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.DianNaoFenLeiBean;
import com.zykj.yixiu.app.activity.bean.DianNaoWeiXiuBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.DNYUR;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class DianNaoWeiXiu extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.tv_pinpai_xianshi)
    TextView tvPinpaiXianshi;
    @Bind(R.id.ll_pinpai)
    LinearLayout llPinpai;
    @Bind(R.id.tv_leixing_xianshi)
    TextView tvLeixingXianshi;
    @Bind(R.id.ll_leixing)
    LinearLayout llLeixing;
    @Bind(R.id.tv_xinghao_xianshi)
    TextView tvXinghaoXianshi;
    @Bind(R.id.ll_xinghao)
    LinearLayout llXinghao;
    @Bind(R.id.tv_guzhang_xianshi)
    TextView tvGuzhangXianshi;
    @Bind(R.id.ll_guzhang)
    LinearLayout llGuzhang;
    @Bind(R.id.ev_guzhang_xiangqing)
    EditText evGuzhangXiangqing;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.fl_tianjiatupian)
    FrameLayout flTianjiatupian;
    @Bind(R.id.but_queren)
    Button butQueren;
    @Bind(R.id.tv_pp)
    TextView tvPp;
    @Bind(R.id.tv_lx)
    TextView tvLx;
    @Bind(R.id.tv_xh)
    TextView tvXh;
    @Bind(R.id.tv_gz)
    TextView tvGz;
    List<DianNaoWeiXiuBean> listdn;//品牌的数据源
    int index=-1;//用于检测是否选择了品牌
    OptionsPickerView opv;

    List<DianNaoWeiXiuBean> listdnlb;//类别的数据源
    int indexlb=-1;//用于检测是否选择了类别

    List<DianNaoWeiXiuBean> listdnxh;//型号的数据源
    int indexxh=-1;//用于检测是否选择了型号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_maintenance);
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
//            电脑品牌——————————————————————
            case R.id.ll_pinpai:
//                发起请求
                Y.get(DNYUR.FIND_COMPUTER_BRAND,null,  new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
//                        成功刷新进度条
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            listdn = JSON.parseArray(Y.getData(result), DianNaoWeiXiuBean.class);
//                            创建选择器
                            if (opv==null)
                            opv=  new OptionsPickerView.Builder(DianNaoWeiXiu.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                    tvPinpaiXianshi.setVisibility(View.VISIBLE);//显示品牌
                                    tvPp.setVisibility(View.GONE);//提示信息
                                    tvPinpaiXianshi.setText(listdn.get(options1).getName());//把解析出来的数据设置到控件里
                                    if (index!=options1){//如果不匹配当前数据
                                        tvLx.setVisibility(View.VISIBLE);//提示信息
                                        tvLeixingXianshi.setVisibility(View.GONE);//类型
                                        tvXh.setVisibility(View.VISIBLE);//提示信息
                                        tvXinghaoXianshi.setVisibility(View.GONE);//型号
                                        index=options1;//当前的索引
                                        opv=null;
                                    }
                                }
                            }).build();
//                            把list进行转换
                            List<String> strings=new ArrayList<String>();
                            for (DianNaoWeiXiuBean mb: listdn){
                                strings.add(mb.getName());
                            }
//                            添加数据
                            opv.setPicker(strings,null,null);
//                            显示选择器
                            if (!opv.isShowing())
                            opv.show();
                        }else {
//                            失败
                            Y.t("数据解析失败");
                        }
                    }
                });

                break;
            case R.id.ll_leixing:
//类型选择————————————————————————————————————————————————————————————
                if (index==-1){
                    Y.t("请先选择品牌");
                }else {
                    //                发起请求
                    HashMap<String, String> map = new HashMap<>();
                    map.put("pid",listdn.get(index).getId()+"");
                    Y.get(DNYUR.FIND_COMPUTER_CATEGORY, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
//                        成功刷新进度条fl
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listdnlb = JSON.parseArray(Y.getData(result), DianNaoWeiXiuBean.class);
//                            创建选择器
                                if (opv==null)
                                opv = new OptionsPickerView.Builder(DianNaoWeiXiu.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                        tvLeixingXianshi.setVisibility(View.VISIBLE);//显示品牌
                                        tvLx.setVisibility(View.GONE);//提示信息
                                        tvLeixingXianshi.setText(listdnlb.get(options1).getName());//把解析出来的数据设置到控件里
                                        indexlb = options1;//当前的索引
                                        opv=null;
                                    }
                                }).build();
//                            把list进行转换
                                List<String> strings = new ArrayList<String>();
                                for (DianNaoWeiXiuBean mb : listdnlb) {
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
            case R.id.ll_xinghao:
//  型号—————————————————————————————————————————————————————————
                if (index==-1){
                    Y.t("请先选择品牌");
                }else if (indexlb==-1){
                    Y.t("请先选择类型");
                }else {
                    //                发起请求
                    HashMap<String, String> map = new HashMap<>();

                    map.put("pid",listdn.get(index).getId()+"");
                    map.put("category",listdnlb.get(indexlb).getId()+"");
                    Y.get(DNYUR.FINDBY_COMPUTER_MODEL, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
//                        成功刷新进度条fl
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listdnxh = JSON.parseArray(Y.getData(result), DianNaoWeiXiuBean.class);
//                            创建选择器
                                if (opv==null)
                                opv = new OptionsPickerView.Builder(DianNaoWeiXiu.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                        tvXinghaoXianshi.setVisibility(View.VISIBLE);//显示品牌
                                        tvXh.setVisibility(View.GONE);//提示信息
                                        tvXinghaoXianshi.setText(listdnxh.get(options1).getName());//把解析出来的数据设置到控件里
                                        indexxh = options1;//当前的索引
                                        opv=null;
                                    }
                                }).build();
//                            把list进行转换
                                List<String> strings = new ArrayList<String>();
                                for (DianNaoWeiXiuBean mb : listdnxh) {
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
//                发起请求—————故障—————————————————————————————————————
                Y.get(DNYUR.FIND_PHONE_FAULT,null,  new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
//                        成功刷新进度条
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            listdn = JSON.parseArray(Y.getData(result), DianNaoWeiXiuBean.class);
//                            创建选择器
                            OptionsPickerView opv=new OptionsPickerView.Builder(DianNaoWeiXiu.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                    tvGuzhangXianshi.setVisibility(View.VISIBLE);//显示品牌
                                    tvGz.setVisibility(View.GONE);//提示信息
                                    tvGuzhangXianshi.setText(listdn.get(options1).getName());//把解析出来的数据设置到控件里

                                }
                            }).build();
//                            把list进行转换
                            List<String> strings=new ArrayList<String>();
                            for (DianNaoWeiXiuBean mb: listdn){
                                strings.add(mb.getName());
                            }
//                            添加数据
                            opv.setPicker(strings,null,null);
//                            显示选择器
                            opv.show();
                        }else {
//                            失败
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
