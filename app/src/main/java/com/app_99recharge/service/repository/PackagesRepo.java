package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.requestBody.PackagesRequest;
import com.app_99recharge.service.model.responseBody.PackageResponse;
import com.app_99recharge.service.network.ApiService;
import com.app_99recharge.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackagesRepo {

    @Inject
    ApiService apiService;

    public void callForPackages(PackagesRequest packagesRequest, IPackageResponse iPackageResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<PackageResponse>initiateCall=   apiService.load_packages(packagesRequest);
        initiateCall.enqueue(new Callback<PackageResponse>() {
            @Override
            public void onResponse(Call<PackageResponse> call, Response<PackageResponse>response) {
                if (response.isSuccessful()){
                    iPackageResponse.onResponse(response.body());
                }else {
                    iPackageResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<PackageResponse> call, Throwable t) {
                iPackageResponse.onFailure(t);
            }
        });
    }



}
