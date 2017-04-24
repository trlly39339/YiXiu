package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.adapters.ChangJianWenTiAdapter;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/24.
 */

public class ChangJianWenTiActivity extends BaseActivity {


    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.rlv)
    RecyclerView rlv;

    private List<String> list=new ArrayList<String>();;
    private String[] weixiuneirong={"NOKIA的开机后反复白屏重启，或者某个功能能进不去等。这一类的故障是软件的问题。国产机MTK系列，不断提示充电连接，充电器移除室COU虚焊。","NOKIA的开机后反复白屏重启，或者某个功能能进不去等。这一类的故障是软件的问题。国产机MTK系列，不断提示充电连接，充电器移除室COU虚焊。"};
    private String[] weixiufangfa={"软件故障就刷机，硬件故障就修硬件。用电源夹上看手机电流。","软件故障就刷机，硬件故障就修硬件。用电源夹上看手机电流。"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_cjwt);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
        for (int i = 0; i < weixiuneirong.length; i++) {
            list.add(weixiuneirong[i]);
            list.add(weixiufangfa[i]);
        }
        ChangJianWenTiAdapter adapter=new ChangJianWenTiAdapter(list,this);
        rlv.setItemAnimator(new DefaultItemAnimator());
        rlv.setLayoutManager(new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,false ));;
        rlv.setAdapter(adapter);

    }
}
