package com.zykj.yixiu.app.activity.activity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.yixiu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */

public class WoDeDingDanAdapter extends RecyclerView.Adapter <WoDeDingDanAdapter.VH>{

    private Context context;//定义上下文
    private List<String> list;//数据源
    //    重新发布点击事件会调接口
    private onChongXinFaBuClickListener clickListener;//保存被点击的事件
    public interface onChongXinFaBuClickListener{
        void onChongXinClick(View view,int pos);//重新发布
        void onChaKanXiangQingClick(View view,int pos);//查看详情
        void onQuXiaoDingDanClick(View view,int pos);//取消订单
    }
//    set点击事件回调接口
    public void setClickListener(onChongXinFaBuClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //    重写构造函数
    public WoDeDingDanAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }


    @Override  //  创建ViewHolder
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.grzx_wdedingdan_item, null);
        VH vh=new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
//        ________________________________________________________________________
//        重新发布点击事件回传
        holder.chongxinfabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onChongXinClick(holder.chongxinfabu,position);
            }
        });
//        查看详情点击事件回传
        holder.chakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onChaKanXiangQingClick(holder.chakanxiangqing,position);
            }
        });
//        取消订单点击事件回传
        holder.quxiaodingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onQuXiaoDingDanClick(holder.quxiaodingdan,position);
            }
        });
//        __________________________________________________________________________
        holder.zhonglei.setText(list.get(position));
        if (holder.zhonglei.getText()=="手机"){
            holder.ddjd.setVisibility(View.GONE);
            holder.shijian.setVisibility(View.GONE);
            holder.chongxinfabu.setVisibility(View.GONE);
        }
        if (holder.zhonglei.getText()=="电脑"){
            holder.yjd.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder{

        FrameLayout chongxinfabu;
        TextView zhonglei,yjd,ddjd,xiadanshijian,xiadandizhi,shijian,chakanxiangqing,quxiaodingdan;
        ImageView tp;
        public VH(View itemView) {
            super(itemView);
//            种类
            zhonglei= (TextView) itemView.findViewById(R.id.zhonglei_tv);
//            已接单
            yjd= (TextView) itemView.findViewById(R.id.yjd_tv);
//            等待接待
            ddjd= (TextView) itemView.findViewById(R.id.ddjd_tv);
//            下单时间
            xiadanshijian= (TextView) itemView.findViewById(R.id.xiadan_shijian_tv);
//            下单地址
            xiadandizhi= (TextView) itemView.findViewById(R.id.xiadan_dizhi_tv);
//            时间
            shijian= (TextView) itemView.findViewById(R.id.shijian_tv);
//            重新发布
            chongxinfabu= (FrameLayout) itemView.findViewById(R.id.chongxin_fabu_tv);
//            查看详情
            chakanxiangqing= (TextView) itemView.findViewById(R.id.chakan_xiangqin_tv);
//            取消订单
            quxiaodingdan= (TextView) itemView.findViewById(R.id.quxiao_dingdan_tv);
//            图片
            tp= (ImageView) itemView.findViewById(R.id.tp_img);
        }
    }
}
