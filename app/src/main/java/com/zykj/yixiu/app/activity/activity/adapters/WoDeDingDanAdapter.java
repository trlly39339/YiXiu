package com.zykj.yixiu.app.activity.activity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity.grzx_activity.DingDanXiangQingActivity;
import com.zykj.yixiu.app.activity.bean.ChaXunDingDanBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/23.
 */

public class WoDeDingDanAdapter extends BaseAdapter{

    private Context context;//定义上下文
    private List<ChaXunDingDanBean> list;//数据源
    private int index=-1;
    private ChaXunDingDanBean chaXunDingDanBean;

    //    getList
    public List<ChaXunDingDanBean> getList() {
        return list;
    }
//    setList
    public void setList(List<ChaXunDingDanBean> list) {
        this.list = list;
    }
//    初始化构造函数
    public WoDeDingDanAdapter(Context context, List<ChaXunDingDanBean> list,int index) {
        this.context = context;
        this.list = list;
        this.index = index;
    }
//    数据长度
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context,R.layout.grzx_wdedingdan_item,null);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //            种类
        holder.zhonglei= (TextView) convertView.findViewById(R.id.zhonglei_tv);
//            已接单
        holder.yjd= (TextView) convertView.findViewById(R.id.yjd_tv);

//            下单时间
        holder .xiadanshijian= (TextView) convertView.findViewById(R.id.xiadan_shijian_tv);
//            下单地址
        holder .xiadandizhi= (TextView) convertView.findViewById(R.id.xiadan_dizhi_tv);
//            时间
        holder.shijian= (TextView) convertView.findViewById(R.id.shijian_tv);
//            重新发布
        holder .chongxinfabu= (FrameLayout) convertView.findViewById(R.id.chongxin_fabu_tv);
//            查看详情
        holder.chakanxiangqing= (TextView) convertView.findViewById(R.id.chakan_xiangqin_tv);
//            取消订单
        holder.quxiaodingdan= (TextView) convertView.findViewById(R.id.quxiao_dingdan_tv);
//            图片
        holder.tp= (ImageView) convertView.findViewById(R.id.tp_img);
/**
 * model :
 * engineer_id :
 * order_state : 1
 * class :
 * service_address :
 * fault :
 * custom_id : 2
 * custom_name :
 * id : 1
 * service_time :
 * addtime :
 * image2 :
 * price :
 * image1 :
 * pay_type :
 * address_id :
 * isdel : 0
 * brand :
 * fault_desc :
 * image3 :
 * custom_phone :
 * order_type : 1
 * region : 香坊区
 * lat : 45.111
 * lon : 经度
 * city_name : 哈尔滨市
 * city_code : 48
 */
        chaXunDingDanBean = list.get(position);
        int order_type = chaXunDingDanBean.getOrder_type();
        final int order_state = chaXunDingDanBean.getOrder_state();
        Y.i( chaXunDingDanBean.getId()+"");
        switch (order_state){
            //订单状态:1,4,5,6为未完成,2为已完成,3为已取消//1刚发布的订单 ,4确认订单,5已支付,6已接单
            case 1:
                holder.yjd.setText("刚发布的订单");
                //                重新发布
                holder.chongxinfabu.setVisibility(View.VISIBLE);
//                时间
                holder.shijian.setVisibility(View.VISIBLE);

                break;
            case 2:
                holder.yjd.setText("已完成");
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
                break;
            case 3:
                holder.yjd.setText("已取消");
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
                break;
            case 4:
                holder.yjd.setText("确认订单");
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
                break;
            case 5:
                holder.yjd.setText("已支付");
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
                break;
            case 6:
                holder.yjd.setText("已接单");
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
                break;
        }

//        订单类型
        switch (order_type){
            case 1:
                holder.zhonglei.setText("手机"+ chaXunDingDanBean.getId());

                break;
            case 2:
                holder.zhonglei.setText("电脑"+ chaXunDingDanBean.getId());

                break;
            case 3:
                holder.zhonglei.setText("家电"+ chaXunDingDanBean.getId());
                break;
        }

        //                下单时间
        holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
        holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
        ImageOptions options = new ImageOptions.Builder().build();
        x.image().bind(holder.tp,YURL.HOST+ chaXunDingDanBean.getImage1(),options);

        holder.chakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        判断 取消 ==0或 删除==1
        if (index==0){
            holder.quxiaodingdan.setText("取消订单");
            holder.quxiaodingdan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CANCEL_ORDER
                    RequestParams map=new RequestParams(YURL.CANCEL_ORDER);
                    map.addBodyParameter("id", chaXunDingDanBean.getId()+"");
                    map.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    Y.post(map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                            Y.t("订单已取消");
                                list.remove(position);
                                notifyDataSetChanged();
                        }else {
                            Y.t("订单取消失败");
                            }
                        }
                    });
                }
            });
        }
        if (index==1){
            holder.quxiaodingdan.setText("删除订单");
            holder.quxiaodingdan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//delOrder
                    RequestParams map=new RequestParams(YURL.DEL_ORDER);
                    map.addBodyParameter("id", chaXunDingDanBean.getId()+"");
                    map.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    Y.post(map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                                Y.t("订单已删除");
                                list.remove(position);
                                notifyDataSetChanged();
                            }else {
                                Y.t("订单删除失败");
                            }
                        }
                    });
                }
            });
        }
//        查看详情
        holder.chakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (order_state){
                    case  1:
                        Intent intent1=new Intent(context, DingDanXiangQingActivity.class);
                        intent1.putExtra("chaXunDingDanBean", chaXunDingDanBean);
                        intent1.putExtra("zhuangtai","1");
                        context.startActivity(intent1);
                        break;
                    case  2:
                        Intent intent2=new Intent(context, DingDanXiangQingActivity.class);
                        intent2.putExtra("chaXunDingDanBean", chaXunDingDanBean);
                        intent2.putExtra("zhuangtai","2");
                        context.startActivity(intent2);
                        break;
                    case  3:
                        Intent intent3=new Intent(context, DingDanXiangQingActivity.class);
                        intent3.putExtra("chaXunDingDanBean", chaXunDingDanBean);
                        intent3.putExtra("zhuangtai","3");
                        context.startActivity(intent3);
                        break;
                }
            }
        });
        return convertView;
    }


    class ViewHolder{

        FrameLayout chongxinfabu;
        TextView zhonglei,yjd,xiadanshijian,xiadandizhi,shijian,chakanxiangqing,quxiaodingdan;
        ImageView tp;



    }
}
