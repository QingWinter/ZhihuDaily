package com.winter.huang.zhihudaily.common.network;

import android.content.Context;

import com.winter.huang.zhihudaily.common.network.base.BaseResult;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Winter on 2016/5/19.
 * Description Parse the result from server, if state = 0 return the data to client, else handle the exception.
 * email:huang.wqing@qq.com
 */
public class NetworkResultFunc1<T> implements Func1<BaseResult<T>, Observable<T>> {
    private Context context;

    public NetworkResultFunc1(Context context) {
        this.context = context;
    }

    @Override
    public Observable<T> call(final BaseResult<T> tBaseResult) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                int state = tBaseResult.getState();
                String msg = tBaseResult.getMsg();
                switch (state) {
                    case 0:
                        subscriber.onNext(tBaseResult.getData());
                        break;
                    case 1000:
                        subscriber.onError(new ApiException(state, msg));
                        break;
                }
                subscriber.onCompleted();
            }
        });
    }

//    @Override
//    public T call(BaseResult<T> headModel) {
//        int state = headModel.getState();
//        String msg = headModel.getMsg();
//        if (state != 0) {
//            switch (state) {
//                case 1000:
//                    //For example, token is past due, use context to start LoginActivity
//
//                    break;
//            }
//            throw new ApiException(state, msg);
//        } else {
//            return headModel.getData();
//        }
//    }
}
