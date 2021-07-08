package com.wangxingxing.mvpmoduledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wangxingxing.lib_network.configuration.Constants;
import com.wangxingxing.mvpmoduledemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initView();
    }

    private void initView() {
        mBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(Constants.Path.LOGIN_LOGIN_ACTIVITY)
                        .navigation();
            }
        });

        mBinding.btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(Constants.Path.MAIN_WEATHER_ACTIVITY)
                        .navigation();
//                Intent intent = new Intent(MainActivity.this, WeatherViewActivity.class);
//                startActivity(intent);
            }
        });
    }
}