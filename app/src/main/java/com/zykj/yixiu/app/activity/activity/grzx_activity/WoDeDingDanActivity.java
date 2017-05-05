package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.adapters.WoDeDingDanAdapter;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/23.
 */

public class WoDeDingDanActivity extends BaseActivity {


    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.wwc_tv)
    TextView wwcTv;
    @Bind(R.id.wwc_img)
    TextView wwcImg;
    @Bind(R.id.wwc_ll)
    LinearLayout wwcLl;
    @Bind(R.id.ywc_tv)
    TextView ywcTv;
    @Bind(R.id.ywc_img)
    TextView ywcImg;
    @Bind(R.id.ywc_ll)
    LinearLayout ywcLl;
    @Bind(R.id.yqx_tv)
    TextView yqxTv;
    @Bind(R.id.yqx_img)
    TextView yqxImg;
    @Bind(R.id.yqx_ll)
    LinearLayout yqxLl;
    @Bind(R.id.grzx_rlv)
    ListView grzxRlv;

//    private List<String> list = new ArrayList<String>();
//    private String[] leixing = {"手机", "电脑"};
    private String zhuangtai;
    private WoDeDingDanAdapter dingDanAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wddingdan);
        ButterKnife.bind(this);
//        返回按钮
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            zhuangtai = intent.getStringExtra("zhuangtai");
        }
//        未完成 已完成 已取消导航函数
        DingDanZhuangTai();


    }
    public void Lv(){
        //        FIND_ORDER_BYS_TATE
        Map map=new HashMap();
        map.put("custom_id",Y.USER.getUser_id()+"");
        map.put("order_state",zhuangtai);
        Y.get(YURL.FIND_ORDER_BYS_TATE, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    Y.i(result+"我item");
                    //        创建Adapter 对象
                    dingDanAdapter = new WoDeDingDanAdapter(WoDeDingDanActivity.this, null);
                    //10.添加适配器
                    grzxRlv.setAdapter(dingDanAdapter);
                }
            }
        });
    }

    public void DingDanZhuangTai() {
        switch (zhuangtai) {
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
                Lv();
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
                Lv();
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
                Lv();
                break;

        }
    }

    @OnClick({R.id.wwc_ll, R.id.ywc_ll, R.id.yqx_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wwc_ll:
                DingDanZhuangTai();

                wwcTv.setTextColor(Color.parseColor("#03cccc"));
                wwcImg.setBackgroundColor(Color.parseColor("#03cccc"));
                //                已完成选中颜色值
                ywcTv.setTextColor(Color.parseColor("#757575"));
                ywcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已取消选中颜色值
                yqxTv.setTextColor(Color.parseColor("#757575"));
                yqxImg.setBackgroundColor(Color.parseColor("#bababa"));
                break;
            case R.id.ywc_ll:
                DingDanZhuangTai();

                wwcTv.setTextColor(Color.parseColor("#757575"));
                wwcImg.setBackgroundColor(Color.parseColor("#bababa"));
                //                已完成选中颜色值
                ywcTv.setTextColor(Color.parseColor("#03cccc"));
                ywcImg.setBackgroundColor(Color.parseColor("#03cccc"));
                //                已取消选中颜色值
                yqxTv.setTextColor(Color.parseColor("#757575"));
                yqxImg.setBackgroundColor(Color.parseColor("#bababa"));
                break;
            case R.id.yqx_ll:
                DingDanZhuangTai();

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