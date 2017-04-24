package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.adapters.WoDeDingDanAdapter;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/23.
 */

public class WoDeDingDanActivity extends BaseActivity {

    @Bind(R.id.wwc_tv)
    TextView wwcTv;
    @Bind(R.id.wwc_img)
    ImageView wwcImg;
    @Bind(R.id.wwc_ll)
    LinearLayout wwcLl;
    @Bind(R.id.ywc_tv)
    TextView ywcTv;
    @Bind(R.id.ywc_img)
    ImageView ywcImg;
    @Bind(R.id.ywc_ll)
    LinearLayout ywcLl;
    @Bind(R.id.yqx_tv)
    TextView yqxTv;
    @Bind(R.id.yqx_img)
    ImageView yqxImg;
    @Bind(R.id.yqx_ll)
    LinearLayout yqxLl;
    @Bind(R.id.grzx_rlv)
    RecyclerView grzxRlv;
    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    private List<String> list=new ArrayList<String>();
    private String[] leixing={"手机","电脑"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wddingdan);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
//                    3.创建数据源
        for (int i = 0; i <=leixing.length ; i++) {
            list.add(leixing[i]);
        }
//        创建Adapter 对象
        WoDeDingDanAdapter dingDanAdapter=new WoDeDingDanAdapter(this,list);
        dingDanAdapter.setClickListener(new WoDeDingDanAdapter.onChongXinFaBuClickListener() {
            @Override
            public void onChongXinClick(View view, int pos) {
                Y.t("重新发");
            }

            @Override
            public void onChaKanXiangQingClick(View view, int pos) {
                Y.t("查看详情");
            }

            @Override
            public void onQuXiaoDingDanClick(View view, int pos) {
                Y.t("取消订单");

            }
        });
        //8.recyclerview创建动画
        grzxRlv.setItemAnimator(new DefaultItemAnimator());
        //9. recyclerview布局管理器

        grzxRlv.setLayoutManager(new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,false));
        //10.添加适配器
        grzxRlv.setAdapter(dingDanAdapter);


    }

    @OnClick({R.id.wwc_ll, R.id.ywc_ll, R.id.yqx_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wwc_ll://未完成
                DingDanZhuangTai();
                //                未完成选中颜色值
                wwcTv.setTextColor(Color.parseColor("#03cccc"));
                wwcImg.setBackgroundColor(Color.parseColor("#03cccc"));
                //                已完成选中颜色值
                ywcTv.setTextColor(Color.parseColor("#757575"));
                ywcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已取消选中颜色值
                yqxTv.setTextColor(Color.parseColor("#757575"));
                yqxImg.setBackgroundColor(Color.parseColor("#bababa"));

                break;
            case R.id.ywc_ll://已完成
                DingDanZhuangTai();
                //                未完成选中颜色值
                wwcTv.setTextColor(Color.parseColor("#757575"));
                wwcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已完成选中颜色值
                ywcTv.setTextColor(Color.parseColor("#03cccc"));
                ywcImg.setBackgroundColor(Color.parseColor("#03cccc"));
                //                已取消选中颜色值
                yqxTv.setTextColor(Color.parseColor("#757575"));
                yqxImg.setBackgroundColor(Color.parseColor("#bababa"));
                break;
            case R.id.yqx_ll://已取消
                DingDanZhuangTai();
                //                未完成选中颜色值
                wwcTv.setTextColor(Color.parseColor("#757575"));
                wwcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已完成选中颜色值
                ywcTv.setTextColor(Color.parseColor("#757575"));
                ywcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已取消选中颜色值
                yqxTv.setTextColor(Color.parseColor("#03cccc"));
                yqxImg.setBackgroundColor(Color.parseColor("#03cccc"));
                break;
        }
    }
    public void DingDanZhuangTai(){
        switch (getIntent().getStringExtra("zhuangtai")){
            case "1":
//未完成
                //                未完成选中颜色值
                wwcTv.setTextColor(Color.parseColor("#03cccc"));
                wwcImg.setBackgroundColor(Color.parseColor("#03cccc"));
                //                已完成选中颜色值
                ywcTv.setTextColor(Color.parseColor("#757575"));
                ywcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已取消选中颜色值
                yqxTv.setTextColor(Color.parseColor("#757575"));
                yqxImg.setBackgroundColor(Color.parseColor("#bababa"));
                break;
            case "2":
 //已完成
                //                未完成选中颜色值
                wwcTv.setTextColor(Color.parseColor("#757575"));
                wwcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已完成选中颜色值
                ywcTv.setTextColor(Color.parseColor("#03cccc"));
                ywcImg.setBackgroundColor(Color.parseColor("#03cccc"));
                //                已取消选中颜色值
                yqxTv.setTextColor(Color.parseColor("#757575"));
                yqxImg.setBackgroundColor(Color.parseColor("#bababa"));
                break;
            case "3":
//已取消
                //                未完成选中颜色值
                wwcTv.setTextColor(Color.parseColor("#757575"));
                wwcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已完成选中颜色值
                ywcTv.setTextColor(Color.parseColor("#757575"));
                ywcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已取消选中颜色值
                yqxTv.setTextColor(Color.parseColor("#03cccc"));
                yqxImg.setBackgroundColor(Color.parseColor("#03cccc"));
                break;

        }
    }
}