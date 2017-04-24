package com.zykj.yixiu.app.activity.activity.grzx_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class YiXiuGeZiXunActivity extends BaseActivity {

    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.biaoti_tu_img)
    ImageView biaotiTuImg;
    @Bind(R.id.biaoyu_tv)
    TextView biaoyuTv;
    @Bind(R.id.diyitiao_xinwen_ll)
    LinearLayout diyitiaoXinwenLl;
    @Bind(R.id.diertiao_xinwen_ll)
    LinearLayout diertiaoXinwenLl;
    @Bind(R.id.disantiao_xinwen_ll)
    LinearLayout disantiaoXinwenLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl_yxgzx);
        ButterKnife.bind(this);
        biaoti.setOnLeftClickListener(new MyTopBer.OnLeftClickListener() {
            @Override
            public void onLeftClickListener() {
                finish();
            }
        });
    }

    @OnClick({R.id.diyitiao_xinwen_ll, R.id.diertiao_xinwen_ll, R.id.disantiao_xinwen_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.diyitiao_xinwen_ll:
                Y.t("暂未开通");
                break;
            case R.id.diertiao_xinwen_ll:
                Y.t("都说了还点");
                break;
            case R.id.disantiao_xinwen_ll:
                Y.t("你是不是脑袋进水了");
                break;
        }
    }
}
