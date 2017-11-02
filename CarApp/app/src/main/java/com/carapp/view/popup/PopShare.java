package com.carapp.view.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.carapp.R;
import com.corelibrary.utils.DialogUtils;


/**
 * Created by Administrator on 2017/10/30.
 */

public class PopShare extends PopupWindow implements View.OnClickListener {

    private Context mContext;
    private View mContentView;
    private View vBackground;
    private View vContent;
    private TextView tvQQ;
    private TextView tvWx;
    private TextView tvFri;

    private View.OnClickListener mOneClickListener;
    private View.OnClickListener mTwoClickListener;
    private View.OnClickListener mCancelClickListener;

    public PopShare(Context context) {
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        mContentView = mInflater.inflate(R.layout.pop_share, null);

        vBackground = mContentView.findViewById(R.id.v_background);

        tvQQ = (TextView) mContentView.findViewById(R.id.tv_qq);
        tvWx = (TextView) mContentView.findViewById(R.id.tv_wx);
        tvFri = (TextView) mContentView.findViewById(R.id.tv_fri);
        vBackground = mContentView.findViewById(R.id.v_background);
        vContent = mContentView.findViewById(R.id.v_content);

        vBackground.setOnClickListener(this);
        tvQQ.setOnClickListener(this);
        tvWx.setOnClickListener(this);
        tvFri.setOnClickListener(this);

        setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(true);
        setContentView(mContentView);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.v_background) {
            dismiss();
        } else if (id == R.id.tv_qq) {
            dismiss();
            DialogUtils.showToastMessage("分享到QQ");
            if (mOneClickListener != null) {
                mOneClickListener.onClick(tvQQ);
            }
        } else if (id == R.id.tv_wx) {
            dismiss();
            DialogUtils.showToastMessage("分享到微信");
            if (mTwoClickListener != null) {
                mTwoClickListener.onClick(tvWx);
            }
        } else if (id == R.id.tv_fri) {
            dismiss();
            DialogUtils.showToastMessage("分享到朋友圈");
            if (mCancelClickListener != null) {
                mCancelClickListener.onClick(tvFri);
            }
        }

    }

    public void setOneClickListener(View.OnClickListener mListener) {
        this.mOneClickListener = mListener;
    }

    public void setTwoClickListener(View.OnClickListener listener) {
        this.mTwoClickListener = listener;
    }

    public void setCancelClickListener(View.OnClickListener listener) {
        this.mCancelClickListener = listener;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.bottom_in);
        vContent.setAnimation(animation);

        Animation fadeAnimation = AnimationUtils.loadAnimation(mContext, R.anim.fade_in);
        vBackground.setAnimation(fadeAnimation);

        animation.start();
        fadeAnimation.start();
    }
}
