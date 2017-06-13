package com.maplechen.mu.Http;

import com.maplechen.mu.Domain.NewInfo;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/20.
 */
public abstract class MySubscriberNew extends Subscriber<NewInfo> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    public abstract void onFailure(Throwable e);

    @Override
    public void onNext(NewInfo newInfo) {
        OnSuccess(newInfo);
    }

    public abstract void OnSuccess(NewInfo newInfo);

}
