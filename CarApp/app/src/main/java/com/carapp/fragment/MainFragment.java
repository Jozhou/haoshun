package com.carapp.fragment;

import com.carapp.R;
import com.carapp.models.adapter.AdAdapter;
import com.carapp.models.operater.GetNewsOperater;
import com.carapp.view.MainHeaderView;
import com.carapp.view.NewsListView;
import com.corelibrary.fragment.base.BaseFragment;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.decoration.SimpleListItemDecoration;
import com.corelibrary.view.viewflow.CircleFlowIndicator;
import com.corelibrary.view.viewflow.ViewFlow;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MainFragment extends BaseFragment {

    @ViewInject("lv_news")
    private NewsListView lvNews;

    private MainHeaderView mainHeaderView;

    @Override
    protected int getContentResId() {
        return R.layout.fragment_main;
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
