package com.winter.huang.zhihudaily.respositry.model;

import com.winter.huang.zhihudaily.network.Foo;
import com.winter.huang.zhihudaily.network.ZhiHuApi;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Winter on 2016/5/16.
 * Description
 * email:huang.wqing@qq.com
 */
public class SplashModel {
    private Subscription subscription;
    private Observer observer;

    public SplashModel(Observer observer) {
        this.observer = observer;
    }

    void getImage() {
        Foo foo = ZhiHuApi.Factory.create(Foo.class);
        subscription = foo
                .getSplashImage("320*432")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .asObservable()
                .subscribe();
    }
}
