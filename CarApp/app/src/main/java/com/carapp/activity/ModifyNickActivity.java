package com.carapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.carapp.R;
import com.carapp.common.data.Account;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ModifyNickActivity extends BaseActivity {

    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("et_nickname")
    private EditText etNick;
    @ViewInject(value = "iv_clear", setClickListener = true)
    private ImageView ivClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_nick);
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
        etNick.setText(Account.get().nickname);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.iv_clear) {
            etNick.setText("");
        }
    }
}
