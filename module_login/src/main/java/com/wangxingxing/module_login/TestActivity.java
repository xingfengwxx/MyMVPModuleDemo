package com.wangxingxing.module_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangxingxing.lib_network.configuration.Constants;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
@Route(path = Constants.Path.LOGIN_TEST_ACTIVITY)
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}