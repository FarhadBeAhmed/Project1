package com._99Recharge.service.repository;

import com._99Recharge.service.model.requestBody.PackagesRequest;
import com._99Recharge.service.model.responseBody.PackageResponse;
import com._99Recharge.service.network.ApiService;
import com._99Recharge.service.network.RetrofitClientInstance;

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
