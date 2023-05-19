package com._99Recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com._99Recharge.service.FHelper.ProjectApp;
import com._99Recharge.service.model.requestBody.PassPinRequest;
import com._99Recharge.service.model.responseBody.CommonResponse;
import com._99Recharge.service.repository.IPassPinnResponse;
import com._99Recharge.service.repository.PassPinRepo;
import com._99Recharge.util.CommonTask;
import com._99Recharge.util.ConstantValues;
import com._99Recharge.view.ui.LoginActivity;

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
