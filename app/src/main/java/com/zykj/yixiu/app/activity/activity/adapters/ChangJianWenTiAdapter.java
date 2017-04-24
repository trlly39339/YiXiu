package com.zykj.yixiu.app.activity.activity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zykj.yixiu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class ChangJianWenTiAdapter extends RecyclerView.Adapter<ChangJianWenTiAdapter.ChangJianWenTiVH> {
    private Context context;
    private List<String> list ;

    public ChangJianWenTiAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ChangJianWenTiVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.wdzl_cjwt_item, null);
        ChangJianWenTiVH vh=new ChangJianWenTiVH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ChangJianWenTiVH holder, int position) {
        holder.chuangjianwentineirong.setText(list.get(position));
//        holder.weixiufangfa.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ChangJianWenTiVH extends RecyclerView.ViewHolder{
        TextView chuangjianwentineirong,weixiufangfa;

        public ChangJianWenTiVH(View itemView) {
            super(itemView);
            chuangjianwentineirong= (TextView) itemView.findViewById(R.id.wentineirong_tv);
            weixiufangfa= (TextView) itemView.findViewById(R.id.weixiufangfa_tv);
        }
    }
}
