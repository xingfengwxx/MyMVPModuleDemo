package com.wangxingxing.lib_network.http;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : 王星星
 * date : 2021/7/7 16:23
 * email : 1099420259@qq.com
 * description :
 */
@Singleton
public class OkHttpProcessor implements IHttpProcessor {

    final OkHttpClient mOkHttpClient;

    final Handler mHandler;

    @Inject
    public OkHttpProcessor(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFailure(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String result = response.body().string();
                                    if (response.isSuccessful()) {
                                        callback.onSuccess(result);
                                    } else {
                                        callback.onFailure(result);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    callback.onFailure(e.getMessage());
                                }
                            }
                        });
                    }
                });
    }

    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        return body.build();
    }
}
