package com.carapp.view.vehicle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;

import com.carapp.R;
import com.carapp.models.entry.VehicleItemEntry;
import com.carapp.models.operater.GetYearStyleOperater;
import com.corelibrary.models.http.IArrayOperater;
import com.corelibrary.view.adapterview.PullToRefreshMoreView;

/**
 * Created by Administrator on 2017/10/9.
 */

public class YearStyleListView extends PullToRefreshMoreView<VehicleItemEntry> {

    private GetYearStyleOperater operater;
    private String series_id = "";

    public YearStyleListView(Context context) {
        super(context);
    }

    public YearStyleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YearStyleListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setParams(String series_id) {
        this.series_id = series_id;
    }

    @Override
    protected IArrayOperater<VehicleItemEntry> createMode() {
        if (operater == null) {
            operater = new GetYearStyleOperater(mContext);
            operater.setParams(series_id);
            operater.setShowLoading(false);
        }
        return operater;
    }

    @Override
    protected SparseIntArray getItemViewTypeAndResId() {
        SparseIntArray array = new SparseIntArray();
        array.put(0, R.layout.item_vehicle);
        return array;
    }

    @Override
    protected View getLayoutItemView(int resId) {
        return new VehicleItemView(mContext);
    }

}
