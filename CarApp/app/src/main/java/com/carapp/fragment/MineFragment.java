package com.carapp.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carapp.R;
import com.carapp.activity.PersonalInfoActivity;
import com.carapp.common.data.Account;
import com.corelibrary.fragment.base.BaseFragment;
import com.corelibrary.utils.ViewInject.ViewInject;
import com.corelibrary.view.CircleImageView;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MineFragment extends BaseFragment {

    @ViewInject(value = "ll_head", setClickListener = true)
    private View vHead;
    @ViewInject("tv_name")
    private TextView tvName;
    @ViewInject("tv_tel")
    private TextView tvTel;
    @ViewInject("iv_head")
    private CircleImageView ivHead;


    @Override
    protected int getContentResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();
        tvName.setText(Account.get().nickname);
        tvTel.setText(Account.get().tel);
        Glide.with(this).load(Account.get().imageurl)
                .placeholder(R.drawable.head_icon)
                .error(R.drawable.head_icon)
                .into(ivHead);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.ll_head) {
            Intent intent = new Intent(mContext, PersonalInfoActivity.class);
            mContext.startActivity(intent);
        }
    }
}
