package com.wangxingxing.mvpmoduledemo.net;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * author : 王星星
 * date : 2021/7/8 11:03
 * email : 1099420259@qq.com
 * description :
 */
public interface ApiService {

    // http://api.k780.com/?app=weather.today&weaid=1&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
    @GET("/")
    Observable<BaseResponse<WeatherInfo>> getWeatherInfo(@QueryMap Map<String, String> map);
}
