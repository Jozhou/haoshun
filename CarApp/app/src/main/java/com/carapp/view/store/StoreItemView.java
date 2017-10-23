package com.carapp.view.store;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carapp.R;
import com.carapp.models.entry.StoreEntry;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.layoutview.MRelativeLayout;

/**
 * Created by Administrator on 2017/10/13.
 */
public class StoreItemView extends MRelativeLayout<StoreEntry> {

    @ViewInject("iv_store")
    private ImageView ivStore;
    @ViewInject("tv_name")
    private TextView tvName;
    @ViewInject("iv_star")
    private ImageView ivStar;
    @ViewInject("tv_time")
    private TextView tvTime;
    @ViewInject("tv_tel")
    private TextView tvTel;
    @ViewInject("tv_distance")
    private TextView tvDistance;
    @ViewInject("tv_address")
    private TextView tvAddress;

    public StoreItemView(Context context) {
        super(context);
    }

    public StoreItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StoreItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_store;
    }

    @Override
    protected void onApplyData() {
        Glide.with(mContext).load(mDataItem.image).dontAnimate().into(ivStore);
        tvName.setText(mDataItem.shopname);
        if (mDataItem.evolution == 10) {
            ivStar.setImageResource(R.drawable.shop_xingxing_5);
        } else if (mDataItem.evolution >=8) {
            ivStar.setImageResource(R.drawable.shop_xingxing_4);
        } else if (mDataItem.evolution >=6) {
            ivStar.setImageResource(R.drawable.shop_xingxing_3);
        } else if (mDataItem.evolution >=4) {
            ivStar.setImageResource(R.drawable.shop_xingxing_2);
        } else {
            ivStar.setImageResource(R.drawable.shop_xingxing_1);
        }
        tvTime.setText(mDataItem.businessstart + "-" + mDataItem.businessend);
        tvTel.setText(mDataItem.tel);
        tvAddress.setText(mDataItem.shoploacl);
//        tvDistance.setText(mDataItem.distance);
    }
}
