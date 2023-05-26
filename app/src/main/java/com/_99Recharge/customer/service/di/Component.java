package com._99Recharge.customer.service.di;


import com._99Recharge.customer.service.network.RetrofitClientInstance;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {RetrofitClientInstance.class})
public interface Component {

    public void inject(RetrofitClientInstance retrofitClientInstance);
}
