package com.carapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.carapp.R;
import com.carapp.activity.ActivityWeb;
import com.carapp.activity.CityListActivity;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.CityItemEntry;
import com.carapp.models.entry.NewsEntry;
import com.carapp.view.MainHeaderView;
import com.carapp.view.news.LatestNewsListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.corelibrary.fragment.base.BaseFragment;
import com.corelibrary.utils.ButtonUtils;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.decoration.SimpleListItemDecoration;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MainFragment extends BaseFragment {

    @ViewInject("lv_news")
    private LatestNewsListView lvNews;
    @ViewInject(value = "titlebar_lefttext", setClickListener = true)
    private TextView tvLeft;
    @ViewInject(value = "titlebar_righticon", setClickListener = true)
    private ImageView ivRight;

    private MainHeaderView mainHeaderView;

    private static final int SEL_CITY = 1001;

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
                intent.putExtra(IntentCode.INTENT_WEB_URL, entry.url);
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

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (ButtonUtils.isFastDoubleClick()) {
            return;
        }
        int id = v.getId();
        if (id == R.id.titlebar_lefttext) {
            Intent intent = new Intent(mContext, CityListActivity.class);
            startActivityForResult(intent, SEL_CITY);
        } else if (id == R.id.titlebar_righticon) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEL_CITY) {
            if (resultCode == Activity.RESULT_OK) {
                CityItemEntry entry = (CityItemEntry) data.getSerializableExtra(IntentCode.INTENT_CITY_ITEM);
                tvLeft.setText(entry.province_name);
            }
        }
    }
}
