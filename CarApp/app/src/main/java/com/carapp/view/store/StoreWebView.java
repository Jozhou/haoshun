package com.carapp.view.store;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.carapp.R;
import com.carapp.activity.ConversationQuerylActivity;
import com.carapp.models.entry.NewsEntry;
import com.carapp.models.entry.StoreEntry;
import com.carapp.models.operater.NewsCollectOperater;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.ButtonUtils;
import com.corelibrary.utils.DialogUtils;
import com.corelibrary.view.WebView;

public class StoreWebView extends WebView implements View.OnClickListener {

	private TextView tvOrder;
	private TextView tvQuery;

	private StoreEntry storeEntry;

	public StoreWebView(Context context) {
		super(context);
	}

	public StoreWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public StoreWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.view_webview_store;
	}

	public void setStoreEntry(StoreEntry storeEntry) {
		this.storeEntry = storeEntry;
	}

	@Override
	protected void onFindView() {
		super.onFindView();
		tvOrder = (TextView) findViewById(R.id.tv_order);
		tvQuery = (TextView) findViewById(R.id.tv_conversation_query);
		tvOrder.setOnClickListener(this);
		tvQuery.setOnClickListener(this);
	}

	@Override
	protected void onApplyData() {
		super.onApplyData();
	}

	@Override
	public void onClick(View v) {
		if (ButtonUtils.isFastDoubleClick()) {
			return;
		}
		int id = v.getId();
		if (id == R.id.tv_order) {

		} else if (id == R.id.tv_conversation_query) {
			Intent intent = new Intent(mContext, ConversationQuerylActivity.class);
			mContext.startActivity(intent);
		}
	}
}