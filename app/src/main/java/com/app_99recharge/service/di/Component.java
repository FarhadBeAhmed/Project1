package com.app_99recharge.service.di;


import com.app_99recharge.service.network.RetrofitClientInstance;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {RetrofitClientInstance.class})
public interface Component {

    public void inject(RetrofitClientInstance retrofitClientInstance);
}
