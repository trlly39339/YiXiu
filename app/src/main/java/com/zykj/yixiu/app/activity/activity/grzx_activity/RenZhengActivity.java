package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.RenZhengBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator on 2017/4/24.
 */

public class RenZhengActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.img_sfz_zm)
    ImageView imgSfzZm;
    @Bind(R.id.img_sfz_zm_gon)
    ImageView imgSfzZmGon;
    @Bind(R.id.img_sfz_zm_gong)
    ImageView imgSfzZmGong;
    @Bind(R.id.fl_sfz_zm)
    FrameLayout flSfzZm;
    @Bind(R.id.img_sfz_fm)
    ImageView imgSfzFm;
    @Bind(R.id.img_sfz_fm_gon)
    ImageView imgSfzFmGon;
    @Bind(R.id.img_sfz_fm_gong)
    ImageView imgSfzFmGong;
    @Bind(R.id.fl_sfz_fm)
    FrameLayout flSfzFm;
    @Bind(R.id.fl_but_tj)
    FrameLayout flButTj;
//    认证图片类
    private RenZhengBean renZhengBean=new RenZhengBean();
    private String photoPath;//身份证正面
    //身份证反面
    private String photoPath1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_rzxi);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
        if (!TextUtils.isEmpty(renZhengBean.getIdcard_image1())){
            ImageOptions options=new ImageOptions.Builder().setCircular(true).setUseMemCache(true).build();
            x.image().bind(imgSfzZm,renZhengBean.getIdcard_image1(),options);
            imgSfzZmGon.setVisibility(View.GONE);
            imgSfzZmGong.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(renZhengBean.getIdcard_image2())){
            ImageOptions options=new ImageOptions.Builder().setCircular(true).setUseMemCache(true).build();
            x.image().bind(imgSfzFm,renZhengBean.getIdcard_image2(),options);
            imgSfzFmGon.setVisibility(View.GONE);
            imgSfzFmGong.setVisibility(View.GONE);
        }
    }

    //    idcard_image1: 身份证正面
//    idcard_image2: 身份证反面
//    token: 用户令牌
    @OnClick({R.id.fl_sfz_zm, R.id.fl_sfz_fm,R.id.fl_but_tj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_sfz_zm:
                //   个人中心
                GalleryFinal.openGallerySingle(Y.REQUEST_CODE_GALLERY,   new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                        检测请求吗
                        if (reqeustCode == Y.REQUEST_CODE_GALLERY) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
//                                    Glide.with(GRZXActivity.this).load(info.getPhotoPath()).into(grzxTx);
                                    ImageOptions options = new ImageOptions.Builder()
                                            .setUseMemCache(true)
                                            .build();
                                    x.image().bind(imgSfzZm, info.getPhotoPath(), options);
                                    photoPath = info.getPhotoPath();
                                    imgSfzZmGon.setVisibility(View.GONE);
                                    imgSfzZmGong.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            case R.id.fl_sfz_fm:
                //   个人中心
                GalleryFinal.openGallerySingle(Y.REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                        检测请求吗
                        if (reqeustCode == Y.REQUEST_CODE_GALLERY) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
//                                    Glide.with(GRZXActivity.this).load(info.getPhotoPath()).into(grzxTx);
                                    ImageOptions options = new ImageOptions.Builder()
                                            .setUseMemCache(true)
                                            .build();
                                    x.image().bind(imgSfzFm, info.getPhotoPath(), options);
                                    photoPath1 = info.getPhotoPath();
                                    imgSfzFmGon.setVisibility(View.GONE);
                                    imgSfzFmGong.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            case R.id.fl_but_tj:

                //                                    发送请求
                RequestParams params=new RequestParams(YURL.UPLOAD_ID_CARD);
                params.addBodyParameter("idcard_image1", photoPath);
                params.addBodyParameter("idcard_image2", photoPath1);
                params.addBodyParameter("token", Y.TOKEN);
                Y.postFlie(params,new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Y.i(result);//打印一下看看是否为0
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            Y.t("上传成功");
                            renZhengBean.setIdcard_image1(Y.getData(result));
                            renZhengBean.setIdcard_image2(Y.getData(result));
                            Y.i(renZhengBean.getIdcard_image1());//打印一下看看有没有图片
                            finish();
                        } else {
                            Y.t("上传失败");
                        }
                    }
                });
                break;
        }
    }
}
