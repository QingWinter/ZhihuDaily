package com.winter.huang.zhihudaily.common;

import android.app.Application;
import android.database.Observable;

import com.winter.huang.zhihudaily.common.network.ApiException;
import com.winter.huang.zhihudaily.common.network.NetworkResultFunc1;
import com.winter.huang.zhihudaily.common.network.base.BaseNetwork;
import com.winter.huang.zhihudaily.common.network.base.BaseResult;
import com.winter.huang.zhihudaily.network.ZhiHuApi;

import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Observers;

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
        BaseNetwork.Factory.create(PersonalNetwork.class)
                .queryUser("")
                .flatMap(new NetworkResultFunc1<QueryData>(this))
                .flatMap(new Func1<QueryData, rx.Observable<?>>() {
                    @Override
                    public rx.Observable<?> call(QueryData queryData) {
                        return null;
                    }
                })
                .subscribe(new Subscriber<QueryData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ApiException) {
                            int state = ((ApiException)e).getState();
                        }
                    }

                    @Override
                    public void onNext(QueryData queryData) {

                    }
                });

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
