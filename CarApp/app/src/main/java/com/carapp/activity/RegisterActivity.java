package com.carapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carapp.R;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.VehicleItemEntry;
import com.carapp.models.operater.RegisterOperater;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.DialogUtils;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

/**
 * Created by Administrator on 2017/9/28.
 */

public class RegisterActivity extends BaseActivity {

    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("et_tel")
    private EditText etTel;
    @ViewInject("et_verify_code")
    private EditText etVerifyCode;
    @ViewInject("et_psw")
    private EditText etPsw;
    @ViewInject(value = "tv_get_code", setClickListener = true)
    private TextView tvGetCode;
    @ViewInject("et_confirm_psw")
    private EditText etConfirmPsw;
    @ViewInject(value = "tv_brand", setClickListener = true)
    private TextView tvBrand;
    @ViewInject(value = "tv_series", setClickListener = true)
    private TextView tvSeries;
    @ViewInject(value = "tv_year_style", setClickListener = true)
    private TextView tvYearStyle;
    @ViewInject(value = "tv_version", setClickListener = true)
    private TextView tvVersion;

    @ViewInject(value = "tv_agreement", setClickListener = true)
    private TextView tvAgreement;
    @ViewInject(value = "btn_confirm", setClickListener = true)
    private Button btnConfirm;

    private VehicleItemEntry brand;
    private VehicleItemEntry series;
    private VehicleItemEntry yearStyle;
    private VehicleItemEntry version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.tv_get_code) {

        } else if (id == R.id.tv_agreement) {

        } else if (id == R.id.btn_confirm) {
            doRegister();
        } else if (id == R.id.tv_brand) {
            onSelBrand();
        } else if (id == R.id.tv_series) {
            onSelSeries();
        } else if (id == R.id.tv_year_style) {
            onSelYearStyle();
        } else if (id == R.id.tv_version) {
            onSelVersion();
        }
    }

    private void onSelBrand() {
        Intent intent = new Intent(this, SelVehicleActivity.class);
        intent.putExtra(IntentCode.INTENT_TYPE, SelVehicleActivity.SEL_BRAND);
        startActivityForResult(intent, SelVehicleActivity.SEL_BRAND);
    }

    private void onSelSeries() {
        if (brand == null) {
            DialogUtils.showToastMessage(R.string.sel_brand_id);
            return;
        }
        Intent intent = new Intent(this, SelVehicleActivity.class);
        intent.putExtra(IntentCode.INTENT_TYPE, SelVehicleActivity.SEL_SERIES);
        intent.putExtra(IntentCode.INTENT_BRAND_ID, "137");
        startActivityForResult(intent, SelVehicleActivity.SEL_SERIES);
    }

    private void onSelYearStyle() {
        if (series == null) {
            DialogUtils.showToastMessage(R.string.sel_series_id);
            return;
        }
        Intent intent = new Intent(this, SelVehicleActivity.class);
        intent.putExtra(IntentCode.INTENT_TYPE, SelVehicleActivity.SEL_YEAR_STYLE);
        intent.putExtra(IntentCode.INTENT_SERIES_ID, series.id);
        startActivityForResult(intent, SelVehicleActivity.SEL_YEAR_STYLE);
    }

    private void onSelVersion() {
        if (yearStyle == null) {
            DialogUtils.showToastMessage(R.string.sel_year_style);
            return;
        }
        Intent intent = new Intent(this, SelVehicleActivity.class);
        intent.putExtra(IntentCode.INTENT_TYPE, SelVehicleActivity.SEL_VERSION);
        intent.putExtra(IntentCode.INTENT_SERIES_ID, series.id);
        intent.putExtra(IntentCode.INTENT_YEAR_STYLE, yearStyle.name);
        startActivityForResult(intent, SelVehicleActivity.SEL_VERSION);
    }

    private void doRegister() {
        String tel = etTel.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        String psw = etPsw.getText().toString().trim();
        String confrimPsw = etConfirmPsw.getText().toString();
//        String brand = tvBrand.getText().toString().trim();
//        String series = tvSeries.getText().toString().trim();
//        String yearStyle = tvYearStyle.getText().toString().trim();
//        String version = tvVersion.getText().toString().trim();

        if (tel.length() == 0) {
            DialogUtils.showToastMessage(R.string.hint_input_tel);
            return;
        }
        if (tel.length() != 11) {
            DialogUtils.showToastMessage(R.string.error_tel_format);
            return;
        }
        if (psw.length() == 0 || confrimPsw.length() == 0) {
            DialogUtils.showToastMessage(R.string.hint_input_psw);
            return;
        }
        if (tel.length() < 6 || confrimPsw.length() < 6) {
            DialogUtils.showToastMessage(R.string.error_psw_format);
            return;
        }
        if (!psw.equals(confrimPsw)) {
            DialogUtils.showToastMessage(R.string.error_psw_not_equal);
            return;
        }
        if (brand.name.length() == 0) {
            DialogUtils.showToastMessage(R.string.sel_brand_id);
            return;
        }
        if (series.name.length() == 0) {
            DialogUtils.showToastMessage(R.string.sel_series_id);
            return;
        }
        if (yearStyle.name.length() == 0) {
            DialogUtils.showToastMessage(R.string.sel_year_style);
            return;
        }
        if (version.name.length() == 0) {
            DialogUtils.showToastMessage(R.string.sel_version);
            return;
        }
        RegisterOperater operater = new RegisterOperater(this);
        operater.setParams("1", tel, psw, version.carcode);
        operater.onReq(new BaseOperater.RspListener() {
            @Override
            public void onRsp(boolean success, Object obj) {
                if (success) {
                    DialogUtils.showToastMessage(R.string.register_succ);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelVehicleActivity.SEL_BRAND) {
            if (resultCode == RESULT_OK) {
                brand = (VehicleItemEntry) data.getSerializableExtra(IntentCode.INTENT_VEHICLE_ITEM);
                tvBrand.setText(brand.name);
            }
        } else if (requestCode == SelVehicleActivity.SEL_SERIES) {
            if (resultCode == RESULT_OK) {
                series = (VehicleItemEntry) data.getSerializableExtra(IntentCode.INTENT_VEHICLE_ITEM);
                tvSeries.setText(series.name);
            }
        } else if (requestCode == SelVehicleActivity.SEL_YEAR_STYLE) {
            if (resultCode == RESULT_OK) {
                yearStyle = (VehicleItemEntry) data.getSerializableExtra(IntentCode.INTENT_VEHICLE_ITEM);
                tvYearStyle.setText(yearStyle.name);
            }
        } else if (requestCode == SelVehicleActivity.SEL_VERSION) {
            if (resultCode == RESULT_OK) {
                version = (VehicleItemEntry) data.getSerializableExtra(IntentCode.INTENT_VEHICLE_ITEM);
                tvVersion.setText(version.name);
            }
        }
    }
}
