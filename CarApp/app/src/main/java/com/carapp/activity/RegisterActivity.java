package com.carapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carapp.R;
import com.carapp.context.Config;
import com.carapp.context.IntentCode;
import com.carapp.models.entry.VehicleItemEntry;
import com.carapp.models.operater.RegisterOperater;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.DialogUtils;
import com.corelibrary.utils.LogcatUtils;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/9/28.
 */

public class RegisterActivity extends BaseActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();

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

    private EventHandler eventHandler;

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

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(final int event, final int result, final Object data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LogcatUtils.e(TAG, "event:" + event + ",result:" + result + "data:" + (data == null ?"" : data.toString()));
                        if (data instanceof Throwable) {
                            Throwable throwable = (Throwable)data;
                            String msg = throwable.getMessage();
                            DialogUtils.showToastMessage(msg);
                            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                // 处理你自己的逻辑
                                onSendMessageFail();
                            } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                onVerifyFail();
                            }
                        } else {
                            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                // 处理你自己的逻辑
                                onSendMessageSucc();
                            } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                onVerifySucc();
                            }
                        }
                    }
                });
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
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
            sendMessage();
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
        intent.putExtra(IntentCode.INTENT_BRAND_ID, brand.id);
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

        if (tel.length() == 0) {
            DialogUtils.showToastMessage(R.string.hint_input_tel);
            return;
        }
        if (tel.length() != 11) {
            DialogUtils.showToastMessage(R.string.error_tel_format);
            return;
        }

        if (verifyCode.length() == 0) {
            DialogUtils.showToastMessage(R.string.hint_input_verify_code);
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

        SMSSDK.submitVerificationCode(Config.COUNTRY_CODE, tel, verifyCode);
    }

    public void register() {
        String tel = etTel.getText().toString().trim();
        String psw = etPsw.getText().toString().trim();
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

    private int count = 60;
    private static final int MSG_WHAT = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_WHAT) {
                if (count > 0) {
                    tvGetCode.setText(count + "s");
                    count --;
                    mHandler.sendEmptyMessageDelayed(MSG_WHAT, 1000);
                } else {
                    tvGetCode.setEnabled(true);
                    tvGetCode.setText(R.string.get_verify_code);
                }
            }
        }
    };

    private void sendMessage() {
        String tel = etTel.getText().toString().trim();
        if (tel.length() == 0) {
            DialogUtils.showToastMessage(R.string.hint_input_tel);
            return;
        }
        if (tel.length() != 11) {
            DialogUtils.showToastMessage(R.string.error_tel_format);
            return;
        }
        count = 60;
        tvGetCode.setEnabled(false);
        mHandler.removeMessages(MSG_WHAT);
        mHandler.sendEmptyMessage(MSG_WHAT);
        SMSSDK.getVerificationCode(Config.COUNTRY_CODE, tel);
    }

    private void onSendMessageSucc() {
        DialogUtils.showToastMessage(R.string.send_succ);
    }

    private void onSendMessageFail() {
        DialogUtils.showToastMessage(R.string.send_fail);
        tvGetCode.setEnabled(true);
        tvGetCode.setText(R.string.get_verify_code);
        mHandler.removeMessages(MSG_WHAT);
    }

    private void onVerifySucc() {
        register();
    }

    private void onVerifyFail() {
//        DialogUtils.showToastMessage(R.string.verify_code_fial);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventHandler != null) {
            SMSSDK.unregisterEventHandler(eventHandler);
        }
    }
}
