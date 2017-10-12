package com.carapp.fragment;

import com.carapp.R;
import com.carapp.models.adapter.AdAdapter;
import com.corelibrary.fragment.base.BaseFragment;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.viewflow.CircleFlowIndicator;
import com.corelibrary.view.viewflow.ViewFlow;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MainFragment extends BaseFragment {


    @ViewInject("viewflow")
    private ViewFlow viewflow;
    @ViewInject("viewflowindic")
    private CircleFlowIndicator viewflowindic;

    @Override
    protected int getContentResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        viewflow.setAdapter(new AdAdapter(getActivity()));
        viewflow.setFlowIndicator(viewflowindic);
        viewflow.setSideBuffer(7);
        viewflow.setSelection(7 * 1000); // 设置初始位置
        viewflow.startAutoFlowTimer();

    }
}
