package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.ChaXunDingDanBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/5/6.
 */

public class DingDanXiangQingActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.shifu)
    LinearLayout shifu;
    @Bind(R.id.dingdan_genzong)
    LinearLayout dingdanGenzong;
    @Bind(R.id.shouji)
    TextView shouji;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.shoujihao)
    TextView shoujihao;
    @Bind(R.id.xiadanshijian)
    TextView xiadanshijian;
    @Bind(R.id.xiadandizhi)
    TextView xiadandizhi;
    @Bind(R.id.pinpai)
    TextView pinpai;
    @Bind(R.id.xinghao)
    TextView xinghao;
    @Bind(R.id.guzhang)
    TextView guzhang;
    @Bind(R.id.guzhangmiaoshu)
    TextView guzhangmiaoshu;
    @Bind(R.id.dingdan_wancheng)
    LinearLayout dingdanWancheng;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.dingdan_zhuangtai_img)
    ImageView dingdanZhuangtaiImg;
    @Bind(R.id.dingdan_zhuangtai)
    TextView dingdanZhuangtai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzx_ddanxiangqing);
        ButterKnife.bind(this);
        initView();

    }

    public void initView() {
        Intent intent = getIntent();
        ChaXunDingDanBean chaXunDingDanBean = (ChaXunDingDanBean) intent.getSerializableExtra("chaXunDingDanBean");
        String zhuangtai = intent.getStringExtra("zhuangtai");
        switch (zhuangtai) {
            case "1":
                shifu.setVisibility(View.GONE);
                break;
            case "2":
                shifu.setVisibility(View.GONE);
                dingdanWancheng.setVisibility(View.VISIBLE);
                dingdanGenzong.setVisibility(View.GONE);
                break;
            case "3":
                shifu.setVisibility(View.GONE);
                dingdanWancheng.setVisibility(View.VISIBLE);
                dingdanGenzong.setVisibility(View.GONE);
                dingdanZhuangtaiImg.setImageResource(R.mipmap.grzx_ddxq_genzong_q);
                dingdanZhuangtai.setText("订单已取消");
                break;
        }
    }
}
