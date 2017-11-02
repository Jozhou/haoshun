package com.carapp.models.operater;

import android.content.Context;

import com.carapp.common.data.Account;
import com.carapp.models.entry.StoreEntry;
import com.corelibrary.models.entry.ArrayEntry;
import com.corelibrary.models.http.BaseArrayOperater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/10/9.
 */

public class GetMyStoreOperater extends BaseArrayOperater<StoreEntry> {

    public GetMyStoreOperater(Context context) {
        super(context);
    }

    public void setParams(String userlon, String userlat, int shoptype) {
        try {
            paramsObj.put("user_id", Account.get().user_id);
            paramsObj.put("userlon", userlon);
            paramsObj.put("userlat", userlat);
            paramsObj.put("shoptype", shoptype);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected ArrayEntry<StoreEntry> parseJsonObject(JSONObject jo) {
        ArrayEntry<StoreEntry> arrayEntry = new ArrayEntry<>();
        try {
            JSONArray array = jo.getJSONObject("content").getJSONArray("data");
            for (int i = 0; i < array.length(); i ++) {
                JSONObject jsonObject = array.getJSONObject(i);
                StoreEntry entry = new StoreEntry();
                entry.shopid =jsonObject.getString("shopid");
                entry.shopname =jsonObject.getString("shopname");
                entry.image =jsonObject.getString("image");
                entry.evolution =jsonObject.getInt("evolution");
//                entry.businessstart =jsonObject.getString("businessstart");
//                entry.businessend =jsonObject.getString("businessend");
                entry.tel =jsonObject.getString("tel");
                entry.shoploacl =jsonObject.getString("shoploacl");
                entry.url =jsonObject.getString("url");
                entry.shoplon =jsonObject.getString("shoplon");
                entry.shoplat =jsonObject.getString("shoplat");
                entry.businessTime =jsonObject.getString("businessTime");

                arrayEntry.getArray().add(entry);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayEntry;
    }

    @Override
    protected ArrayEntry<StoreEntry> parseJsonArray(JSONArray ja) {
        return null;
    }

    @Override
    protected String getUrlAction() {
        return "/shop/myshop";
    }

}