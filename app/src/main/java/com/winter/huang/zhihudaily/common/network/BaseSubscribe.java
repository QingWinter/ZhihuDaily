package com.winter.huang.zhihudaily.common.network;

import rx.Subscriber;

/**
 * Created by Winter on 2016/5/20.
 * Description
 * email:huang.wqing@qq.com
 */
public class BaseSubscribe<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
