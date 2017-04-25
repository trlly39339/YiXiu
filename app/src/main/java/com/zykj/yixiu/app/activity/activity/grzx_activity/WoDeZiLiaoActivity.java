package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    private  String SEX;//    储存选中的性别
    private String name;//姓名
    private String phoneh;//手机号
    private String nan;//男
    private String nv;//女
    private String sheng;//省
    private String shi;//市

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
        //      检测是否已存在头像 如果存在并设置到空间上 否则什么也不干
        if (!TextUtils.isEmpty(Y.USER.getIcon())){//图片不为空的时候直接加载到控件上
            ImageOptions options=new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(wdzlTxImg,Y.USER.getIcon(),options);
            wdzlGongImg.setVisibility(View.GONE);//隐藏头像叠加图片
        }
//        用户姓名不为空的时候取出
        if (!TextUtils.isEmpty(Y.USER.getUsername())){
            evName.setText(Y.USER.getUsername());//姓名设置到空间上
//            姓名显示到右侧去
            evName.setGravity(Gravity.RIGHT);
//            用户性别不为空的时候
            if (!TextUtils.isEmpty(Y.USER.getSex())) {
//                用户性别与男的地址是否相等
                if ("男".equals(Y.USER.getSex())) {
//                    相等选中男
                    rbNan.setChecked(true);
                }
                if ("女".equals(Y.USER.getSex())){
//                    否则选中女
                    rbNv.setChecked(true);
                }
            }
//            省不为空时候
            if (!TextUtils.isEmpty(Y.USER.getProvince())) {
//                设置省
                tvSheng.setText(Y.USER.getProvince());
//                设置市
                tvShi.setText(Y.USER.getCity());
            }
//            设置手机号
            etPhoneH.setText(Y.USER.getPhone());
        }
//        ——————————————————————————————————————————————

    }

    @OnClick({R.id.wdzl_genggaitx_ll, R.id.ll_diqu, R.id.but_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wdzl_genggaitx_ll:
                //   单选头像
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
                                    Y.i(info.getPhotoPath());
//                                    发送请求 上传头像图片
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
//            ________________________________________________________________________
            case R.id.but_queren:

                //姓名shez
                name = evName.getText().toString().trim();
                Y.USER.setUsername(name);
                //电话号
                phoneh = etPhoneH.getText().toString().trim();
                Y.USER.setPhone(phoneh);
                //                设置省份
                sheng = tvSheng.getText().toString();
                Y.USER.setProvince(sheng);
//                设置市
                shi = tvShi.getText().toString();
                Y.USER.setCity(shi);
                //是否选中  如果选中男 设置到user.setSex里  否则什么也不干
                nan = rbNan.getText().toString();
                nv = rbNv.getText().toString();
                if (rbNan.isChecked()){
                    Y.USER.setSex(nan);
                }
                if (rbNv.isChecked()){//是否选中  如果选中女 设置到user.setSex里  否则什么也不干
                    Y.USER.setSex(nv);
                    }
                RequestParams params=new RequestParams(YURL.SET_USER_INFO);
                params.addBodyParameter("username", Y.USER.getUsername()+"");
                params.addBodyParameter("sex",Y.USER.getSex()+"");
                params.addBodyParameter("province", Y.USER.getProvince()+"");
                params.addBodyParameter("city", Y.USER.getCity()+"");
                params.addBodyParameter("user_id",Y.USER.getUser_id()+"");
                params.addBodyParameter("token",Y.TOKEN);
                Y.post(params, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            Y.t("上传成功");
                        }else {
                            Y.t("上传失败");
                        }
                    }
                });
                break;
        }
    }
}
