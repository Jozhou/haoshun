package com.carapp.models.operater;

import android.content.Context;

import com.carapp.common.data.Account;
import com.corelibrary.models.http.BaseOperater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/30.
 */

public class LoginOperater extends BaseOperater {

    private String pwd;
    private String tel;
    public LoginOperater(Context context) {
        super(context);
    }

    public void setParams(String type, String tel, String pwd) {
        try {
            paramsObj.put("type", type);
            paramsObj.put("tel", tel);
            paramsObj.put("pwd", pwd);
            this.tel = tel;
            this.pwd = pwd;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getUrlAction() {
        return "/user/login";
    }

    @Override
    protected void onParser(JSONObject jsonObject) {
        try {
            JSONObject response = jsonObject.getJSONObject("content");
            int user_id = response.getInt("user_id");
            String imageurl = response.getString("imageurl");
            String nickname = response.getString("nickname");
            int sex = response.getInt("sex");
            JSONObject vehicleObj = response.getJSONObject("vertical");
            String brand_id = vehicleObj.optString("brand_id");
            String brand_name = vehicleObj.optString("brand_name");
            String series_id = vehicleObj.optString("series_id");
            String series_name = vehicleObj.optString("series_name");
            String year_style = vehicleObj.optString("year_style");
            String version = vehicleObj.optString("version");
            String carcode = vehicleObj.getString("carcode");
            Account.get().login(user_id, imageurl, nickname, sex, brand_id, brand_name, series_id, series_name, year_style, version, pwd, tel, carcode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onParser(JSONArray response) {


    }

}
