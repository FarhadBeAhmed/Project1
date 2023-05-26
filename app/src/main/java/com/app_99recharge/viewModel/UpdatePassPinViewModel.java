package com.app_99recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app_99recharge.service.FHelper.ProjectApp;
import com.app_99recharge.service.model.requestBody.PassPinRequest;
import com.app_99recharge.service.model.responseBody.CommonResponse;
import com.app_99recharge.service.repository.IPassPinnResponse;
import com.app_99recharge.service.repository.PassPinRepo;
import com.app_99recharge.util.CommonTask;
import com.app_99recharge.util.ConstantValues;
import com.app_99recharge.view.ui.LoginActivity;

public class UpdatePassPinViewModel extends ViewModel {

    PassPinRepo passPinRepo;
    String user;
    MutableLiveData<CommonResponse>mPinPass=new MutableLiveData<>();

    public UpdatePassPinViewModel(){
        passPinRepo=new PassPinRepo();
    }

    public void updatePinPass(String old_number,String new_number,String confirm_number,String type){

        if (!CommonTask.getPreferences(ProjectApp.getContext(), ConstantValues.user.USER_ID).equals("")){
            user=CommonTask.getPreferences(ProjectApp.getContext(), ConstantValues.user.USER_ID);

        }else if (!LoginActivity.userId.equals("")){
            user=LoginActivity.userId;
        }
        passPinRepo.updatePinPass(new PassPinRequest(user,old_number, new_number, confirm_number, type), new IPassPinnResponse() {
            @Override
            public void onResponse(CommonResponse response) {
                mPinPass.postValue(response);
            }

            @Override
            public void onFailure(Throwable t) {
                mPinPass.postValue(new CommonResponse());
            }
        });

    }

    public LiveData<CommonResponse> getData(){
        return mPinPass;
    }


}
