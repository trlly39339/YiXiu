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
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

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
        if (!TextUtils.isEmpty(Y.USER.getIdcard_image1())){
            ImageOptions options=new ImageOptions.Builder().setCircular(true).setUseMemCache(true).build();
            x.image().bind(imgSfzZm,Y.USER.getIdcard_image1(),options);
            imgSfzZmGon.setVisibility(View.GONE);
            imgSfzZmGong.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(Y.USER.getIdcard_image2())){
            ImageOptions options=new ImageOptions.Builder().setCircular(true).setUseMemCache(true).build();
            x.image().bind(imgSfzFm,Y.USER.getIdcard_image2(),options);
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
                GalleryFinal.openGallerySingle(Y.REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                        检测请求吗
                        if (reqeustCode == Y.REQUEST_CODE_GALLERY) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
//                                    Glide.with(GRZXActivity.this).load(info.getPhotoPath()).into(grzxTx);
                                    ImageOptions options = new ImageOptions.Builder().setCircular(true)
                                            .setUseMemCache(true)
                                            .build();
                                    x.image().bind(imgSfzZm, info.getPhotoPath(), options);
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
                                    ImageOptions options = new ImageOptions.Builder().setCircular(true)
                                            .setUseMemCache(true)
                                            .build();
                                    x.image().bind(imgSfzFm, info.getPhotoPath(), options);
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
                Map<String, String> map = new HashMap<String, String>();
                map.put("idcard_image1", imgSfzZm.getResources().toString());
                map.put("idcard_image2", imgSfzFm.getResources().toString());
                map.put("token", Y.TOKEN);
                Y.get(YURL.UPLOAD_ID_CARD, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {

                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            Y.t("上传成功");
                            Y.USER.setIdcard_image1(Y.getData(result));
                            Y.USER.setIdcard_image2(Y.getData(result));
                        } else {
                            Y.t("上传失败");
                        }
                    }
                });
                break;
        }
    }
}
