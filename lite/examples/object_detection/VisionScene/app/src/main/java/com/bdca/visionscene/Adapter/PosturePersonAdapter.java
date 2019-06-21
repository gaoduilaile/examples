package com.bdca.visionscene.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdca.visionscene.R;
import com.bdca.visionscene.bean.PostureAdapterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 运动动作列表
 */
public class PosturePersonAdapter extends RecyclerView.Adapter<PosturePersonAdapter.ViewHolder> {
    private List<PostureAdapterBean> mList = new ArrayList<>();
    private Context mContext;
    private MyOrderAdapterFunc func;

    public PosturePersonAdapter(Context context, List<PostureAdapterBean> mList, MyOrderAdapterFunc func) {
        this.mList = mList;
        mContext = context;
        this.func = func;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = null;
        switch (viewType) {
            case 0:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sportlist_present, null);
                break;
            case 1:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sportlist_child, null);
                break;
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PostureAdapterBean adapterBean = mList.get(position);
        switch (adapterBean.getType()) {
            case 0:
                holder.tv_child_name.setText(adapterBean.getName());
                holder.iv_child_icon.setImageResource(adapterBean.getIconChild());
                break;
            case 1:
                holder.tv_parent_name.setText(adapterBean.getName());
                holder.iv_patent_icon.setImageResource(adapterBean.getIconParent());
                break;
        }
    }

    public void setData(List<PostureAdapterBean> mList, boolean isAdd) {
        if (isAdd) {
            mList.addAll(mList);
        } else {
            this.mList = mList;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_child_name;
        ImageView iv_child_icon;
        TextView tv_parent_name;
        ImageView iv_patent_icon;
        ViewHolder(View view) {
            super(view);
            tv_child_name = view.findViewById(R.id.tv_child_name);
            iv_child_icon = view.findViewById(R.id.iv_child_icon);
            tv_parent_name = view.findViewById(R.id.tv_parent_name);
            iv_patent_icon = view.findViewById(R.id.iv_patent_icon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    func.itemClick(getLayoutPosition());
                }
            });
        }
    }

    public interface MyOrderAdapterFunc {
        void itemClick(int position);
    }

}
