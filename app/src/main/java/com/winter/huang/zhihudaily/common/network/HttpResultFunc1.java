package com.winter.huang.zhihudaily.common.network;

import com.hele.buyer.common.log.Logger;
import com.hele.buyer.personal.model.HeadModel;

import rx.functions.Func1;

/**
 * Created by Winter on 2016/5/19.
 * Description
 * email:huang.wqing@qq.com
 */
public class HttpResultFunc1<T> implements Func1<HeadModel<T>, T>{
    @Override
    public T call(HeadModel<T> headModel) {
        Logger.e("LoginActivity", headModel.toString());
        int state = headModel.getState();
        String msg = headModel.getMsg();
        if (state != 0) {
            throw new ApiException(state, msg);
        }

        return headModel.getData();
    }
}
