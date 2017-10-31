package com.carapp.view.vehicle;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.carapp.R;
import com.carapp.models.adapter.BrandAdapter;
import com.carapp.models.entry.VehicleItemEntry;
import com.carapp.models.operater.GetBrandOperater2;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.DialogUtils;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.loading.FrameLayout;

import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * Created by Administrator on 2017/10/31.
 */

public class SelBrandView extends FrameLayout {

    @ViewInject("indexableLayout")
    private IndexableLayout indexableLayout;

    private BrandAdapter mAdapter;
    private List<VehicleItemEntry> vehicleItemEntries;

    public SelBrandView(Context context) {
        super(context);
    }

    public SelBrandView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelBrandView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.view_sel_brand;
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
        mAdapter = new BrandAdapter(mContext);
        indexableLayout.setAdapter(mAdapter);

        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<VehicleItemEntry>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, VehicleItemEntry entity) {
                if (originalPosition >= 0) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(originalPosition, entity);
                    }
                }
            }
        });
    }

    public void refreshData() {
        getBrandData();
    }

    private void getBrandData() {
        final GetBrandOperater2 operater = new GetBrandOperater2(mContext);
        operater.onReq(new BaseOperater.RspListener() {
            @Override
            public void onRsp(boolean success, Object obj) {
                if (success) {
                    vehicleItemEntries = operater.getVehicleItemEntries();
                    if (vehicleItemEntries.size() == 0) {
                        gotoBlank();
                    } else {
                        gotoSuccessful();
                        mAdapter.setDatas(vehicleItemEntries);
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
        void onItemClick(int pos, VehicleItemEntry entry);
    }
}
