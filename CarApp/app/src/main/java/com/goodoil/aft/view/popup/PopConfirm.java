package com.goodoil.aft.view.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.goodoil.aft.R;

public class PopConfirm extends PopupWindow implements OnClickListener {
	private Context mContext;
	private View mContentView;
	private View vEmpty;
	private View vContent;
	private TextView tvOne;
	private TextView tvTwo;
	private TextView tvCancel;

	private OnClickListener mOneClickListener;
	private OnClickListener mTwoClickListener;
	private OnClickListener mCancelClickListener;

	public PopConfirm(Context context) {
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		LayoutInflater mInflater = LayoutInflater.from(mContext);
		mContentView = mInflater.inflate(R.layout.pop_confirm, null);

		vEmpty = mContentView.findViewById(R.id.v_empty);

		tvOne = (TextView) mContentView.findViewById(R.id.tv_one);
		tvTwo = (TextView) mContentView.findViewById(R.id.tv_two);
		tvCancel = (TextView) mContentView.findViewById(R.id.tv_cancel);
		vContent = mContentView.findViewById(R.id.content);
		vEmpty.setOnClickListener(this);
		tvOne.setOnClickListener(this);
		tvTwo.setOnClickListener(this);
		tvCancel.setOnClickListener(this);

		setWindowLayoutMode(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		setOutsideTouchable(true);
		setContentView(mContentView);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.v_empty) {
			dismiss();
		} else if (id == R.id.tv_one) {
            dismiss();
            if (mOneClickListener != null) {
                mOneClickListener.onClick(tvOne);
            }
		} else if (id == R.id.tv_two) {
            dismiss();
            if (mTwoClickListener != null) {
                mTwoClickListener.onClick(tvTwo);
            }
		} else if (id == R.id.tv_cancel) {
            dismiss();
            if (mCancelClickListener != null) {
                mCancelClickListener.onClick(tvCancel);
            }
		}

	}

	public void setText(CharSequence strOne, CharSequence strTwo) {
		tvOne.setText(strOne);
		tvTwo.setText(strTwo);
	}

	public void setOneClickListener(OnClickListener mListener) {
		this.mOneClickListener = mListener;
	}

	public void setTwoClickListener(View.OnClickListener listener) {
		this.mTwoClickListener = listener;
	}

	public void setCancelClickListener(OnClickListener listener) {
		this.mCancelClickListener = listener;
	}

	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.bottom_in);
		animation.setFillAfter(true);
		vContent.setAnimation(animation);

		Animation fadeAnimation = AnimationUtils.loadAnimation(mContext, R.anim.fade_in);
		mContentView.setAnimation(fadeAnimation);

		animation.start();
		fadeAnimation.start();
	}

}
