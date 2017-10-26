package com.carapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/9/28.
 */

public class OilModelActivity extends BaseActivity {

    private static final String TAG = OilModelActivity.class.getSimpleName();

    @ViewInject("titlebar")
    private TitleBar titleBar;
    @ViewInject("photoview")
    private PhotoView photoView;

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_image);
    }

    @Override
    protected void onQueryArguments(Intent intent) {
        super.onQueryArguments(intent);
        path = intent.getStringExtra(IntentCode.INTENT_OIL_MODEL);
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
        Glide.with(this).load(path).dontAnimate().into(photoView);
    }

}
