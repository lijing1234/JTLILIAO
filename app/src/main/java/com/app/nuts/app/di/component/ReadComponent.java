package com.app.nuts.app.di.component;

import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.di.module.ReadModule;
import com.app.nuts.app.mvp.ui.ReadActivity;
import com.app.nuts.base.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by 王立强 on 2017/2/4.
 */

@ActivityScope
@Component(modules = ReadModule.class,dependencies = AppComponent.class)
public interface ReadComponent {
    void inject(ReadActivity activity);
}
