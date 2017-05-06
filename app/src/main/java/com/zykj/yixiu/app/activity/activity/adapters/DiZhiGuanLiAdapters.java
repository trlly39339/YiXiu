package com.zykj.yixiu.app.activity.activity.adapters;

        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.LinearLayout;
        import android.widget.RadioButton;
        import android.widget.TextView;

        import com.hss01248.dialog.StyledDialog;
        import com.zykj.yixiu.R;
        import com.zykj.yixiu.app.activity.bean.ChaXunAddress;
        import com.zykj.yixiu.app.activity.yixiuge_utils.Y;
        import com.zykj.yixiu.app.activity.yixiuge_utils.YURL;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import butterknife.Bind;
        import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/5/2.
 */

public class DiZhiGuanLiAdapters extends BaseAdapter {
    private List<ChaXunAddress> list;
    private Context context;

    public List<ChaXunAddress> getList() {
        return list;
    }

    public void setList(List<ChaXunAddress> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public DiZhiGuanLiAdapters(List<ChaXunAddress> list, Context context) {
        this.list = list;
        this.context = context;
    }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder =new ViewHolder();
            convertView = View.inflate(context, R.layout.wdzl_dzgl_item, null);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.bianjiTv= (TextView) convertView.findViewById(R.id.bianji_tv);
        holder.shanchuTv= (TextView) convertView.findViewById(R.id.shanchu_tv);
        holder.dizhiTv= (TextView) convertView.findViewById(R.id.dizhi_tv);
        holder.nameTv= (TextView) convertView.findViewById(R.id.name_tv);
        holder.phoneTv= (TextView) convertView.findViewById(R.id.phone_tv);
        holder.morenRb= (RadioButton) convertView.findViewById(R.id.moren_rb);
        holder.llItem= (LinearLayout) convertView.findViewById(R.id.ll_item);

        ChaXunAddress address = list.get(position);
        holder.nameTv.setText(address.getName());
        holder.phoneTv.setText(address.getPhone());
        holder.dizhiTv.setText(address.getAddress());
//        选择默认事件
        holder.morenRb.setTag(address.getAddress_id());
        holder.morenRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map=new HashMap();
                map.put("user_id",Y.USER.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                Y.get(YURL.DEF_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("设置成功");
                            for (int i = 0; i <list.size() ; i++) {
                                if (i==position){//需要勾选的位置
                                    list.get(position).setIsdefault(1);
                                }else {//取消勾选
                                    list.get(position).setIsdefault(0);
                                }
                            }
                            notifyDataSetChanged();//刷新列表
                        }
                    }
                });
            }
        });
        //        检测默认
        if (address.getIsdefault()==1){//勾选
            holder.morenRb.setChecked(true);
        }else {//不勾选
            holder.morenRb.setChecked(false);
        }

//        删除
        holder.shanchuTv.setTag(address.getAddress_id());//
        holder.shanchuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map=new HashMap();
                map.put("user_id", Y.USER.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                Y.get(YURL.ADD_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("删除成功");
                            list.remove(position);
                            notifyDataSetChanged();
                        }

                    }
                });
            }
        });
        return convertView;
    }
}
class ViewHolder {

    TextView nameTv,phoneTv,dizhiTv,bianjiTv,shanchuTv;
    RadioButton morenRb;
    LinearLayout llItem;




}
