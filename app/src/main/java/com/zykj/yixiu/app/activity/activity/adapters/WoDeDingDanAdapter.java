package com.zykj.yixiu.app.activity.activity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.bean.ChaXunDingDanBean;
import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */

public class WoDeDingDanAdapter extends BaseAdapter{

    private Context context;//定义上下文
    private List<ChaXunDingDanBean> list;//数据源
//    getList
    public List<ChaXunDingDanBean> getList() {
        return list;
    }
//    setList
    public void setList(List<ChaXunDingDanBean> list) {
        this.list = list;
    }
//    初始化构造函数
    public WoDeDingDanAdapter(Context context, List<ChaXunDingDanBean> list) {
        this.context = context;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
//            等待接待
        holder.ddjd= (TextView) convertView.findViewById(R.id.ddjd_tv);
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
        ChaXunDingDanBean chaXunDingDanBean = list.get(position);
//        订单状态
        int zhuangtai = chaXunDingDanBean.getOrder_state();
//        订单类型
        int leixing = chaXunDingDanBean.getOrder_type();
        //刚刚下的单  或者未完成-------------------------------------------------
        if ("1".equals(zhuangtai)||"4".equals(leixing)||"5".equals(leixing)||"6".equals(leixing)){

            if ("1".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("手机");
//                已接单
                holder.yjd.setVisibility(View.VISIBLE);
//                等待接单
                holder.ddjd.setVisibility(View.GONE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp,YURL.HOST+chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
            if ("2".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("电脑");
//                已接单
                holder.yjd.setVisibility(View.GONE);
//                等待接单
                holder.ddjd.setVisibility(View.VISIBLE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.VISIBLE);
//                时间
                holder.shijian.setVisibility(View.VISIBLE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp, chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
            if ("3".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("家电");
//                已接单
                holder.yjd.setVisibility(View.GONE);
//                等待接单
                holder.ddjd.setVisibility(View.VISIBLE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.VISIBLE);
//                时间
                holder.shijian.setVisibility(View.VISIBLE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp,chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
        }
//        ----------------------------------------------------------------
//        已完成状态
        if ("2".equals(zhuangtai)){
            holder.quxiaodingdan.setText("删除订单");
            if ("1".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("手机");
//                已接单
                holder.yjd.setVisibility(View.GONE);
//                等待接单
                holder.ddjd.setVisibility(View.GONE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp,YURL.HOST+chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
            if ("2".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("电脑");
//                已接单
                holder.yjd.setVisibility(View.GONE);
//                等待接单
                holder.ddjd.setVisibility(View.GONE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp, chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
            if ("3".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("家电");
//                已接单
                holder.yjd.setVisibility(View.GONE);
//                等待接单
                holder.ddjd.setVisibility(View.GONE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp,chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
        }
//        ----------------------------------------------------------------
//        已取消状态
        if ("3".equals(zhuangtai)){
            holder.quxiaodingdan.setText("删除订单");
            if ("1".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("手机");
//                已接单
                holder.yjd.setVisibility(View.GONE);
//                等待接单
                holder.ddjd.setVisibility(View.GONE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp,YURL.HOST+chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
            if ("2".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("电脑");
//                已接单
                holder.yjd.setVisibility(View.GONE);
//                等待接单
                holder.ddjd.setVisibility(View.GONE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp, chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
            if ("3".equals(leixing)){//1为手机 刚刚下的的 等待接单中
//                种类
                holder.zhonglei.setText("家电");
//                已接单
                holder.yjd.setVisibility(View.GONE);
//                等待接单
                holder.ddjd.setVisibility(View.GONE);
//                重新发布
                holder.chongxinfabu.setVisibility(View.GONE);
//                时间
                holder.shijian.setVisibility(View.GONE);
//                下单时间
                holder.xiadanshijian.setText(chaXunDingDanBean.getAddtime());
//                取消订单
                holder.xiadandizhi.setText(chaXunDingDanBean.getService_address());
//                添加图片
                x.image().bind(holder.tp,chaXunDingDanBean.getImage1());
//                holder.tp.set(chaXunDingDanBean.getImage1()+"");
            }
        }
        return convertView;
    }


    class ViewHolder{

        FrameLayout chongxinfabu;
        TextView zhonglei,yjd,ddjd,xiadanshijian,xiadandizhi,shijian,chakanxiangqing,quxiaodingdan;
        ImageView tp;



    }
}
