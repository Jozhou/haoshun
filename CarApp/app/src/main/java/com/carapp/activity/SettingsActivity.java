package com.carapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carapp.R;
import com.carapp.common.data.Account;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

/**
 * Created by Administrator on 2017/10/16.
 */

public class SettingsActivity extends BaseActivity {


    @ViewInject(value = "titlebar")
    private TitleBar titlebar;
    @ViewInject(value = "btn_logout", setClickListener = true)
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onBindListener() {
        super.onBindListener();
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
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
        if (id == R.id.btn_logout) {
            logout();
        }
    }

    private void logout() {
        Account.get().clear();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
