package com.zykj.yixiu.app.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/8.
 */

public class MainActivity extends BaseActivity {
    @Bind(R.id.xianshi_chrngshi_tv)
    TextView xianshiChrngshiTv;
    @Bind(R.id.xuanze_chrngshi_ll)
    LinearLayout xuanzeChrngshiLl;
    @Bind(R.id.grzx_img)
    ImageView grzxImg;
    @Bind(R.id.banner_main)
    Banner bannerMain;
    @Bind(R.id.shoujiweixiu_ll)
    LinearLayout shoujiweixiuLl;
    @Bind(R.id.diannaoweixiu_ll)
    LinearLayout diannaoweixiuLl;
    @Bind(R.id.jiadianweixiu_ll)
    LinearLayout jiadianweixiuLl;
    private int[] bannd = {R.mipmap.bannder_a, R.mipmap.bannder_b, R.mipmap.bannder_c, R.mipmap.bannder_d};
    private List list = new ArrayList();
    private ArrayList<String> citys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for (int i = 0; i < bannd.length; i++) {
            list.add(bannd[i]);
        }
//        设置banner样式
        bannerMain.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//        设置图片加载器
        bannerMain.setImageLoader(new GlideImageLoader());
//        设置图片集合
        bannerMain.setImages(list);
//        设置banner动画效果
        bannerMain.setBannerAnimation(Transformer.DepthPage);
//        设置自动轮播
        bannerMain.isAutoPlay(true);
//        轮播时间
        bannerMain.setDelayTime(2500);
//        设置指示器位置
        bannerMain.setIndicatorGravity(BannerConfig.CENTER);
//        设置方法全部完毕时调用
        bannerMain.start();
    }
    @OnClick({R.id.xuanze_chrngshi_ll, R.id.grzx_img, R.id.shoujiweixiu_ll, R.id.diannaoweixiu_ll, R.id.jiadianweixiu_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xuanze_chrngshi_ll:
                citys = new ArrayList<String>();
                citys.add("哈尔滨");
                citys.add("齐齐哈尔");
                citys.add("牡丹江");
                citys.add("佳木斯");
                citys.add("大庆");
                citys.add("鹤岗");
                citys.add("鸡西");
                citys.add("双鸭山");
                citys.add("伊春");
                citys.add("七台河");
                citys.add("黑河");
                citys.add("绥化");
                citys.add("大兴安岭地区");
                OptionsPickerView view1=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        xianshiChrngshiTv.setText(citys.get(options1));
                    }
                }).build();
                view1.setPicker(citys);
                view1.show();
                break;
            case R.id.grzx_img:
                Intent intentgrzx = new Intent(this, PhoneWeiXiuActivity.class);
                startActivity(intentgrzx);
                break;
            case R.id.shoujiweixiu_ll:
                Intent intent = new Intent(this, PhoneWeiXiuActivity.class);
                startActivity(intent);
                break;
            case R.id.diannaoweixiu_ll:
                Intent intent1 = new Intent(this, DianNaoWeiXiu.class);
                startActivity(intent1);
                break;
            case R.id.jiadianweixiu_ll:
                Intent intent2 = new Intent(this, JiaDianWeiXiuActivity.class);
                startActivity(intent2);
                break;
        }
    }
    //    重写图片加载器
    public class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView);


        }
    }
}


