/*
 * Copyright (C) 2011 Patrik �kerfeldt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carapp.models.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.carapp.R;

public class AdAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private static final int[] ids = { R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder,
			R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder };

	public AdAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (ids == null) {
			return 0;
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public Integer getItem(int position) {
		return ids[position % ids.length];
	}

	@Override
	public long getItemId(int position) {
		return position % ids.length;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_ad, null);
		}
		((ImageView) convertView.findViewById(R.id.iv_ad)).setImageResource(getItem(position));
		return convertView;
	}

}
