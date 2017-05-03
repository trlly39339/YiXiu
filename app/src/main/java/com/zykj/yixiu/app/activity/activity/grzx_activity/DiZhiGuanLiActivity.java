package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.BianJiDiZhiActivity;
import com.zykj.yixiu.app.activity.activity.adapters.DiZhiGuanLiAdapters;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.ChaXunAddress;
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
 * Created by zykj on 2017/4/25.
 */

public class DiZhiGuanLiActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.rlv)
    RecyclerView rlv;
    @Bind(R.id.xzdz_ll)
    LinearLayout xzdzLl;
    List<ChaXunAddress> list=new ArrayList<ChaXunAddress>();
    private DiZhiGuanLiAdapters adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_dlgl);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
        ChaXunAddress chaXunAddress=new ChaXunAddress();
        list.add(chaXunAddress);
        adapters = new DiZhiGuanLiAdapters(list,DiZhiGuanLiActivity.this);
        adapters.setClickLister(new DiZhiGuanLiAdapters.setOnDiZhiClickLister() {
            @Override
            public void ll_item_Click(View v, int ops) {

            }

            @Override
            public void bianji_Click(View v, final int ops) {
                Map map=new HashMap();
                map.put("user_id", Y.USER.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                Y.get(YURL.ADD_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            List<ChaXunAddress> lists = adapters.getList();
                            for (int i = 0; i < lists.size() ; i++) {
                                if (i==ops){
                                    lists.get(ops).setIsdefault(1);
                                }else {
                                    lists.get(ops).setIsdefault(0);
                                }
                            }

                        }
                    }
                });
            }

            @Override
            public void shanchu_Click(View v, final int ops) {
                Map map=new HashMap();
                map.put("user_id", Y.USER.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                Y.get(YURL.ADD_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("删除成功");
                            adapters.getList().remove(ops);

                        }
                    }
                });

            }
        });
        rlv.setItemAnimator(new DefaultItemAnimator());//
        rlv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));//
        rlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));//
        rlv.setAdapter(adapters);//



    }

    @OnClick(R.id.xzdz_ll)
    public void onViewClicked() {
        startActivity(new Intent(DiZhiGuanLiActivity.this,BianJiDiZhiActivity.class));
    }
}
