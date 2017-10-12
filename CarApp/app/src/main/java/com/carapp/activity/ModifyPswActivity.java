package com.carapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.carapp.R;
import com.corelibrary.activity.base.BaseActivity;
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

        }
    }
}
