package com.wangxingxing.mvpmoduledemo.contract;

import com.wangxingxing.lib_network.http.HttpCallback;
import com.wangxingxing.mvpmoduledemo.base.IModel;
import com.wangxingxing.mvpmoduledemo.base.IView;
import com.wangxingxing.mvpmoduledemo.net.WeatherInfo;

/**
 * author : 王星星
 * date : 2021/7/8 11:02
 * email : 1099420259@qq.com
 * description :
 */
public interface WeatherContract {

    interface View extends IView {
        void showWeatherInfo(WeatherInfo bean);
    }

    interface Model extends IModel {
        void loadWeatherInfo(HttpCallback<WeatherInfo> callback);

        void loadWeatherInfoNested(HttpCallback<WeatherInfo> callback);
    }
}
