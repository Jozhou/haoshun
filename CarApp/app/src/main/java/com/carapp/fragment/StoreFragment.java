package com.carapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.RadioGroup;

import com.carapp.R;
import com.carapp.activity.ActivityWeb;
import com.carapp.activity.ActivityWebStore;
import com.carapp.activity.StoreMapActivity;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.NewsEntry;
import com.carapp.models.entry.StoreEntry;
import com.carapp.utils.location.DLocation;
import com.carapp.utils.location.LocationListener;
import com.carapp.utils.location.LocationManager;
import com.carapp.view.store.StoreListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.corelibrary.fragment.base.BaseFragment;
import com.corelibrary.models.entry.ArrayEntry;
import com.corelibrary.utils.DialogUtils;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;
import com.corelibrary.view.decoration.SimpleListItemDecoration;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/28.
 */

public class StoreFragment extends BaseFragment {

    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("rg_tab")
    private RadioGroup rgTabs;
    @ViewInject("rv_all")
    private StoreListView rvAll;
    @ViewInject("rv_conversation")
    private StoreListView rvConversation;
    @ViewInject("rv_repair")
    private StoreListView rvRepair;

    @Override
    protected int getContentResId() {
        return R.layout.fragment_store;
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

        titleBar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList list = (ArrayList) rvAll.getDataSource();
                if (list == null) {
                    list = new ArrayList();
                }
                Intent intent = new Intent(mContext, StoreMapActivity.class);
                intent.putExtra(IntentCode.INTENT_STORE_LIST, list);
                startActivity(intent);
            }
        });

        rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_all) {
                    rvAll.setVisibility(View.VISIBLE);
                    rvConversation.setVisibility(View.GONE);
                    rvRepair.setVisibility(View.GONE);
                } else if (checkedId == R.id.rb_conversation_center) {
                    rvAll.setVisibility(View.GONE);
                    rvConversation.setVisibility(View.VISIBLE);
                    rvRepair.setVisibility(View.GONE);
                } else if (checkedId == R.id.rb_repair_center) {
                    rvAll.setVisibility(View.GONE);
                    rvConversation.setVisibility(View.GONE);
                    rvRepair.setVisibility(View.VISIBLE);
                }
            }
        });

        rvAll.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreEntry entry = (StoreEntry) adapter.getData().get(position);
                Intent intent = new Intent(mContext, ActivityWebStore.class);
                intent.putExtra(IntentCode.INTENT_WEB_URL, entry.url);
                intent.putExtra(IntentCode.INTENT_STORE_ITEM, entry);
                startActivity(intent);
            }
        });

        rvConversation.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreEntry entry = (StoreEntry) adapter.getData().get(position);
                Intent intent = new Intent(mContext, ActivityWeb.class);
                intent.putExtra(IntentCode.INTENT_WEB_URL, entry.url);
                startActivity(intent);
            }
        });

        rvRepair.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreEntry entry = (StoreEntry) adapter.getData().get(position);
                Intent intent = new Intent(mContext, ActivityWeb.class);
                intent.putExtra(IntentCode.INTENT_WEB_URL, entry.url);
                startActivity(intent);
            }
        });

        rvAll.setOnLoadingDataListener(new StoreListView.OnLoadingDataListener() {
            @Override
            public void onLoadingData() {
                getData();
            }
        });
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();

        rvAll.addItemDecoration(new SimpleListItemDecoration(mContext, R.drawable.divider_latest_news));
        rvConversation.addItemDecoration(new SimpleListItemDecoration(mContext, R.drawable.divider_latest_news));
        rvRepair.addItemDecoration(new SimpleListItemDecoration(mContext, R.drawable.divider_latest_news));


//        rvConversation.setParams("117.201538", "39.085294", 1);
//        rvRepair.setParams("117.201538", "39.085294", 2);


//        rvConversation.refresh();
//        rvRepair.refresh();

        rgTabs.check(R.id.rb_all);
        getData();
    }

    private void getData() {
        rvAll.gotoLoading();
        LocationManager.getInstance().registLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(DLocation location) {
                Activity activity = (Activity) mContext;
                if (activity == null || activity.isFinishing()) {
                    return;
                }
                rvAll.setParams(location.getLongitude() + "", location.getLatitude() + "", 0);
                rvAll.refresh();
            }

            @Override
            public void onlocationFail() {
                rvAll.gotoError();
                DialogUtils.showToastMessage(R.string.location_fail);
            }
        });
    }
}
