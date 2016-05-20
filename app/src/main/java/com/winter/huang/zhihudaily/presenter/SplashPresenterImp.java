package com.winter.huang.zhihudaily.presenter;

import com.winter.huang.zhihudaily.respositry.model.SplashModel;
import com.winter.huang.zhihudaily.view.SplashView;

import rx.Observer;

/**
 * Created by Winter on 2016/5/16.
 * Description
 * email:huang.wqing@qq.com
 */
public class SplashPresenterImp implements SplashPresenter{
    private SplashView splashView;
    private SplashModel splashModel;


    public SplashPresenterImp(SplashView splashView) {
        this.splashView = splashView;
        this.splashModel = new SplashModel(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }

    @Override
    public void getSplashImage() {

    }
}
