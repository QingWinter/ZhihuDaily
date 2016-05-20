package com.winter.huang.zhihudaily.common;

import android.app.Application;

import com.winter.huang.zhihudaily.network.ZhiHuApi;

/**
 * Created by Winter on 2016/5/16.
 * Description the application class
 * email:huang.wqing@qq.com
 */
public class ZhiHuApplication extends Application {

    public static final int ENV = Constants.Http.Environment.Command.DEV_HOST;
    private static ZhiHuApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * get the instance of ZhiHuApplication
     *
     * @return the instance of ZhiHuApplication
     */
    public static ZhiHuApplication getApplication() {
        if (null == instance) {
            synchronized (ZhiHuApplication.class) {
                if (null == instance) {
                    instance = new ZhiHuApplication();
                }
            }
        }
        return instance;
    }

}
