package com.carapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carapp.R;
import com.carapp.common.data.Account;
import com.carapp.context.Config;
import com.carapp.context.IntentCode;
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

    @ViewInject(value = "ll_feedback", setClickListener = true)
    private View vFeedback;
    @ViewInject(value = "ll_service", setClickListener = true)
    private View vService;
    @ViewInject(value = "ll_about_us", setClickListener = true)
    private View vAbout;

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
        } else if (id == R.id.ll_feedback) {
            Intent intent = new Intent(this, ActivityWeb.class);
            intent.putExtra(IntentCode.INTENT_WEB_URL, Config.FEEDBACK);
            startActivity(intent);
        } else if (id == R.id.ll_service) {
            Intent intent = new Intent(this, ActivityWeb.class);
            intent.putExtra(IntentCode.INTENT_WEB_URL, Config.SERVICE_ITEM);
            startActivity(intent);
        } else if (id == R.id.ll_about_us) {
            Intent intent = new Intent(this, ActivityWeb.class);
            intent.putExtra(IntentCode.INTENT_WEB_URL, Config.ABOUT_US);
            startActivity(intent);
        }
    }

    private void logout() {
        Account.get().clear();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
