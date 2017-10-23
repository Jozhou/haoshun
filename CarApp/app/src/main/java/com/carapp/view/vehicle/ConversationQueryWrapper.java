package com.carapp.view.vehicle;

import android.text.TextUtils;

import com.carapp.common.data.Account;
import com.carapp.models.entry.VehicleItemEntry;
import com.carapp.models.entry.VehicleQueryEntry;
import com.carapp.utils.DataUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/23.
 */

public class ConversationQueryWrapper {

    private static ConversationQueryWrapper mInstance;

    private List<VehicleQueryEntry> mData;

    public static ConversationQueryWrapper get() {
        if (mInstance == null) {
            mInstance = new ConversationQueryWrapper();
        }
        return mInstance;
    }

    private ConversationQueryWrapper() {
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        String queryItem = DataUtils.getConversationQueryItem(Account.get().user_id);
        if (!TextUtils.isEmpty(queryItem)) {
            try {
                JSONArray array = new JSONArray(queryItem);
                for (int i = 0; i < array.length(); i ++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    VehicleQueryEntry entry = new VehicleQueryEntry();
                    entry.carcode =jsonObject.getString("carcode");
                    entry.detail =jsonObject.getString("detail");
                    mData.add(entry);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void clear() {
        if (mData != null && !mData.isEmpty()) {
            mData.clear();
            DataUtils.clearConversationQueryItem(Account.get().user_id);
        }
    }

    public void add(VehicleQueryEntry entry) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(entry);

        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < mData.size(); i ++) {
                VehicleQueryEntry item = mData.get(i);
                JSONObject object = new JSONObject();
                object.put("carcode", item.carcode);
                object.put("detail", item.detail);
                jsonArray.put(object);
            }
            DataUtils.setConversationQueryItem(Account.get().user_id, jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<VehicleQueryEntry> getData() {
        return this.mData;
    }
}
