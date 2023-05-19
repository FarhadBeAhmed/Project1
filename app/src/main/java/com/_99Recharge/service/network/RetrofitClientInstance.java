package com._99Recharge.service.network;


import com._99Recharge.service.CommonUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitClientInstance {
    public static Retrofit retrofit;


    @Singleton
    @Provides
    public ApiService getApiServiceInterface(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }


    @Singleton
    @Provides
    public static Retrofit getRetroInstance(){
        if (retrofit== null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(CommonUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
