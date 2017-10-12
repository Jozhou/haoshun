package com.carapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.carapp.R;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.TitleBar;

/**
 * Created by Administrator on 2017/9/29.
 */

public class FrameDetailActivity extends BaseActivity {


    @ViewInject("titlebar")
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_detail);
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
}
