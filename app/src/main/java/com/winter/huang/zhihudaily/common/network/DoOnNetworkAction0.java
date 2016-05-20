package com.winter.huang.zhihudaily.common.network;

import android.content.Context;
import android.text.TextUtils;

import com.hele.buyer.common.base.BaseActivity;
import com.hele.buyer.common.view.NetProgressBar;

import rx.functions.Action0;

/**
 * Created by Winter on 2016/5/20.
 * Description
 * email:huang.wqing@qq.com
 */
public class DoOnNetworkAction0 implements Action0 {

    private Context context;
    private String msg;
    private NetProgressBar netProgressBar;

    public DoOnNetworkAction0(Context context) {
        this.context = context;
    }

    public DoOnNetworkAction0(Context context, String msg) {
        this.context = context;
        this.msg = msg;
    }

    @Override
    public void call() {
        if (context instanceof BaseActivity) {
            netProgressBar = new NetProgressBar(context);
            if (!TextUtils.isEmpty(msg)) {
                netProgressBar.setText(msg);
            }
            netProgressBar.show();
        } else {
            throw new IllegalArgumentException("The context must be a Activity");
        }
    }
}
