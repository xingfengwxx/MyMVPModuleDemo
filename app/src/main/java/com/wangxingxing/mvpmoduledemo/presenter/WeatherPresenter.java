package com.wangxingxing.mvpmoduledemo.presenter;

import com.wangxingxing.lib_network.http.HttpCallback;
import com.wangxingxing.mvpmoduledemo.base.BasePresenter;
import com.wangxingxing.mvpmoduledemo.contract.WeatherContract;
import com.wangxingxing.mvpmoduledemo.net.WeatherInfo;

/**
 * author : 王星星
 * date : 2021/7/8 11:43
 * email : 1099420259@qq.com
 * description :
 */
public class WeatherPresenter extends BasePresenter<WeatherContract.Model, WeatherContract.View> {
    public WeatherPresenter(WeatherContract.View view, WeatherContract.Model model) {
        super(view, model);
    }

    public void requestWeatherInfo() {
        mView.showLoading();
        mModel.loadWeatherInfo(new HttpCallback<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo weatherInfo) {
                mView.showWeatherInfo(weatherInfo);
                mView.dismissLoading();
            }
        });
    }
}
