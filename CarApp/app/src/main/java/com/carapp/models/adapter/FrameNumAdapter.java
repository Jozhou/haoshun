package com.carapp.models.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carapp.R;
import com.carapp.models.entry.NameValueEntry;
import com.corelibrary.utils.DeviceUtils;

import java.util.List;

public class FrameNumAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<NameValueEntry> mData;
	private Context mContext;

	public FrameNumAdapter(Context context) {
		this.mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public FrameNumAdapter(Context context, List<NameValueEntry> data) {
		this.mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mData = data;
	}

	public void setData(List<NameValueEntry> data) {
		this.mData = data;
	}

	@Override
	public int getCount() {
		if (mData == null) {
			return 0;
		}
		return mData.size();
	}

	@Override
	public NameValueEntry getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_frame_num, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvValue = (TextView) convertView.findViewById(R.id.tv_value);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final NameValueEntry entry = getItem(position);
		holder.tvName.setText(entry.name);
		holder.tvValue.setText(entry.value);
		return convertView;
	}

	static class ViewHolder {
		TextView tvName;
		TextView tvValue;
	}

}
