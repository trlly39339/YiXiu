package com.zykj.yixiu.app.activity.activity;

import android.content.Intent;
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
import com.zykj.yixiu.app.activity.bean.DianNaoBean;
import com.zykj.yixiu.app.activity.bean.DianNaoWeiXiuBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.DNYUR;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

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
    @Bind(R.id.gong)
    ImageView gong;
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
    int index = -1;//用于检测是否选择了品牌
    OptionsPickerView opv;

    List<DianNaoWeiXiuBean> listdnlb;//类别的数据源
    int indexlb = -1;//用于检测是否选择了类别

    List<DianNaoWeiXiuBean> listdnxh;//型号的数据源
    int indexxh = -1;//用于检测是否选择了型号
    @Bind(R.id.img)
    ImageView img;

    private String photoPath;
    //    创建电脑维修数据存储类
    private DianNaoBean dianNaoBean = new DianNaoBean();

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
                Y.get(DNYUR.FIND_COMPUTER_BRAND, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
//                        成功刷新进度条
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            listdn = JSON.parseArray(Y.getData(result), DianNaoWeiXiuBean.class);
//                            创建选择器
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(DianNaoWeiXiu.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                        tvPinpaiXianshi.setVisibility(View.VISIBLE);//显示品牌
                                        tvPp.setVisibility(View.GONE);//提示信息
                                        String pinpai = listdn.get(options1).getName();
                                        tvPinpaiXianshi.setText(pinpai);//把解析出来的数据设置到控件里
                                        dianNaoBean.setPinpai(pinpai);//把最终得到的品牌数据设置到数据类里
                                        if (index != options1) {//如果不匹配当前数据
                                            tvLx.setVisibility(View.VISIBLE);//提示信息
                                            tvLeixingXianshi.setVisibility(View.GONE);//类型
                                            tvXh.setVisibility(View.VISIBLE);//提示信息
                                            tvXinghaoXianshi.setVisibility(View.GONE);//型号
                                            index = options1;//当前的索引
                                            opv = null;
                                        }
                                    }
                                }).build();
//                            把list进行转换
                            List<String> strings = new ArrayList<String>();
                            for (DianNaoWeiXiuBean mb : listdn) {
                                strings.add(mb.getName());
                            }
//                            添加数据
                            opv.setPicker(strings, null, null);
//                            显示选择器
                            if (!opv.isShowing())
                                opv.show();
                        } else {
//                            失败
                            Y.t("数据解析失败");
                        }
                    }
                });

                break;
            case R.id.ll_leixing:
//类型选择————————————————————————————————————————————————————————————
                if (index == -1) {
                    Y.t("请先选择品牌");
                } else {
                    //                发起请求
                    HashMap<String, String> map = new HashMap<>();
                    map.put("pid", listdn.get(index).getId() + "");
                    Y.get(DNYUR.FIND_COMPUTER_CATEGORY, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
//                        成功刷新进度条fl
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listdnlb = JSON.parseArray(Y.getData(result), DianNaoWeiXiuBean.class);
//                            创建选择器
                                if (opv == null)
                                    opv = new OptionsPickerView.Builder(DianNaoWeiXiu.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                            tvLeixingXianshi.setVisibility(View.VISIBLE);//显示品牌
                                            tvLx.setVisibility(View.GONE);//提示信息
                                            String leixing = listdnlb.get(options1).getName();//获取最终数据
                                            tvLeixingXianshi.setText(leixing);//把解析出来的数据设置到控件里
                                            dianNaoBean.setLeixing(leixing);//把最终的数据存到电脑数据累里
                                            indexlb = options1;//当前的索引
                                            opv = null;
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
                                if (!opv.isShowing())
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
                if (index == -1) {
                    Y.t("请先选择品牌");
                } else if (indexlb == -1) {
                    Y.t("请先选择类型");
                } else {
                    //                发起请求
                    HashMap<String, String> map = new HashMap<>();

                    map.put("pid", listdn.get(index).getId() + "");
                    map.put("category", listdnlb.get(indexlb).getId() + "");
                    Y.get(DNYUR.FINDBY_COMPUTER_MODEL, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
//                        成功刷新进度条fl
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listdnxh = JSON.parseArray(Y.getData(result), DianNaoWeiXiuBean.class);
//                            创建选择器
                                if (opv == null)
                                    opv = new OptionsPickerView.Builder(DianNaoWeiXiu.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                            tvXinghaoXianshi.setVisibility(View.VISIBLE);//显示品牌
                                            tvXh.setVisibility(View.GONE);//提示信息
                                            String xinghao = listdnxh.get(options1).getName();//获取最终数据
                                            tvXinghaoXianshi.setText(xinghao);//把解析出来的数据设置到控件里
                                            dianNaoBean.setXinghao(xinghao);//把最终的数据存到电脑数据累里
                                            indexxh = options1;//当前的索引
                                            opv = null;
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
                                if (!opv.isShowing())
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
                Y.get(DNYUR.FIND_PHONE_FAULT, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
//                        成功刷新进度条
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            listdn = JSON.parseArray(Y.getData(result), DianNaoWeiXiuBean.class);
//                            创建选择器
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(DianNaoWeiXiu.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                        tvGuzhangXianshi.setVisibility(View.VISIBLE);//显示品牌
                                        tvGz.setVisibility(View.GONE);//提示信息
                                        String guzhang = listdn.get(options1).getName();//获取最终数据
                                        tvGuzhangXianshi.setText(guzhang);//把解析出来的数据设置到控件里
                                        dianNaoBean.setGuzhang(guzhang);//把最终的数据存到电脑数据累里
                                        opv = null;

                                    }
                                }).build();
//                            把list进行转换
                            List<String> strings = new ArrayList<String>();
                            for (DianNaoWeiXiuBean mb : listdn) {
                                strings.add(mb.getName());
                            }
//                            添加数据
                            opv.setPicker(strings, null, null);
//                            显示选择器
                            if (!opv.isShowing())
                                opv.show();
                        } else {
//                            失败
                            Y.t("数据解析失败");
                        }
                    }
                });
                break;
            case R.id.fl_tianjiatupian:
                GalleryFinal.openGallerySingle(Y.REQUEST_CODE_GALLERY,  new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                        检测请求吗
                        if (reqeustCode == Y.REQUEST_CODE_GALLERY) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
//                                    Glide.with(GRZXActivity.this).load(info.getPhotoPath()).into(grzxTx);

                                    ImageOptions options = new ImageOptions.Builder().build();
                                    x.image().bind(img, info.getPhotoPath(), options);
                                    gong.setVisibility(View.GONE);
//                                    发送请求
                                    photoPath = info.getPhotoPath();
                                    dianNaoBean.setFileimg(photoPath);
                                }
                            }
                        }
                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            case R.id.but_queren:
                //                获取故障描述
                String guzhangmiaoshu = evGuzhangXiangqing.getText().toString().trim();
                if (guzhangmiaoshu !=null) {
//                    故障描述设置到手机类里
                    dianNaoBean.setGuzhangmiaoshu(guzhangmiaoshu);
                }
                if (tvPinpaiXianshi.getText().toString()==""){
                    Y.t("请输入品牌");
                }else if (tvLeixingXianshi.getText().toString()==""){
                    Y.t("请输入类型");
                }else if (tvXinghaoXianshi.getText().toString()==""){
                    Y.t("请输入型号");
                }else if (tvGuzhangXianshi.getText().toString()==""){
                    Y.t("请输入故障信息");
                }else if (dianNaoBean.getFileimg()==null){
                    Y.t("必须上传一张图片");
                }else {
                    Intent intent = new Intent(DianNaoWeiXiu.this, HuJiaoFuWuActivity.class);
                    intent.putExtra("dianNaoBean", dianNaoBean);
                    intent.putExtra("LeiXing", "2");
                    startActivity(intent);
                    finish();
                }

                break;
        }
    }
}
