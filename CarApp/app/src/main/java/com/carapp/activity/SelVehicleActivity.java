package com.carapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.carapp.R;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.VehicleItemEntry;
import com.carapp.view.vehicle.BrandListView;
import com.carapp.view.vehicle.SeriesListView;
import com.carapp.view.vehicle.VersionListView;
import com.carapp.view.vehicle.YearStyleListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;
import com.corelibrary.view.adapterview.PullToRefreshMoreView;
import com.corelibrary.view.decoration.SimpleListItemDecoration;

/**
 * Created by Administrator on 2017/10/9.
 */

public class SelVehicleActivity extends BaseActivity {

    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("rl_container")
    private RelativeLayout rlContainer;

    private PullToRefreshMoreView refreshMoreView;

    public static final int SEL_BRAND = 1;
    public static final int SEL_SERIES = 2;
    public static final int SEL_YEAR_STYLE = 3;
    public static final int SEL_VERSION = 4;

    private int type;
    private String brand_id;
    private String series_id;
    private String year_style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_vehicle);
    }

    @Override
    protected void onQueryArguments(Intent intent) {
        super.onQueryArguments(intent);
        type = intent.getIntExtra(IntentCode.INTENT_TYPE, 1);
        brand_id = intent.getStringExtra(IntentCode.INTENT_BRAND_ID);
        series_id = intent.getStringExtra(IntentCode.INTENT_SERIES_ID);
        year_style = intent.getStringExtra(IntentCode.INTENT_YEAR_STYLE);
    }

    @Override
    protected void onBindListener() {
        super.onBindListener();
        titleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        titleBar.setTitle(getTitleRes(type));
        if (type == SEL_BRAND) {
            refreshMoreView = new BrandListView(this);
        } else if (type == SEL_SERIES) {
            refreshMoreView = new SeriesListView(this);
            ((SeriesListView)refreshMoreView).setParams(brand_id);
        } else if (type == SEL_YEAR_STYLE) {
            refreshMoreView = new YearStyleListView(this);
            ((YearStyleListView)refreshMoreView).setParams(series_id);
        } else if (type == SEL_VERSION) {
            refreshMoreView = new VersionListView(this);
            ((VersionListView)refreshMoreView).setParams(series_id, year_style);
        }
        refreshMoreView.addItemDecoration(new SimpleListItemDecoration(this));
        rlContainer.addView(refreshMoreView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        refreshMoreView.refresh();

        refreshMoreView.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra(IntentCode.INTENT_VEHICLE_ITEM, (VehicleItemEntry)adapter.getData().get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private int getTitleRes(int type) {
        int resId = 0;
        if (type == SEL_BRAND) {
            resId = R.string.title_sel_brand;
        } else if (type == SEL_SERIES) {
            resId = R.string.title_sel_series;
        } else if (type == SEL_YEAR_STYLE) {
            resId = R.string.title_sel_year_style;
        } else if (type == SEL_VERSION) {
            resId = R.string.title_sel_version;
        }
        return resId;
    }




}
