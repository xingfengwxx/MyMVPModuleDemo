package com.wangxingxing.module_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.wangxingxing.lib_network.configuration.Constants;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
@Route(path = Constants.Path.LOGIN_LOGIN_ACTIVITY)
public class LoginActivity extends AppCompatActivity {

    @Inject
    Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LogUtils.i(test);
    }
}