package com.maplechen.mu.Http;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/20.
 */
public abstract class MySubscriber extends Subscriber<ResponseBody> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onErrorMsg(e);
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            byte[] bytes = responseBody.bytes();
            String s = new String(bytes, "gb2312");
            onSuccessMsg(s);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public  abstract void onErrorMsg(Throwable e);
    public  abstract void onSuccessMsg(String html);
}
