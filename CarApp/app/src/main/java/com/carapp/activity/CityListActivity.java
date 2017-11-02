package com.carapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.carapp.R;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.CityItemEntry;
import com.carapp.view.mine.CityListView;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

/**
 * Created by Administrator on 2017/10/16.
 */

public class CityListActivity extends BaseActivity {


    @ViewInject(value = "titlebar")
    private TitleBar titlebar;
    @ViewInject(value = "v_city_list")
    private CityListView cityListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
    }

    @Override
    protected void onBindListener() {
        super.onBindListener();
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cityListView.setOnItemClickListener(new CityListView.OnItemClickListener() {
            @Override
            public void onItemClick(CityItemEntry entry) {
                Intent intent = new Intent();
                intent.putExtra(IntentCode.INTENT_CITY_ITEM, entry);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        cityListView.refreshData();
    }
}
