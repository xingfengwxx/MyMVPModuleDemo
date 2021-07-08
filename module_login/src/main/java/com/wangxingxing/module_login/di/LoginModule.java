package com.wangxingxing.module_login.di;

import com.wangxingxing.module_login.Test;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

/**
 * author : 王星星
 * date : 2021/7/7 18:00
 * email : 1099420259@qq.com
 * description :
 */
@Module
@InstallIn(ApplicationComponent.class)
public class LoginModule {

    @Provides
    Test provideTest() {
        return new Test();
    }
}
