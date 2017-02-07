package com.app.nuts.app.mvp.model;

import com.app.nuts.app.mvp.contract.ReadContract;
import com.app.nuts.app.mvp.model.api.cache.CacheManager;
import com.app.nuts.app.mvp.model.api.service.ServiceManager;
import com.app.nuts.base.di.scope.ActivityScope;
import com.app.nuts.base.mvp.BaseModel;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 王立强 on 2017/2/4.
 */
@ActivityScope
public class ReadModel extends BaseModel<ServiceManager, CacheManager> implements ReadContract.Model {

    @Inject
    public ReadModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }


    @Override
    public Observable<String> getReadInfo(Map<String, String> id) {
        Observable<String> movieInfo = mServiceManager.getCommonService()
                .getReadInfo(id);
        return movieInfo;
    }
}
