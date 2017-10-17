package com.carapp.fragment;

import android.content.Intent;
import android.view.View;

import com.carapp.R;
import com.carapp.activity.ActivityWeb;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.NewsEntry;
import com.carapp.view.MainHeaderView;
import com.carapp.view.news.LatestNewsListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.corelibrary.fragment.base.BaseFragment;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.decoration.SimpleListItemDecoration;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MainFragment extends BaseFragment {

    @ViewInject("lv_news")
    private LatestNewsListView lvNews;

    private MainHeaderView mainHeaderView;

    @Override
    protected int getContentResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onBindListener() {
        super.onBindListener();
        lvNews.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsEntry entry = (NewsEntry) adapter.getData().get(position);
                Intent intent = new Intent(mContext, ActivityWeb.class);
                intent.putExtra(IntentCode.INTENT_WEB_URL, "http://" + entry.url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        mainHeaderView = new MainHeaderView(mContext);
        lvNews.addHeaderView(mainHeaderView);
        lvNews.addItemDecoration(new SimpleListItemDecoration(mContext, R.drawable.divider_latest_news));
        lvNews.refresh();
    }

}
