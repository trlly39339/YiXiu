package com.zykj.yixiu.app.activity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

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
            case R.id.ll_pinpai:
                break;
            case R.id.ll_leixing:
                break;
            case R.id.ll_xinghao:
                break;
            case R.id.ll_guzhang:
                break;
            case R.id.fl_tianjiatupian:
                break;
            case R.id.but_queren:
                break;
        }
    }
}
