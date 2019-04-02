package com.csmobile.csyd.net;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author:liyongchang
 * Time:2018/3/16  下午6:25
 * Description:This is ResponseObserver
 */

public abstract class ResponseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        onStart(d);
    }

    @Override
    public void onComplete() {
        onCompleted();
    }

    public void onStart(Disposable d){}
    public abstract void onCompleted();
}
