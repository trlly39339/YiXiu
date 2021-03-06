package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.grzx_activity.wdqb_activity.WDQBActivity;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/19.
 */

public class GRZXActivity extends BaseActivity {


    @Bind(R.id.fanhui)
    ImageView fanhui;
    @Bind(R.id.grzx_tx)
    ImageView grzxTx;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.name_image_nv)
    ImageView nameImageNv;
    @Bind(R.id.tv_wrz)
    TextView tvWrz;
    @Bind(R.id.fl_wwc)
    FrameLayout flWwc;
    @Bind(R.id.fl_ywc)
    FrameLayout flYwc;
    @Bind(R.id.fl_yqx)
    FrameLayout flYqx;
    @Bind(R.id.ll_wdzl)
    LinearLayout llWdzl;
    @Bind(R.id.ll_wdqb)
    LinearLayout llWdqb;
    @Bind(R.id.ll_rzgl)
    LinearLayout llRzgl;
    @Bind(R.id.ll_rzxx)
    LinearLayout llRzxx;
    @Bind(R.id.ll_ptfw)
    LinearLayout llPtfw;
    @Bind(R.id.ll_gywm)
    LinearLayout llGywm;
    @Bind(R.id.ll_sz)
    LinearLayout llSz;
    @Bind(R.id.weiwancheng_shuliang_img)
    ImageView weiwanchengShuliangImg;
    @Bind(R.id.dingdan_shuliang)
    TextView dingdanShuliang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzx);
        ButterKnife.bind(this);
        weiWanChengDingDanShuLiang();
    }

    @OnClick({R.id.fanhui, R.id.grzx_tx, R.id.fl_wwc, R.id.fl_ywc, R.id.fl_yqx, R.id.ll_wdzl, R.id.ll_wdqb, R.id.ll_rzgl, R.id.ll_rzxx, R.id.ll_ptfw, R.id.ll_gywm, R.id.ll_sz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.grzx_tx:
//   个人中心
                GalleryFinal.openGallerySingle(Y.REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                        检测请求吗
                        if (reqeustCode == Y.REQUEST_CODE_GALLERY) {

                            if (resultList != null) {
                                for (final PhotoInfo info : resultList) {
//                                    发送请求
                                    File file = new File(info.getPhotoPath());
                                    RequestParams params = new RequestParams(YURL.UP_LOAD_ICON);
                                    params.addBodyParameter("icon", file);
                                    params.addBodyParameter("token", Y.TOKEN);
                                    Y.postFlie(params, new Y.MyCommonCall<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            StyledDialog.dismissLoading();
                                            if (Y.getRespCode(result)) {
                                                Y.i(result);
                                                Y.t("上传成功");
                                                Y.USER.setIcon(Y.getData(result));
//                                                成功后直接添加本地图片到控件上就行
                                                ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
                                                x.image().bind(grzxTx, info.getPhotoPath(), options);
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
            case R.id.fl_wwc:
//                未完成
                Intent intent = new Intent(GRZXActivity.this, WoDeDingDanActivity.class);
                intent.putExtra("zhuangtai", "1");
                startActivity(intent);
                break;
            case R.id.fl_ywc:
//                已完成
                startActivity(new Intent(GRZXActivity.this, WoDeDingDanActivity.class).putExtra("zhuangtai", "2"));
                break;
            case R.id.fl_yqx:
//                已取消
                startActivity(new Intent(GRZXActivity.this, WoDeDingDanActivity.class).putExtra("zhuangtai", "3"));
                break;
            case R.id.ll_wdzl:
//                我的资料
                startActivity(new Intent(GRZXActivity.this, WoDeZiLiaoActivity.class));
                break;
            case R.id.ll_wdqb:
//                我的钱包
                startActivity(new Intent(GRZXActivity.this, WDQBActivity.class));
                break;
            case R.id.ll_rzgl:
//                地址管理
                startActivity(new Intent(GRZXActivity.this, DiZhiGuanLiActivity.class));
                break;
            case R.id.ll_rzxx:
//                认证信息
                startActivity(new Intent(GRZXActivity.this, RenZhengActivity.class));
                break;
            case R.id.ll_ptfw:
//                平台服务
                startActivity(new Intent(GRZXActivity.this, PingTaiFuWuActivity.class));
                break;
            case R.id.ll_gywm:
//                关于我们
                startActivity(new Intent(GRZXActivity.this, GuanYuWoMenActivity.class));
                break;
            case R.id.ll_sz:

//                设置
                startActivity(new Intent(GRZXActivity.this, SheZhiActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        weiWanChengDingDanShuLiang();
        if (!TextUtils.isEmpty(Y.USER.getIcon())) {//图片不为空的时候直接加载到控件上
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(grzxTx, YURL.HOST + Y.USER.getIcon(), options);
        }
    }

    //    未完成订单数量
    public void weiWanChengDingDanShuLiang() {
        RequestParams params = new RequestParams(YURL.FIND_UN_FINISH_COUNT);
        params.addBodyParameter("custom_id", Y.USER.getUser_id() + "");
        Y.post(params, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    dingdanShuliang.setText(Y.getData(result));
                }
            }
        });
    }
}
