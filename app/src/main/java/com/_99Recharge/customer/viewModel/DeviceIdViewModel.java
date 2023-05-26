package com._99Recharge.customer.viewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com._99Recharge.customer.service.model.requestBody.DeviceIdRequest;
import com._99Recharge.customer.service.model.responseBody.DeviceIdResponse;
import com._99Recharge.customer.service.repository.DeviceIdRepo;
import com._99Recharge.customer.service.repository.IDeviceIdResponse;

public class DeviceIdViewModel extends ViewModel {

    DeviceIdRepo deviceIdRepo;
    MutableLiveData<DeviceIdResponse>mDeviceId=new MutableLiveData<>();

    public DeviceIdViewModel(){
        deviceIdRepo=new DeviceIdRepo();
    }
    public void callForDeviceId(String deviceId){
        deviceIdRepo.callForDeviceId(new DeviceIdRequest(deviceId), new IDeviceIdResponse() {
            @Override
            public void onResponse(DeviceIdResponse deviceIdResponse) {
                mDeviceId.postValue(deviceIdResponse);
            }
            @Override
            public void onFailure(Throwable t) {
                mDeviceId.postValue(new DeviceIdResponse());
            }
        });
    }

    public LiveData<DeviceIdResponse> getData(){
        return mDeviceId;
    }

}
