package com.wangxingxing.mvpmoduledemo;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangxingxing.lib_network.configuration.Constants;
import com.wangxingxing.mvpmoduledemo.base.BaseActivity;
import com.wangxingxing.mvpmoduledemo.contract.WeatherContract;
import com.wangxingxing.mvpmoduledemo.databinding.ActivityWeatherViewBinding;
import com.wangxingxing.mvpmoduledemo.net.WeatherInfo;
import com.wangxingxing.mvpmoduledemo.presenter.WeatherPresenter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
@Route(path = Constants.Path.MAIN_WEATHER_ACTIVITY)
public class WeatherViewActivity extends BaseActivity<WeatherPresenter, ActivityWeatherViewBinding> implements WeatherContract.View {

    @Override
    protected void initView() {
        mBinding.btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.requestWeatherInfo();
            }
        });
    }

    @Override
    public void showWeatherInfo(WeatherInfo bean) {
        mBinding.tvWeatherInfo.setText(bean.toString());
    }
}