package com.carapp.utils;

import com.carapp.common.data.Account;
import com.corelibrary.utils.PreferenceUtils;

/**
 * 用SharedPreferences存储
 * 
 */

public class DataUtils {
	
	private static final String TAG = DataUtils.class.getSimpleName();
	

	/****************  Acccount 相关  ************************/
	private static final String KEY_ACCOUNT_ID = "account_id";
	private static final String KEY_ACCOUNT_IMAGE = "account_image";
	private static final String KEY_ACCOUNT_NICK = "account_nick";
    private static final String KEY_ACCOUNT_SEX = "account_sex";
    private static final String KEY_ACCOUNT_BRAND_ID = "account_brand_id";
	private static final String KEY_ACCOUNT_BRAND_NAME = "account_brand_name";
    private static final String KEY_ACCOUNT_SERIES_ID = "account_series_id";
	private static final String KEY_ACCOUNT_SERIES_NAME = "account_series_name";
	private static final String KEY_ACCOUNT_YEAR_STYLE = "account_year_style";
	private static final String KEY_ACCOUNT_VERSION = "account_version";
	private static final String KEY_ACCOUNT_PWD = "account_PWD";
	private static final String KEY_ACCOUNT_TEL = "account_tel";
	private static final String KEY_ACCOUNT_CARCODE = "account_carcode";
    
    /**
     * 上次发生crash的时间戳
     */
    public static final String KEY_LASTCRASH = "lastCrashTime";
    
	/**
	 * 保存用户信息
	 * @param account
	 */
	public static void putAccount(Account account) {
		if (account == null) {
			return;
		}
		try {
			PreferenceUtils.putInt(KEY_ACCOUNT_ID, account.user_id);
			PreferenceUtils.putString(KEY_ACCOUNT_IMAGE, account.imageurl);
			PreferenceUtils.putString(KEY_ACCOUNT_NICK, account.nickname);
            PreferenceUtils.putInt(KEY_ACCOUNT_SEX, account.sex);
            PreferenceUtils.putString(KEY_ACCOUNT_BRAND_ID, account.brand_id);
			PreferenceUtils.putString(KEY_ACCOUNT_BRAND_NAME, account.brand_name);
			PreferenceUtils.putString(KEY_ACCOUNT_SERIES_ID, account.series_id);
			PreferenceUtils.putString(KEY_ACCOUNT_SERIES_NAME, account.series_name);
			PreferenceUtils.putString(KEY_ACCOUNT_YEAR_STYLE, account.year_style);
			PreferenceUtils.putString(KEY_ACCOUNT_VERSION, account.version);
			PreferenceUtils.putString(KEY_ACCOUNT_PWD, account.pwd);
			PreferenceUtils.putString(KEY_ACCOUNT_TEL, account.tel);
			PreferenceUtils.putString(KEY_ACCOUNT_CARCODE, account.carcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public static void getAccount(Account account) {
		try  {
			account.user_id = PreferenceUtils.getInt(KEY_ACCOUNT_ID);
			account.imageurl = PreferenceUtils.getString(KEY_ACCOUNT_IMAGE);
			account.nickname = PreferenceUtils.getString(KEY_ACCOUNT_NICK);
			account.sex = PreferenceUtils.getInt(KEY_ACCOUNT_SEX);
			account.brand_id = PreferenceUtils.getString(KEY_ACCOUNT_BRAND_ID);
			account.brand_name = PreferenceUtils.getString(KEY_ACCOUNT_BRAND_NAME);
			account.series_id = PreferenceUtils.getString(KEY_ACCOUNT_SERIES_ID);
			account.series_name = PreferenceUtils.getString(KEY_ACCOUNT_SERIES_NAME);
			account.year_style = PreferenceUtils.getString(KEY_ACCOUNT_YEAR_STYLE);
			account.version = PreferenceUtils.getString(KEY_ACCOUNT_VERSION);
			account.pwd = PreferenceUtils.getString(KEY_ACCOUNT_PWD);
			account.tel = PreferenceUtils.getString(KEY_ACCOUNT_TEL);
			account.carcode = PreferenceUtils.getString(KEY_ACCOUNT_CARCODE);
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * 移除用户信息
	 */
	public static void removeAccount() {
		PreferenceUtils.remove(KEY_ACCOUNT_ID);
		PreferenceUtils.remove(KEY_ACCOUNT_IMAGE);
		PreferenceUtils.remove(KEY_ACCOUNT_NICK);
		PreferenceUtils.remove(KEY_ACCOUNT_SEX);
		PreferenceUtils.remove(KEY_ACCOUNT_BRAND_ID);
		PreferenceUtils.remove(KEY_ACCOUNT_BRAND_NAME);
		PreferenceUtils.remove(KEY_ACCOUNT_SERIES_ID);
		PreferenceUtils.remove(KEY_ACCOUNT_SERIES_NAME);
		PreferenceUtils.remove(KEY_ACCOUNT_YEAR_STYLE);
		PreferenceUtils.remove(KEY_ACCOUNT_VERSION);
		PreferenceUtils.remove(KEY_ACCOUNT_PWD);
		PreferenceUtils.remove(KEY_ACCOUNT_TEL);
		PreferenceUtils.remove(KEY_ACCOUNT_CARCODE);
	}
	
}