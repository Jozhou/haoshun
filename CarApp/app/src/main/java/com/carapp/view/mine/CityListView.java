package com.carapp.view.mine;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.carapp.R;
import com.carapp.models.adapter.CityAdapter;
import com.carapp.models.entry.CityItemEntry;
import com.carapp.models.operater.GetCityOperater;
import com.carapp.utils.location.DLocation;
import com.carapp.utils.location.LocationListener;
import com.carapp.utils.location.LocationManager;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.loading.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;
import me.yokeyword.indexablerv.SimpleHeaderAdapter;

/**
 * Created by Administrator on 2017/10/31.
 */

public class CityListView extends FrameLayout {

    @ViewInject("indexableLayout")
    private IndexableLayout indexableLayout;

    private CityAdapter mAdapter;
    private List<CityItemEntry> cityItemEntries;

    private SimpleHeaderAdapter mHotCityAdapter;
    private SimpleHeaderAdapter mGpsHeaderAdapter;
    private List<CityItemEntry> gpsCity;



    public CityListView(Context context) {
        super(context);
    }

    public CityListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CityListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.view_city_list;
    }

    @Override
    protected void onBindListener() {
        super.onBindListener();
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        indexableLayout.setLayoutManager(new LinearLayoutManager(mContext));
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
        mAdapter = new CityAdapter(mContext);
        indexableLayout.setAdapter(mAdapter);

        mHotCityAdapter = new SimpleHeaderAdapter<>(mAdapter, "热", "热门城市", iniyHotCityDatas());
        // 热门城市
        indexableLayout.addHeaderAdapter(mHotCityAdapter);
        // 定位
        gpsCity = iniyGPSCityDatas();
        mGpsHeaderAdapter = new SimpleHeaderAdapter<>(mAdapter, "", "当前城市", gpsCity);
        indexableLayout.addHeaderAdapter(mGpsHeaderAdapter);

        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<CityItemEntry>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, CityItemEntry entity) {
                if (originalPosition >= 0) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(originalPosition, entity);
                    }
                }
            }
        });

        mGpsHeaderAdapter.setOnItemHeaderClickListener(new IndexableHeaderAdapter.OnItemHeaderClickListener() {
            @Override
            public void onItemClick(View v, int currentPosition, Object entity) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(currentPosition, (CityItemEntry) entity);
                }
            }
        });

    }

    public void refreshData() {
        getCityData();
        startLoction();
    }

    private void getCityData() {
        final GetCityOperater operater = new GetCityOperater(mContext);
        operater.onReq(new BaseOperater.RspListener() {
            @Override
            public void onRsp(boolean success, Object obj) {
                if (success) {
                    cityItemEntries = operater.getCityItemEntries();
                    if (cityItemEntries.size() == 0) {
                        gotoBlank();
                    } else {
                        gotoSuccessful();
                        mAdapter.setDatas(cityItemEntries);
                    }
                } else {
                    gotoError();
                }
            }
        });
    }

    private void startLoction() {
        LocationManager.getInstance().registLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(DLocation location) {
                gpsCity.get(0).province_name = location.getProvince();
                mGpsHeaderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onlocationFail() {
                gpsCity.get(0).province_name = mContext.getString(R.string.location_fail);
                mGpsHeaderAdapter.notifyDataSetChanged();
            }
        });
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, CityItemEntry entry);
    }

    @Override
    public void onApplyLoadingData() {
        super.onApplyLoadingData();
        refreshData();
    }

    private List<CityItemEntry> iniyGPSCityDatas() {
        List<CityItemEntry> list = new ArrayList<>();
        list.add(new CityItemEntry("定位中..."));
        return list;
    }

    private List<CityItemEntry> iniyHotCityDatas() {
        List<CityItemEntry> list = new ArrayList<>();
        list.add(new CityItemEntry(""));
        return list;
    }
}
