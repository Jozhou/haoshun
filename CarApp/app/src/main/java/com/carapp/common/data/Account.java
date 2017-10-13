package com.carapp.common.data;

import android.content.Context;
import android.text.TextUtils;

import com.carapp.utils.DataUtils;
import com.corelibrary.activity.base.BaseActivity;
import com.corelibrary.cache.data.CacheManager;
import com.corelibrary.models.entry.BaseEntry;

public class Account extends BaseEntry {

	private static final String TAG = Account.class.getSimpleName();

	private static final byte[] mLock = new byte[0];
	private static Account mInstance = null;

	public final static Account get() {
		synchronized (mLock) {
			if (mInstance == null) {
				mInstance = new Account();
			}
			return mInstance;
		}
	}

	public int user_id;
	public String imageurl = "";
	public String nickname = "";
	public int sex; // 0/1 男／女
	public String brand_id = "";
	public String brand_name = "";
	public String series_id = "";
	public String series_name = "";
	public String year_style = "";
	public String version = "";
	public String pwd = "";
    public String tel = "";
	public String carcode = "";

	public String getSexStr() {
		return sex == 0? "男" : "女";
	}

	private Account() {
		DataUtils.getAccount(this);
	}

	/**
	 * 重新加载数据
	 */
	public void reload() {
		DataUtils.getAccount(this);
	}

	/**
	 * 是否已登录
	 * 
	 * @return
	 */
	public boolean isLogin() {
		return isLogin(false);
	}

	/**
	 * 是否已登录
	 * 
	 * @param loadFromPref
	 *            是否从文件pref加载最新状态
	 * @return
	 */
	public boolean isLogin(boolean loadFromPref) {
		if (loadFromPref) {
			DataUtils.getAccount(this);
		}
		return user_id != 0
				&& !TextUtils.isEmpty(pwd);
	}

	/**
	 * 登录
	 */
	public void login(int user_id, String imageurl,
			String nickname, int sex, String brand_id,
			String brand_name, String series_id,
			String series_name, String year_style,
			String version, String pwd, String tel,
					  String carcode) {
		this.user_id = user_id;
		this.imageurl = imageurl;
		this.nickname = nickname;
		this.sex = sex;
		this.brand_id = brand_id;
		this.brand_name = brand_name;
		this.series_id = series_id;
		this.series_name = series_name;
		this.year_style = year_style;
		this.version = version;
		this.pwd = pwd;
        this.tel = tel;
		this.carcode = carcode;

		save();
	}

	public void logout(final Context context) {
		logout(context, true);
	}

	/**
	 * 退出登录
	 * 
	 * @param context
	 * @param requestOperater
	 *            是否需要请求operater
	 */
	public void logout(final Context context, boolean requestOperater) {
		if (requestOperater) {
			if (context != null && context instanceof BaseActivity) {
				((BaseActivity) context).showLoading(true);
			}
		} else {
			_logout();
		}
	}

	/**
	 * 退出登录
	 */
	private void _logout() {
		clear();
		// 跳转至登录页
//		PackageUtils.restartApplication();
	}

	/**
	 * 清除登录信息
	 */
	public void clear() {
		this.user_id = 0;
		this.imageurl = "";
		this.nickname = "";
		this.sex = 0;
		this.brand_id = "";
		this.brand_name = "";
		this.series_id = "";
		this.series_name = "";
		this.year_style = "";
		this.version = "";
		this.pwd = "";
        this.tel = "";
		this.carcode = "";
		// 清除账号信息
		DataUtils.removeAccount();
		// 清除缓存
		CacheManager.get().clearCache();
	}

	public void save() {
		DataUtils.putAccount(this);
	}

}
