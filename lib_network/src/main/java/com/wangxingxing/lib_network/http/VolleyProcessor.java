package com.wangxingxing.lib_network.http;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import javax.inject.Inject;

/**
 * author : 王星星
 * date : 2021/7/7 16:49
 * email : 1099420259@qq.com
 * description :
 */
public class VolleyProcessor implements IHttpProcessor {

    private RequestQueue mQueue = null;

    @Inject
    public VolleyProcessor(Application application) {
        mQueue = Volley.newRequestQueue(application);
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        String finalUrl = HttpUtils.appendParams(url, params);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                finalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.getMessage());
            }
        });
        mQueue.add(stringRequest);
    }
}
