package com.carapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carapp.R;
import com.carapp.common.data.Account;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.VehicleItemEntry;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

/**
 * Created by Administrator on 2017/10/11.
 */

public class PersonalInfoActivity extends BaseActivity {

    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("iv_head")
    private ImageView ivHead;

    @ViewInject(value = "ll_head", setClickListener = true)
    private View vHead;
    @ViewInject(value = "ll_nick", setClickListener = true)
    private View vNick;
    @ViewInject(value = "ll_sex", setClickListener = true)
    private View vSex;
    @ViewInject(value = "ll_account", setClickListener = true)
    private View vAccount;
    @ViewInject(value = "ll_bind_tel", setClickListener = true)
    private View vBindTel;
    @ViewInject(value = "ll_modify_psw", setClickListener = true)
    private View vModifyPsw;
    @ViewInject(value = "ll_brand", setClickListener = true)
    private View vBrand;
    @ViewInject(value = "ll_series", setClickListener = true)
    private View vSeries;
    @ViewInject(value = "ll_year_style", setClickListener = true)
    private View vYearStyle;
    @ViewInject(value = "ll_version", setClickListener = true)
    private View vVersion;

    @ViewInject("tv_nick")
    private TextView tvNick;
    @ViewInject("tv_sex")
    private TextView tvSex;
    @ViewInject("tv_account")
    private TextView tvAccount;
    @ViewInject("tv_bind_tel")
    private TextView tvBindTel;
    @ViewInject("tv_brand")
    private TextView tvBrand;
    @ViewInject("tv_series")
    private TextView tvSeries;
    @ViewInject("tv_year_style")
    private TextView tvYearStyle;
    @ViewInject("tv_version")
    private TextView tvVersion;

    private VehicleItemEntry brand;
    private VehicleItemEntry series;
    private VehicleItemEntry yearStyle;
    private VehicleItemEntry version;
    private static final int REQUEST_MODIFY_NICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
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
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.ll_head) {

        } else if (id == R.id.ll_head) {

        } else if (id == R.id.ll_nick) {
            Intent intent = new Intent(this, ModifyNickActivity.class);
            startActivityForResult(intent, REQUEST_MODIFY_NICK);
        } else if (id == R.id.ll_sex) {

        } else if (id == R.id.ll_bind_tel) {

        } else if (id == R.id.ll_modify_psw) {
            Intent intent = new Intent(this, ModifyPswActivity.class);
            startActivity(intent);
        } else if (id == R.id.ll_brand) {
            onSelBrand();
        } else if (id == R.id.ll_series) {
            onSelSeries();
        } else if (id == R.id.ll_year_style) {
            onSelYearStyle();
        } else if (id == R.id.ll_version) {
            onSelVersion();
        }
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        Glide.with(this).load(Account.get().imageurl)
                .placeholder(R.drawable.head_icon)
                .error(R.drawable.head_icon)
                .into(ivHead);
        tvNick.setText(Account.get().nickname);
        tvSex.setText(Account.get().getSexStr());
        tvAccount.setText(Account.get().tel);
        tvBindTel.setText(Account.get().tel);
        tvBrand.setText(Account.get().brand_id);
        tvSeries.setText(Account.get().series_id);
        tvYearStyle.setText(Account.get().year_style);
        tvVersion.setText(Account.get().version);
    }

    private void onSelBrand() {
        Intent intent = new Intent(this, SelVehicleActivity.class);
        intent.putExtra(IntentCode.INTENT_TYPE, SelVehicleActivity.SEL_BRAND);
        startActivityForResult(intent, SelVehicleActivity.SEL_BRAND);
    }

    private void onSelSeries() {
//        if (brand == null) {
//            DialogUtils.showToastMessage(R.string.sel_brand_id);
//            return;
//        }
        Intent intent = new Intent(this, SelVehicleActivity.class);
        intent.putExtra(IntentCode.INTENT_TYPE, SelVehicleActivity.SEL_SERIES);
        intent.putExtra(IntentCode.INTENT_BRAND_ID, "137");
        startActivityForResult(intent, SelVehicleActivity.SEL_SERIES);
    }

    private void onSelYearStyle() {
//        if (series == null) {
//            DialogUtils.showToastMessage(R.string.sel_series_id);
//            return;
//        }
        Intent intent = new Intent(this, SelVehicleActivity.class);
        intent.putExtra(IntentCode.INTENT_TYPE, SelVehicleActivity.SEL_YEAR_STYLE);
//        intent.putExtra(IntentCode.INTENT_SERIES_ID, series.id);
        startActivityForResult(intent, SelVehicleActivity.SEL_YEAR_STYLE);
    }

    private void onSelVersion() {
//        if (yearStyle == null) {
//            DialogUtils.showToastMessage(R.string.sel_year_style);
//            return;
//        }
        Intent intent = new Intent(this, SelVehicleActivity.class);
        intent.putExtra(IntentCode.INTENT_TYPE, SelVehicleActivity.SEL_VERSION);
//        intent.putExtra(IntentCode.INTENT_SERIES_ID, series.id);
//        intent.putExtra(IntentCode.INTENT_YEAR_STYLE, yearStyle.name);
        startActivityForResult(intent, SelVehicleActivity.SEL_VERSION);
    }
}
