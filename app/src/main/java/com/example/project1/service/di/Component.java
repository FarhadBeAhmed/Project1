package com.example.project1.service.di;


import com.example.project1.service.network.RetrofitClientInstance;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {RetrofitClientInstance.class})
public interface Component {

    public void inject(RetrofitClientInstance retrofitClientInstance);
}
