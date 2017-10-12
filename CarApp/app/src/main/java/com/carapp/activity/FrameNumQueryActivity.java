package com.carapp.activity;

import android.content.Intent;
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

public class FrameNumQueryActivity extends BaseActivity {


    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("et_frame_num")
    private EditText etFrameNum;
    @ViewInject(value = "btn_query", setClickListener = true)
    private Button btnQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_query);
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
        if (id == R.id.btn_query) {
            Intent intent = new Intent(this, FrameDetailActivity.class);
            startActivity(intent);
        }
    }
}
