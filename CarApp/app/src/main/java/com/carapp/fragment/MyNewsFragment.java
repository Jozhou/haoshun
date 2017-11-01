package com.carapp.fragment;

import android.content.Intent;
import android.view.View;

import com.carapp.R;
import com.carapp.activity.ActivityWeb;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.NewsEntry;
import com.carapp.view.news.MyNewsListView;
import com.carapp.view.news.NewsListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.corelibrary.fragment.base.BaseFragment;
import com.corelibrary.utils.ViewInject.ViewInject;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MyNewsFragment extends BaseFragment {

    @ViewInject("rv_news")
    private MyNewsListView rvNews;

    @Override
    protected int getContentResId() {
        return R.layout.fragment_my_news;
    }

    @Override
    protected void onBindListener() {
        super.onBindListener();
        rvNews.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsEntry entry = (NewsEntry) adapter.getData().get(position);
                Intent intent = new Intent(mContext, ActivityWeb.class);
                intent.putExtra(IntentCode.INTENT_WEB_URL, entry.url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        rvNews.getFirstPage(false, true);
    }
}
