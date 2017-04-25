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
import com.zykj.yixiu.app.activity.bean.PhoneWeiXiuBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.http.RequestParams;
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
    @Bind(R.id.tp_img)
    ImageView tpImg;
    @Bind(R.id.tp_img_gon)
    ImageView tpImgGon;
    private OptionsPickerView opv;


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
                Y.get(YURL.FIND_PHONE_BRAND, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), PhoneWeiXiuBean.class);
                            //创建选择器

                            //选择后的监听器
// 当前选择的索引
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(PhoneWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        tvPp.setVisibility(View.GONE);
                                        tvPinpai.setVisibility(View.VISIBLE);
                                        tvPinpai.setText(lists.get(options1).getName());
                                        if (mobileIndex != options1) {
                                            tvXh.setVisibility(View.VISIBLE);
                                            tvXinghao.setVisibility(View.GONE);
                                            mobileIndex = options1;// 当前选择的索引
                                            opv = null;
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
                            if (!opv.isShowing())
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
                    map.put("pid", lists.get(mobileIndex).getId() + "");
                    Y.get(YURL.FIND_PHONE_MODEL, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), PhoneWeiXiuBean.class);
                                //创建选择器
                                if (opv == null)
                                    opv = new OptionsPickerView.Builder(PhoneWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                            //选择后的监听器
                                            tvXh.setVisibility(View.GONE);
                                            tvXinghao.setVisibility(View.VISIBLE);
                                            tvXinghao.setText(lists.get(options1).getName());
                                            opv = null;

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
            case R.id.ll_guzhang:
                //发起请求
                Y.get(YURL.FIND_PHONE_FAULT, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), PhoneWeiXiuBean.class);
                            //创建选择器
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(PhoneWeiXiuActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        tvGz.setVisibility(View.GONE);
                                        tvGuzhang.setVisibility(View.VISIBLE);
                                        tvGuzhang.setText(lists.get(options1).getName());
                                        opv = null;
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
                                    ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
                                    x.image().bind(tpImg, info.getPhotoPath(), options);
//                                    发送请求
                                    RequestParams params = new RequestParams(YURL.UP_LOAD_ICON);
                                    params.addBodyParameter("icon", info.getPhotoPath());
                                    params.addBodyParameter("token", Y.TOKEN);
                                    Y.post(params, new Y.MyCommonCall<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            StyledDialog.dismissLoading();
                                            if (Y.getRespCode(result)) {
                                                Y.t("上传成功");
                                                tpImgGon.setVisibility(View.GONE);
                                                Y.USER.setIcon(Y.getData(result));
                                            } else {
                                                Y.t("上传失败");
                                            }
                                        }
                                    });
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

                break;
        }
    }
}
