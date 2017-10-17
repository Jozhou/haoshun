package com.carapp.view.store;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;

import com.carapp.R;
import com.carapp.models.entry.VehicleItemEntry;
import com.carapp.models.operater.GetBrandOperater;
import com.corelibrary.models.http.IArrayOperater;
import com.corelibrary.view.adapterview.PullToRefreshMoreView;

/**
 * Created by Administrator on 2017/10/9.
 */

public class StoreListView extends PullToRefreshMoreView<VehicleItemEntry> {

    private GetBrandOperater operater;

    public StoreListView(Context context) {
        super(context);
    }

    public StoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected IArrayOperater<VehicleItemEntry> createMode() {
        if (operater == null) {
            operater = new GetBrandOperater(mContext);
            operater.setShowLoading(false);
        }
        return operater;
    }

    @Override
    protected SparseIntArray getItemViewTypeAndResId() {
        SparseIntArray array = new SparseIntArray();
        array.put(0, R.layout.item_store);
        return array;
    }

    @Override
    protected View getLayoutItemView(int resId) {
        return new StoreItemView(mContext);
    }

    @Override
    public void refresh() {
        getFirstPage(false, true);
    }
}
