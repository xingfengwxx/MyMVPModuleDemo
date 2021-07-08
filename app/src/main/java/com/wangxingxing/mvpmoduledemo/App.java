package com.wangxingxing.mvpmoduledemo;

import android.app.Application;

import androidx.startup.AppInitializer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wangxingxing.lib_network.configuration.AppConfig;

import dagger.hilt.android.HiltAndroidApp;

/**
 * author : 王星星
 * date : 2021/7/8 10:32
 * email : 1099420259@qq.com
 * description : 使用Hilt的步骤：
 *               1. 在自定义的Application上使用@HiltAndroidApp (其实就是帮助生成ApplicationComponent)
 *               2. 定义Module,并使用@InstallIn指定装载到哪个Component上
 *               3. @AndroidEntryPoint 在目标Activity上使用这个注解，自动执行注入动作
 *               4. 注入，构造方法注入，provides/binds方法注入
 */
@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppInitializer.getInstance(this)
                .initializeComponent(AppConfig.class)
                .baseUrl("http://api.k780.com")
                .writeTimeout(60)
                .readTimeout(60)
                .connTimeout(2)
                .configure();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
