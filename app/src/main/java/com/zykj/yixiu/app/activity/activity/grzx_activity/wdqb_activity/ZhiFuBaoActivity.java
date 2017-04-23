package com.zykj.yixiu.app.activity.activity.grzx_activity.wdqb_activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/23.
 */

public class ZhiFuBaoActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.zhifubaozhanghao_et)
    EditText zhifubaozhanghaoEt;
    @Bind(R.id.zhifubaoname_et)
    EditText zhifubaonameEt;
    @Bind(R.id.but_queren)
    FrameLayout butQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdqb_zfb);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }

    @OnClick(R.id.but_queren)
    public void onViewClicked() {
    }
}
