package com.zykj.yixiu.app.activity.activity.denglu_zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.MainActivity;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.User;
import com.zykj.yixiu.app.activity.bean.ZhuCeBase;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class DengLuActivity extends BaseActivity {


    @Bind(R.id.denglu_tx)
    ImageView dengluTx;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.ev_phone)
    EditText evPhone;
    @Bind(R.id.ev_pwd)
    EditText evPwd;
    @Bind(R.id.fl_but_denglu)
    FrameLayout flButDenglu;
    @Bind(R.id.tv_zhuce)
    TextView tvZhuce;
    @Bind(R.id.tv_wjmm)
    TextView tvWjmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        ButterKnife.bind(this);
//         if (!TextUtils.isEmpty(Y.USER.getIcon())){//图片不为空的时候直接加载到控件上
//            ImageOptions options=new ImageOptions.Builder().setCircular(true).setUseMemCache(true).build();
//            x.image().bind(dengluTx,Y.USER.getIcon(),options);
//        }

    }

    @OnClick({R.id.fl_but_denglu, R.id.tv_zhuce, R.id.tv_wjmm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_but_denglu:
                //把手机号和密码取出来
                String numm = evPhone.getText().toString().trim();
                String pwd = evPwd.getText().toString().trim();
                //判断手机号是否为空
                if (TextUtils.isEmpty(numm)){
                    Y.t("手机号不能为空");
                    return;
                }

                //判断验证码是否为空
                if (TextUtils.isEmpty(pwd)){
                    Y.t("密码不能为空");
                    return;
                }
                //创建map的集合 把需要的数据传上去
                Map<String,String> map=new HashMap<String, String>();
                map.put("phone",numm);//phone 电话号码
                map.put("password",pwd);// password  密码
                Y.get(YURL.LOGIN,map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //成功后关闭dialog
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            User user = JSON.parseObject(Y.getData(result), User.class);

                            Y.USER=user;
                            Y.TOKEN=user.getToken();
                            Y.t("登录成功");
                            //成功之后跳转页面 调到主页面
                            Intent intent = new Intent(DengLuActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            //失败
                            Y.t("登录失败");
                        }
                    }
                });
                break;
            case R.id.tv_zhuce:
                startActivity(new Intent(DengLuActivity.this,ZhuCeActivity.class));
                break;
            case R.id.tv_wjmm:
                startActivity(new Intent(DengLuActivity.this,WangJiMiMaActivity.class));
                break;
        }
    }
}
