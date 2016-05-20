package com.winter.huang.zhihudaily.common.network;

import com.hele.buyer.common.log.Logger;

/**
 * Created by Winter on 2016/5/19.
 * Description
 * email:huang.wqing@qq.com
 */
public class ApiException extends RuntimeException {

    public ApiException(int state, String msg) {
        Logger.e("LoginActivity", msg);
    }
}
