package com.wangxingxing.mvpmoduledemo.di;

import android.app.Activity;

import com.wangxingxing.mvpmoduledemo.WeatherViewActivity;
import com.wangxingxing.mvpmoduledemo.databinding.ActivityMainBinding;
import com.wangxingxing.mvpmoduledemo.databinding.ActivityWeatherViewBinding;
import com.wangxingxing.mvpmoduledemo.model.WeatherModel;
import com.wangxingxing.mvpmoduledemo.net.ApiService;
import com.wangxingxing.mvpmoduledemo.presenter.WeatherPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import retrofit2.Retrofit;

/**
 * author : 王星星
 * date : 2021/7/8 11:30
 * email : 1099420259@qq.com
 * description :
 */
// A @Module may not contain both non-static and abstract binding methods
@Module
@InstallIn({ActivityComponent.class})
public abstract class AppModule {

    // Hilt帮我们注入了Application, Activity对象
    @ActivityScoped
    @Provides
    static WeatherPresenter provideWeatherPresenter(Activity activity, WeatherModel weatherModel) {
        return new WeatherPresenter((WeatherViewActivity) activity, weatherModel);
    }

    @Provides
    static ActivityWeatherViewBinding provideActivityWeatherBinding(Activity activity) {
        return ActivityWeatherViewBinding.inflate(activity.getLayoutInflater());
    }

    @Provides
    static ActivityMainBinding provideActivityMainBinding(Activity activity) {
        return ActivityMainBinding.inflate(activity.getLayoutInflater());
    }

    @Provides
    static ApiService bindApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
