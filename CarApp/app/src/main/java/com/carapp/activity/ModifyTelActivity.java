package com.carapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carapp.R;
import com.carapp.common.data.Account;
import com.carapp.context.Config;
import com.carapp.models.operater.UpdatePwdOperater;
import com.carapp.models.operater.UpdateTelnumOperater;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.DialogUtils;
import com.corelibrary.utils.LogcatUtils;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ModifyTelActivity extends BaseActivity {

    private static final String TAG = ModifyTelActivity.class.getName();

    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("et_username")
    private EditText etUsername;
    @ViewInject("et_verify_code")
    private EditText etVerifyCode;
    @ViewInject(value = "tv_get_code", setClickListener = true)
    private TextView tvGetCode;
    @ViewInject(value = "btn_save", setClickListener = true)
    private Button btnSave;

    private EventHandler eventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_tel);
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
                            try {
                                JSONObject object = new JSONObject(msg);
                                String detail = object.getString("detail");
                                DialogUtils.showToastMessage(detail);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                DialogUtils.showToastMessage(msg);
                            }
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
        } else if (id == R.id.btn_save) {
            save();
        }
    }

    private void save() {
        String tel = etUsername.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();

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

        SMSSDK.submitVerificationCode(Config.COUNTRY_CODE, tel, verifyCode);
    }

    public void modifyPsw() {
        final String tel = etUsername.getText().toString().trim();
        final UpdateTelnumOperater operater = new UpdateTelnumOperater(this);
        operater.setParams(tel);
        operater.onReq(new BaseOperater.RspListener() {
            @Override
            public void onRsp(boolean success, Object obj) {
                if (success) {
                    DialogUtils.showToastMessage(operater.getMsg());
                    Account.get().setPwd(tel);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
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
        String tel = etUsername.getText().toString().trim();
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
        modifyPsw();
    }

    private void onVerifyFail() {
//        DialogUtils.showToastMessage(R.string.verify_code_fial);
    }
}
