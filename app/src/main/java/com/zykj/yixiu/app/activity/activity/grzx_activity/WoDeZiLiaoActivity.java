package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.yixiuge_utils.OptionsPicke;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/22.
 */

public class WoDeZiLiaoActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.wdzl_tx_img)
    ImageView wdzlTxImg;
    @Bind(R.id.wdzl_gong_img)
    ImageView wdzlGongImg;
    @Bind(R.id.wdzl_genggaitx_ll)
    LinearLayout wdzlGenggaitxLl;
    @Bind(R.id.ev_name)
    EditText evName;
    @Bind(R.id.rb_nan)
    RadioButton rbNan;
    @Bind(R.id.rb_nv)
    RadioButton rbNv;
    @Bind(R.id.et_phone_h)
    EditText etPhoneH;
    @Bind(R.id.tv_sheng)
    TextView tvSheng;
    @Bind(R.id.tv_shi)
    TextView tvShi;
    @Bind(R.id.ll_diqu)
    LinearLayout llDiqu;
    @Bind(R.id.but_queren)
    Button butQueren;

//    储存选中的性别
    private  String SEX;
    private String name;
    private String phoneh;
    private String nan;
    private String nv;
    private String sheng;
    private String shi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl);
        ButterKnife.bind(this);
//        返回按钮
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });

//        ———————————————————————————————————————
        //      检测是否已存在头像 如果存在并上传 否则什么也不干
        if (!TextUtils.isEmpty(Y.USER.getIcon())){//图片不为空的时候直接加载到控件上
            ImageOptions options=new ImageOptions.Builder().setCircular(true).setUseMemCache(true).build();
            x.image().bind(wdzlTxImg,Y.USER.getIcon(),options);
            wdzlGongImg.setVisibility(View.GONE);//隐藏头像叠加图片
        }
        if (!TextUtils.isEmpty(Y.USER.getUsername())){
            evName.setText(Y.USER.getUsername());//
            if (SEX.equals(rbNan)){
                rbNan.setChecked(true);
                return;
            }else if (SEX.equals(rbNv)){
                rbNv.setChecked(true);
                return;
            }
            etPhoneH.setText(Y.USER.getPhone());
            tvSheng.setText(Y.USER.getProvince());
            tvShi.setText(Y.USER.getCity());
        }
//        ——————————————————————————————————————————————

    }

    @OnClick({R.id.wdzl_genggaitx_ll, R.id.ll_diqu, R.id.but_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wdzl_genggaitx_ll:
                //   个人中心
                GalleryFinal.openGallerySingle(Y.REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                        检测请求吗
                        if (reqeustCode==Y.REQUEST_CODE_GALLERY){
                            if (resultList!=null){
                                for (PhotoInfo info:resultList) {
//                                    Glide.with(GRZXActivity.this).load(info.getPhotoPath()).into(grzxTx);
                                    ImageOptions options=new ImageOptions.Builder().setCircular(true).setUseMemCache(true).build();
                                    x.image().bind(wdzlTxImg,info.getPhotoPath(),options);
//                                    发送请求
                                    RequestParams params=new RequestParams(YURL.UP_LOAD_ICON);
                                    params.addBodyParameter("icon",info.getPhotoPath());
                                    params.addBodyParameter("token", Y.TOKEN);
                                    Y.post(params, new Y.MyCommonCall<String>() {
                                        @Override
                                        public void onSuccess(String result) {

                                            StyledDialog.dismissLoading();
                                            if (Y.getRespCode(result)) {
                                                Y.t("上传成功");
                                                Y.USER.setIcon(Y.getData(result));
                                            }else {
                                                Y.t("上传失败");
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) { }
                });
                break;
            case R.id.ll_diqu:
                /*
                * 全国城市三级联动
                * */
                OptionsPicke optionsPicke=new OptionsPicke();
                optionsPicke.showOptionsPicke(this, new OptionsPicke.OptionsSelectListener() {
                    @Override
                    public void selectListener(String province, String city, String district) {
                        tvSheng.setText(province);//省设置
                        tvShi.setText(city);//市设置
                    }
                });
                break;
            case R.id.but_queren:
                //姓名shez
                name = evName.getText().toString().trim();
                //电话号
                phoneh = etPhoneH.getText().toString().trim();
                //                设置省份
                sheng = tvSheng.getText().toString();
//                设置市
                shi = tvShi.getText().toString();
                //是否选中  如果选中男 设置到user.setSex里  否则什么也不干
                nan = rbNan.getText().toString();
                nv = rbNv.getText().toString();
                if (rbNan.isChecked()){
                    Y.t(nan);
                    SEX= nan;
                    return;
                }else if (rbNv.isChecked()){//是否选中  如果选中女 设置到user.setSex里  否则什么也不干
                        Y.t(nv);
                        SEX= nv;
                        return;
                    }
                RequestParams params=new RequestParams(YURL.SET_USER_INFO);
                params.addBodyParameter("username", name);
                params.addBodyParameter("sex",SEX);
                params.addBodyParameter("province", sheng);
                params.addBodyParameter("city", shi);
                params.addBodyParameter("user_id",Y.USER.getUser_id()+"");
                params.addBodyParameter("token",Y.TOKEN);
                Y.post(params, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        Y.USER.setUsername(name);//姓名设置到USER
                        Y.USER.setPhone(phoneh);//手机号设置到USER
                        Y.USER.setSex(SEX);//性别设置到USER
                        Y.USER.setProvince(sheng);//省设置到USER
                        Y.USER.setCity(shi);//市设置到USER
                    }
                });
                break;
        }
    }
}
