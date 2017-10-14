package com.carapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carapp.R;
import com.carapp.common.data.Account;
import com.carapp.models.operater.UpdatePwdOperater;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.models.http.BaseOperater;
import com.corelibrary.utils.DialogUtils;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ModifyPswActivity extends BaseActivity {

    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("et_username")
    private EditText etUsername;
    @ViewInject("et_verify_code")
    private EditText etVerifyCode;
    @ViewInject(value = "tv_get_code", setClickListener = true)
    private TextView tvGetCode;
    @ViewInject(value = "et_new_psw")
    private EditText etNewPsw;
    @ViewInject(value = "et_re_new_psw")
    private EditText etReNewPsw;
    @ViewInject(value = "btn_save", setClickListener = true)
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
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

        } else if (id == R.id.btn_save) {
            save();
        }
    }

    private void save() {
        String tel = etUsername.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        String psw = etNewPsw.getText().toString().trim();
        String confrimPsw = etReNewPsw.getText().toString();

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

        modifyPsw();
    }

    public void modifyPsw() {
        String tel = etUsername.getText().toString().trim();
        final String psw = etNewPsw.getText().toString().trim();
        final UpdatePwdOperater operater = new UpdatePwdOperater(this);
        operater.setParams(tel, psw);
        operater.onReq(new BaseOperater.RspListener() {
            @Override
            public void onRsp(boolean success, Object obj) {
                if (success) {
                    DialogUtils.showToastMessage(operater.getMsg());
                    Account.get().setPwd(psw);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }
}
