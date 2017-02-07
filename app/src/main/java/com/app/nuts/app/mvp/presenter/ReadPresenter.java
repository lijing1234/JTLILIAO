package com.app.nuts.app.mvp.presenter;

import android.app.Application;

import com.app.nuts.app.mvp.contract.ReadContract;
import com.app.nuts.app.mvp.entity.ReadInfo;
import com.app.nuts.base.AppManager;
import com.app.nuts.base.mvp.BasePresenter;
import com.app.nuts.base.rxerrorhandler.core.RxErrorHandler;
import com.app.nuts.base.rxerrorhandler.handler.ErrorHandleSubscriber;
import com.app.nuts.base.rxerrorhandler.handler.RetryWithDelay;
import com.app.nuts.utils.RxUtils;

import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class ReadPresenter extends BasePresenter<ReadContract.Model, ReadContract.View> {
    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;
    private ReadInfo readInfo;

    @Inject
    public ReadPresenter(ReadContract.Model model, ReadContract.View view, RxErrorHandler handler, AppManager appManager, Application application) {
        super(model, view);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }

    public void getReadInfo(Map<String, String> id) {
        mModel.getReadInfo(id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(String readInfosStr) {
//                        readInfo = JSON.parseObject(readInfosStr, ReadInfo.class);
                        mView.showReadInfo(readInfosStr);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.readInfo = null;
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }
}
