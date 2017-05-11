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
import com.zykj.yixiu.app.activity.bean.DianNaoFenLeiBean;
import com.zykj.yixiu.app.activity.bean.JiaDianBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.JIADIANL;
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
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.gong)
    ImageView gong;
    private List<DianNaoFenLeiBean> lists;//品牌数据源
    int index = -1;

    private List<DianNaoFenLeiBean> listslx;//品牌数据源
    int indexlx = -1;

    private List<DianNaoFenLeiBean> listsxh;//品牌数据源
    int indexxh = -1;
    OptionsPickerView opv;
    //    创建家电数据存储类
    private JiaDianBean jiaDianBean = new JiaDianBean();

    private String photoPath;

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
//                品牌对接
                Y.get(JIADIANL.FIND_BY_APPLIANCE_BRAND, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), DianNaoFenLeiBean.class);
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(JiaDianWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        pinpaiXianshi.setVisibility(View.GONE);
                                        pinpaiX.setVisibility(View.VISIBLE);
                                        String pinpai = lists.get(options1).getName();//获取品牌数据
                                        pinpaiX.setText(pinpai);
                                        jiaDianBean.setPinpai(pinpai);//把最终选定的数据设置到数据存储类中
                                        if (index != options1) {
                                            leixingXianshi.setVisibility(View.VISIBLE);//类型提示
                                            leixingX.setVisibility(View.GONE);//类型显示
                                            xinghaoXianshi.setVisibility(View.VISIBLE);//型号提示
                                            xinghaoX.setVisibility(View.GONE);//型号显示
                                            index = options1;
                                            opv = null;
                                        }

                                    }
                                }).build();
                            List<String> list = new ArrayList<String>();
                            for (DianNaoFenLeiBean bm : lists) {
                                list.add(bm.getName());

                            }
//                        添加数据
                            opv.setPicker(list, null, null);
//                        显示
                            if (!opv.isShowing())
                                opv.show();
                        } else {
                            Y.t("数据解析失败");
                        }
                    }
                });

                break;
            case R.id.ll_leixing:
// 类型对接
                //检测是否选择了品牌
                if (index == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取类型数据
                    //发起请求
                    HashMap<String, String> map = new HashMap<>();
                    map.put("pid", lists.get(index).getId() + "");
                    Y.get(JIADIANL.FIND_APPLIANCE_CATEGORY, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                listslx = JSON.parseArray(Y.getData(result), DianNaoFenLeiBean.class);
                                //创建选择器
                                if (opv == null)
                                    opv = new OptionsPickerView.Builder(JiaDianWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                            //选择后的监听器
                                            leixingXianshi.setVisibility(View.GONE);
                                            leixingX.setVisibility(View.VISIBLE);
                                            String leixing = listslx.get(options1).getName();//获取品牌数据
                                            leixingX.setText(leixing);
                                            jiaDianBean.setLeixing(leixing);//把最终选定的数据设置到数据存储类中
                                            if (indexlx != options1) {
                                                xinghaoXianshi.setVisibility(View.VISIBLE);//型号提示
                                                xinghaoX.setVisibility(View.GONE);//型号显示
                                                indexlx = options1;
                                                opv = null;
                                            }
                                        }
                                    }).build();
                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (DianNaoFenLeiBean mb : listslx) {
                                    strs.add(mb.getName());
                                }
                                //添加数据
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                if (!opv.isShowing())
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
                if (index == -1) {
                    Y.t("请先选择品牌");
                } else if (indexlx == -1) {
                    Y.t("请先选择类型");
                } else {
                    //                发起型号请求
                    HashMap<String, String> map = new HashMap<>();
                    map.put("pid", lists.get(index).getId() + "");
                    map.put("category", listslx.get(indexlx).getId() + "");
                    Y.get(JIADIANL.FIND_BY_APPLIANCE_MODEL, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
//                        成功刷新进度条fl
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listsxh = JSON.parseArray(Y.getData(result), DianNaoFenLeiBean.class);
//                            创建选择器
                                if (opv == null)
                                    opv = new OptionsPickerView.Builder(JiaDianWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                                    选择后的监听器
                                            xinghaoX.setVisibility(View.VISIBLE);//显示品牌
                                            xinghaoXianshi.setVisibility(View.GONE);//提示信息
                                            String xinghao = listsxh.get(options1).getName();//获取品牌数据
                                            xinghaoX.setText(xinghao);//把解析出来的数据设置到控件里
                                            jiaDianBean.setXinghao(xinghao);//把最终选定的数据设置到数据存储类中
                                            indexxh = options1;//当前的索引
                                            opv = null;
                                        }
                                    }).build();
//                            把list进行转换
                                List<String> strings = new ArrayList<String>();
                                for (DianNaoFenLeiBean mb : listsxh) {
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
                //发起故障请求
                Y.get(JIADIANL.FIND_PHONE_FAULT, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), DianNaoFenLeiBean.class);
                            //创建选择器
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(JiaDianWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        guzhangXianshi.setVisibility(View.GONE);
                                        guzhangX.setVisibility(View.VISIBLE);
                                        String guzhang = lists.get(options1).getName();//获取品牌数据
                                        guzhangX.setText(guzhang);
                                        jiaDianBean.setGuzhang(guzhang);//把最终选定的数据设置到数据存储类中
                                        opv = null;
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
                            if (!opv.isShowing())
                                opv.show();
                        } else {
                            //失败
                            Y.t("数据解析失败");
                        }
                    }
                });
                break;
            case R.id.fl_tianjiatupian:
                GalleryFinal.openGallerySingle(Y.REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
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
                                    jiaDianBean.setFileimg(photoPath);
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
                String guzhangmiaoshu = evGuzhangXiangqing.getText().toString().trim();
                if (guzhangmiaoshu != null) {
                    jiaDianBean.setGuzhangmiaoshu(guzhangmiaoshu);
                }
                if (pinpaiX.getText().toString()==""){
                    Y.t("请输入品牌");
                }else if (leixingX.getText().toString()==""){
                    Y.t("请输入类型");
                }else if (xinghaoX.getText().toString()==""){
                    Y.t("请输入型号");
                }else if (guzhangX.getText().toString()==""){
                    Y.t("请输入故障信息");
                }else if (jiaDianBean.getFileimg()==null){
                    Y.t("必须上传一张图片");
                }{
                    Intent intent = new Intent(JiaDianWeiXiuActivity.this, HuJiaoFuWuActivity.class);
                    intent.putExtra("jiaDianBean", jiaDianBean);
                    intent.putExtra("LeiXing", "3");
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
