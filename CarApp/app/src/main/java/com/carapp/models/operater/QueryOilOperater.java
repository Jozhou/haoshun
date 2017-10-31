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

public class QueryOilOperater extends BaseOperater {

    public QueryOilOperater(Context context) {
        super(context);
    }

    public void setParams(String carcode) {
        try {
            paramsObj.put("user_id", Account.get().user_id);
            paramsObj.put("carcode", carcode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getUrlAction() {
        return "/vehicle/changeoil";
    }

    @Override
    protected void onParser(JSONObject jsonObject) {
//        mData = new ArrayList<>();
//        try {
//            JSONArray array = jsonObject.getJSONObject("content").getJSONArray("maintenance");
//            for(int i = 0; i < array.length(); i ++) {
//                NameValueEntry entry = new NameValueEntry();
//                JSONObject obj = array.getJSONObject(i);
//                entry.name = obj.getString("key");
//                entry.value = obj.getString("value");
//                entry.image = obj.optString("image");
//                mData.add(entry);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onParser(JSONArray response) {
    }

}
