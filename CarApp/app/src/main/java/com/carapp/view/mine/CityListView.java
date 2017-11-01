package com.carapp.view.mine;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.carapp.R;
import com.carapp.models.adapter.CityAdapter;
import com.carapp.models.entry.CityItemEntry;
import com.carapp.models.operater.GetCityOperater;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.loading.FrameLayout;

import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * Created by Administrator on 2017/10/31.
 */

public class CityListView extends FrameLayout {

    @ViewInject("indexableLayout")
    private IndexableLayout indexableLayout;

    private CityAdapter mAdapter;
    private List<CityItemEntry> cityItemEntries;

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
    }

    public void refreshData() {
        getCityData();
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
}
