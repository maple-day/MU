package com.maplechen.mu.Http;

import com.maplechen.mu.Domain.ImgInfo;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/20.
 */
public abstract class MySubscriberImg extends Subscriber<ImgInfo> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    public abstract void onFailure(Throwable e);

    @Override
    public void onNext(ImgInfo imgInfo) {
        OnSuccess(imgInfo);
    }

    public abstract void OnSuccess(ImgInfo imgInfo);

}
