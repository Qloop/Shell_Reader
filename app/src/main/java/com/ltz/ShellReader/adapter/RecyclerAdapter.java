package com.ltz.ShellReader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

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

		public LinearLayout mLayout;
		public TextView tvTitle;
		public TextView tvClassify;
		public TextView tvNum;
		public TextView tvDetail;
		public ImageView ivPic;

		public ViewHolder(View itemView) {
			super(itemView);
			mLayout = (LinearLayout) itemView;
			mLayout.setOnClickListener(this);
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
//		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_detail_listview, parent, false);
		View view = null;
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
//		ArticleList.ArticleInfo articleInfo = (ArticleList.ArticleInfo) mData.get(position);

//		holder.tvTitle.setText(articleInfo.getTitle());

	}

	@Override
	public int getItemCount() {
		return mData.size();
	}
}
