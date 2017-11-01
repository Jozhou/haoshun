package com.carapp.fragment;

import android.content.Intent;
import android.view.View;

import com.carapp.R;
import com.carapp.activity.ActivityWeb;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.StoreEntry;
import com.carapp.view.store.StoreListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.corelibrary.fragment.base.BaseFragment;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.decoration.SimpleListItemDecoration;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MyStoreFragment extends BaseFragment {

    @ViewInject("rv_all")
    private StoreListView rvAll;

    @Override
    protected int getContentResId() {
        return R.layout.fragment_my_store;
    }

    @Override
    protected void onBindListener() {
        super.onBindListener();

        rvAll.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreEntry entry = (StoreEntry) adapter.getData().get(position);
                Intent intent = new Intent(mContext, ActivityWeb.class);
                intent.putExtra(IntentCode.INTENT_WEB_URL, entry.url);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        rvAll.addItemDecoration(new SimpleListItemDecoration(mContext, R.drawable.divider_latest_news));
        rvAll.setParams("117.201538", "39.085294", 0);
        rvAll.refresh();
    }
}
