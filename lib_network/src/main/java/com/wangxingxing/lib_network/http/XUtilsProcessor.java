package com.wangxingxing.lib_network.http;

import android.app.Application;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

import javax.inject.Inject;

/**
 * author : 王星星
 * date : 2021/7/7 16:56
 * email : 1099420259@qq.com
 * description :
 */
public class XUtilsProcessor implements IHttpProcessor {

    @Inject
    public XUtilsProcessor(Application app) {
        x.Ext.init(app);
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        String finalUrl = HttpUtils.appendParams(url, params);
        RequestParams requestParams = new RequestParams(finalUrl);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onFailure(cex.getMessage());
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
