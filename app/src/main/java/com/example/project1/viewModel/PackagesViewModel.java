package com.example.project1.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project1.service.model.requestBody.PackagesRequest;
import com.example.project1.service.model.responseBody.DeviceIdResponse;
import com.example.project1.service.model.responseBody.PackageResponse;
import com.example.project1.service.repository.IPackageResponse;
import com.example.project1.service.repository.PackagesRepo;

import java.util.ArrayList;

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
