package com.zykj.yixiu.app.activity.activity_styles;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;

/**
 * Created by zykj on 2017/4/11.
 */

public class MyTopBer extends RelativeLayout {
//    自定义成员属性  左侧
    private String leftText;
    private float leftTextSizc;
    private Drawable leftGB;
    //    自定义成员属性  居中
    private String titleText;
    private float titleTextSizc;
    private int titleGB;
    //    自定义成员属性  右侧
    private String rightText;
    private float rightTextSizc;
    private int rightGB;

    private TextView lefe;
    private TextView title;
    private TextView rightview;
//    自定义点击事件-------------------------------------------------------
//    左侧回调事件函数
    private OnLeftClickListener onLeftClickListener;
    public interface OnLeftClickListener {
        public void onLeftClickListener();
    }
public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
    this.onLeftClickListener = onLeftClickListener;
}
//    右侧回调事件函数---------------------------------------------
    private OnRightClickListener onRightClickListener;
    public interface OnRightClickListener{
        public void onRightClickListener();
    }
    public void setOnRightClickListener(OnRightClickListener onRightClickListener){
        this.onRightClickListener=onRightClickListener;
    }


    //    初始化构造函数
    public MyTopBer(Context context) {
        super(context);
    }

    public MyTopBer(Context context, AttributeSet attrs) {
        super(context, attrs);
//        获取自定义控件属性
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.MyTopBer);
//        左侧
        leftText=ta.getString(R.styleable.MyTopBer_leftText);
        leftTextSizc=ta.getDimension(R.styleable.MyTopBer_leftTextSizc,0);
        leftGB=ta.getDrawable(R.styleable.MyTopBer_leftGB);
//        居中

        titleText=ta.getString(R.styleable.MyTopBer_titleText);
        titleTextSizc=ta.getDimension(R.styleable.MyTopBer_titleTextSizc,0);
        titleGB=ta.getColor(R.styleable.MyTopBer_titleGB,0);
//        右侧
        rightText=ta.getString(R.styleable.MyTopBer_rightText);
        rightTextSizc=ta.getDimension(R.styleable.MyTopBer_rightTextSizc,0);
        rightGB=ta.getColor(R.styleable.MyTopBer_rightGB,0);
        //      关闭资源
        ta.recycle();
//      创建控件
//        左侧按钮控件TextView
        lefe = new TextView(context);

//        居中TextView
        title = new TextView(context);
//        右侧图片按钮
        rightview=new TextView(context);
//        左侧按钮点击事件
        lefe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftClickListener.onLeftClickListener();
            }
        });
//        右侧点击事件
        rightview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightClickListener.onRightClickListener();
            }
        });

//        赋值属性
        lefe.setText(leftText);
        lefe.setTextSize(leftTextSizc);
        lefe.setBackgroundDrawable(leftGB);

//        居中
        title.setText(titleText);
        title.setTextSize(titleTextSizc);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(titleGB);
//        右侧图片按钮
        rightview.setText(rightText);
        rightview.setTextSize(rightTextSizc);
        rightview.setGravity(Gravity.CENTER_VERTICAL);
        rightview.setTextColor(rightGB);
//        把控件绑定到RL
//        绑定左侧按钮
        LayoutParams leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(ALIGN_PARENT_LEFT);
        addView(lefe,leftParams);
//        绑定居中TextView
        LayoutParams titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(CENTER_IN_PARENT);
        addView(title,titleParams);

//        绑定右侧图片按钮
        LayoutParams rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(ALIGN_PARENT_RIGHT);
        addView(rightview,rightParams);

    }

    public MyTopBer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
