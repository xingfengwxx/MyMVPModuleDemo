package com.wangxingxing.mvpmoduledemo.model;

import com.wangxingxing.lib_network.http.HttpCallback;
import com.wangxingxing.lib_network.retrofit.exception.ApiException;
import com.wangxingxing.lib_network.retrofit.exception.ErrorConsumer;
import com.wangxingxing.lib_network.retrofit.respone.ResponseTransformer;
import com.wangxingxing.mvpmoduledemo.base.BaseModel;
import com.wangxingxing.mvpmoduledemo.contract.WeatherContract;
import com.wangxingxing.mvpmoduledemo.net.ApiService;
import com.wangxingxing.mvpmoduledemo.net.WeatherInfo;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityScoped;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author : 王星星
 * date : 2021/7/8 11:33
 * email : 1099420259@qq.com
 * description :
 */
@ActivityScoped
public class WeatherModel extends BaseModel implements WeatherContract.Model {

    final ApiService mApiService;

    @Inject
    public WeatherModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void loadWeatherInfo(HttpCallback<WeatherInfo> callback) {
        final Map<String, String> params = new HashMap<>();
        params.put("app", "weather.today");
        params.put("weaid", "1");
        params.put("appkey", "10003");
        params.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        params.put("format", "json");

        Disposable d = mApiService.getWeatherInfo(params)
                .compose(ResponseTransformer.<WeatherInfo>obtain(mCompositeDisposable))
                .subscribe(new Consumer<WeatherInfo>() {
                    @Override
                    public void accept(WeatherInfo weatherInfo) throws Exception {
                        callback.onSuccess(weatherInfo);
                    }
                }, new ErrorConsumer() {
                    @Override
                    protected void error(ApiException ex) {
                        callback.onFailure(ex.getMessage());
                    }
                });

    }

    @Override
    public void loadWeatherInfoNested(HttpCallback<WeatherInfo> callback) {
        final Map<String, String> params = new HashMap<>();
        params.put("app", "weather.today");
        params.put("weaid", "1");
        params.put("appkey", "10003");
        params.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        params.put("format", "json");
        //嵌套请求

    }
}
