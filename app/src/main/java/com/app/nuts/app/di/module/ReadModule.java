package com.app.nuts.app.di.module;

import com.app.nuts.app.mvp.contract.ReadContract;
import com.app.nuts.app.mvp.model.ReadModel;
import com.app.nuts.base.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 王立强 on 2017/2/4.
 */
@Module
public class ReadModule {
    private ReadContract.View view;

    /**
     * 构建Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public ReadModule(ReadContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ReadContract.View provideReadView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    ReadContract.Model provideReadModel(ReadModel model){
        return model;
    }
}
