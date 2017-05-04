package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
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
    MyTopBer biaoti;//返回键
    @Bind(R.id.xzdz_ll)
    LinearLayout xzdzLl;//新增地址
    @Bind(R.id.rlv)
    ListView rlv;//显示地址listview
    private DiZhiGuanLiAdapters adapters;
//    List<ChaXunAddress> list = new ArrayList<ChaXunAddress>();
    private ChaXunAddress address;

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

        rlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                address = adapters.getList().get(position);
                Intent intent=new Intent();
                intent.putExtra("address",address);
                setResult(110,intent);
                finish();
            }
        });

        adapters = new DiZhiGuanLiAdapters(null, DiZhiGuanLiActivity.this);
        rlv.setAdapter(adapters);//
//        adapters.setClickLister(new DiZhiGuanLiAdapters.setOnDiZhiClickLister() {
//            @Override
//            public void ll_item_Click(View v, int ops) {
//                Y.t("再点要钱了");
//            }
//
//            @Override
//            public void bianji_Click(View v, final int ops) {
//                Map map=new HashMap();
//                map.put("user_id", Y.USER.getUser_id()+"");
//                map.put("address_id",v.getTag()+"");
//                Y.get(YURL.ADD_ADDRESS, map, new Y.MyCommonCall<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        StyledDialog.dismissLoading();
//                        if (Y.getRespCode(result)){
//                            List<ChaXunAddress> lists = adapters.getList();
//                            for (int i = 0; i < lists.size() ; i++) {
//                                if (i==ops){
//                                    lists.get(ops).setIsdefault(1);
//                                }else {
//                                    lists.get(ops).setIsdefault(0);
//                                }
//                            }
//
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void shanchu_Click(View v, final int ops) {
//                Map map=new HashMap();
//                map.put("user_id", Y.USER.getUser_id()+"");
//                map.put("address_id",v.getTag()+"");
//                Y.get(YURL.ADD_ADDRESS, map, new Y.MyCommonCall<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        StyledDialog.dismissLoading();
//                        if (Y.getRespCode(result)){
//                            Y.t("删除成功");
//                            adapters.getList().remove(ops);
//
//                        }
//                    }
//                });
//
//            }
//        });
//        rlv.setItemAnimator(new DefaultItemAnimator());//
//        rlv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));//
//        rlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));//


    }

    @Override
    protected void onResume() {
        super.onResume();
        Map map=new HashMap();
        map.put("user_id", Y.USER.getUser_id()+"");
        Y.get(YURL.SELECT_ADDRESS, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    List<ChaXunAddress> userList = JSON.parseArray(Y.getData(result), ChaXunAddress.class);
                    adapters.setList(userList);
                }
            }
        });
    }

    @OnClick(R.id.xzdz_ll)
    public void onViewClicked() {
        startActivity(new Intent(DiZhiGuanLiActivity.this, BianJiDiZhiActivity.class));
    }
}
