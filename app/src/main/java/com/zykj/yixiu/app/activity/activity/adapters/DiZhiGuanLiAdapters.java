package com.zykj.yixiu.app.activity.activity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baidu.location.Address;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.bean.ChaXunAddress;

import java.util.List;

/**
 * Created by zykj on 2017/5/2.
 */

public class DiZhiGuanLiAdapters extends RecyclerView.Adapter<DiZhiGuanLiAdapters.VH>{
    private List<ChaXunAddress> list;
    private Context context;

//--------set   get  list----------------------------
    public List<ChaXunAddress> getList() {
        return list;
    }

    public void setList(List<ChaXunAddress> list) {
        this.list = list;
    }
    private setOnDiZhiClickLister clickLister;

    public setOnDiZhiClickLister getClickLister() {
        return clickLister;
    }

    public void setClickLister(setOnDiZhiClickLister clickLister) {
        this.clickLister = clickLister;
    }

    public interface setOnDiZhiClickLister{
        void ll_item_Click(View v,int ops);
        void bianji_Click(View v,int ops);
        void shanchu_Click(View v,int ops);
    }
//-------------------------
    public DiZhiGuanLiAdapters(List<ChaXunAddress> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.wdzl_dzgl_item, null);
        VH vh=new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        ChaXunAddress chaXunAddress = list.get(position);
        holder.name.setText(chaXunAddress.getName());
        holder.phone.setText(chaXunAddress.getPhone());
        holder.dizhi.setText(chaXunAddress.getAddress());
        /**
         * 判断是否为默认
         */
        if (chaXunAddress.getIsdefault()==1){
            holder.moren.setChecked(true);
            notifyDataSetChanged();
        }else {
            holder.moren.setChecked(false);
            notifyDataSetChanged();
        }
        /**
         * item跳转页面
         */
        holder.ll_item.setTag(chaXunAddress.getAddress_id());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLister.ll_item_Click(holder.ll_item,position);
            }
        });
        /**
         * 编辑事件
         */
        holder.bianji.setTag(chaXunAddress.getAddress_id());
        holder.bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLister.bianji_Click(holder.bianji,position);
            }
        });
        /**
         * 删除事件
         */
        holder.shanchu.setTag(chaXunAddress.getAddress_id());//捆绑ID地址
        holder.shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLister.shanchu_Click(holder.shanchu,position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder{
        LinearLayout ll_item;
        TextView name,phone,dizhi,bianji,shanchu;
        RadioButton moren;

        public VH(View itemView) {
            super(itemView);
            ll_item= (LinearLayout) itemView.findViewById(R.id.ll_item);
            moren= (RadioButton) itemView.findViewById(R.id.moren_rb);
            name= (TextView) itemView.findViewById(R.id.name_tv);
            phone= (TextView) itemView.findViewById(R.id.phone_tv);
            dizhi= (TextView) itemView.findViewById(R.id.dizhi_tv);
            bianji= (TextView) itemView.findViewById(R.id.bianji_tv);
            shanchu= (TextView) itemView.findViewById(R.id.shanchu_tv);
        }
    }
}
