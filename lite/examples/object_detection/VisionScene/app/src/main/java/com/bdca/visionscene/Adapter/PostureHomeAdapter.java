package com.bdca.visionscene.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bdca.visionscene.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 运动动作列表
 */
public class PostureHomeAdapter extends RecyclerView.Adapter<PostureHomeAdapter.ViewHolder> {
    private List<Integer> mList = new ArrayList<>();
    private Context mContext;
    private MyOrderAdapterFunc func;

    public PostureHomeAdapter(Context context, List<Integer> mList, MyOrderAdapterFunc func) {
        this.mList = mList;
        mContext = context;
        this.func = func;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.iv_home_img.setImageResource(mList.get(position));
    }

    public void setData(List<Integer> mList, boolean isAdd) {
        if (isAdd) {
            mList.addAll(mList);
        } else {
            this.mList = mList;
        }
        notifyDataSetChanged();
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
        ImageView iv_home_img;

        ViewHolder(View view) {
            super(view);
            iv_home_img = view.findViewById(R.id.iv_home_img);
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
