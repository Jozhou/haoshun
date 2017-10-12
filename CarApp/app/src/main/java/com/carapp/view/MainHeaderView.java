package com.carapp.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.carapp.R;
import com.carapp.activity.FrameNumQueryActivity;
import com.corelibrary.utils.DeviceUtils;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.layoutview.MLinearLayout;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MainHeaderView extends MLinearLayout {

    @ViewInject("ll_first_line")
    private View vFirstLine;
    @ViewInject("ll_second_line")
    private View vSecondLine;

    @ViewInject(value = "ll_conversation_know", setClickListener = true)
    private View llConversationKnow;
    @ViewInject(value = "ll_conversation_query", setClickListener = true)
    private View getLlConversationQuery;
    @ViewInject(value = "ll_oil_change", setClickListener = true)
    private View llOilChange;
    @ViewInject(value = "ll_frame_num_query", setClickListener = true)
    private View llFrameNumQuery;
    @ViewInject(value = "ll_pro_intro", setClickListener = true)
    private View llProIntro;

    public MainHeaderView(Context context) {
        super(context);
    }

    public MainHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main_header;
    }

    @Override
    protected void initData() {
        super.initData();
        int height = DeviceUtils.getScreenWidth() / 3;
        ViewGroup.LayoutParams params = vFirstLine.getLayoutParams();
        params.height = height;
        vFirstLine.setLayoutParams(params);

        params = vSecondLine.getLayoutParams();
        params.height = height;
        vSecondLine.setLayoutParams(params);

    }

    @Override
    protected void onApplyData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.ll_conversation_know) {

        } else if (id == R.id.ll_conversation_query) {

        } else if (id == R.id.ll_oil_change) {

        } else if (id == R.id.ll_frame_num_query) {
            Intent intent = new Intent(mContext, FrameNumQueryActivity.class);
            mContext.startActivity(intent);
        } else if (id == R.id.ll_pro_intro) {

        }
    }
}
