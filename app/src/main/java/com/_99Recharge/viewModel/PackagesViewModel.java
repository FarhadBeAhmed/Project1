package com._99Recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com._99Recharge.service.model.requestBody.PackagesRequest;
import com._99Recharge.service.model.responseBody.PackageResponse;
import com._99Recharge.service.repository.IPackageResponse;
import com._99Recharge.service.repository.PackagesRepo;

public class PackagesViewModel extends ViewModel {
    MutableLiveData<PackageResponse>mPackages=new MutableLiveData<>();
    PackagesRepo packagesRepo;
    public PackagesViewModel(){
        packagesRepo=new PackagesRepo();


    }

    public void callForPackages(String operator){
        packagesRepo.callForPackages(new PackagesRequest(operator), new IPackageResponse() {
            @Override
            public void onResponse(PackageResponse packageResponse) {
                mPackages.postValue(packageResponse);
            }

            @Override
            public void onFailure(Throwable t) {


            }
        });
    }

    public LiveData<PackageResponse> getData(){
        return mPackages;
    }
}
