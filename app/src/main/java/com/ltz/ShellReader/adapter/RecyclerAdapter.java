package com.ltz.ShellReader.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltz.ShellReader.R;

import java.util.List;
import java.util.Map;

/**
 * RecyclerView适配器
 * Created by Qloop on 2016/9/6.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<?> mData;

    public RecyclerAdapter(List<?> data, Context context) {
        mData = data;
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView mCardView;
        public TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, Object> articleInfo = (Map<String, Object>) mData.get(position);
        holder.tvTitle.setText(articleInfo.get("unit").toString());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
