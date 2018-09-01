package com.noby.dptask.Data.network;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by A.Noby on 8/29/2018.
 */
public class MainApiBody {

    private static final String JSON_TYPE = "application/json";

    @NonNull
    private static RequestBody requestBody(JSONObject jsonBody) {
        return RequestBody.create(MediaType.parse(JSON_TYPE), jsonBody.toString());
    }
    public static RequestBody WelcomeBoby(String lang) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("lang", lang);
        return requestBody(params);
    }
}
