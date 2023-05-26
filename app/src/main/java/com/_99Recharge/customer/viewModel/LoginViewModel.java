package com._99Recharge.customer.viewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com._99Recharge.customer.service.model.requestBody.LoginRequestBody;
import com._99Recharge.customer.service.model.responseBody.LoginResponse;
import com._99Recharge.customer.service.model.responseBody.PinChkResponse;
import com._99Recharge.customer.service.repository.ILoginResponse;
import com._99Recharge.customer.service.repository.IPinchk;
import com._99Recharge.customer.service.repository.LoginRepo;


public class LoginViewModel extends ViewModel {
    MutableLiveData<Integer>mProgressbarLiveData=new MutableLiveData<>();
    MutableLiveData<String>mLoginLiveData=new MutableLiveData<>();
    MutableLiveData<PinChkResponse>mPinLiveData=new MutableLiveData<>();

    LoginRepo loginRepo;

    public LoginViewModel() {
        mLoginLiveData.postValue("");
        mProgressbarLiveData.postValue(View.INVISIBLE);
        loginRepo=new LoginRepo();
    }
    public void login(String username,String pass,String device){
        mProgressbarLiveData.postValue(View.VISIBLE);
        loginRepo.loginRemote(new LoginRequestBody(username, pass, device), new ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                mProgressbarLiveData.postValue(View.INVISIBLE);
                if (loginResponse.getError()==0) {
                    mLoginLiveData.postValue(loginResponse.getError().toString());
                }else {
                    mLoginLiveData.postValue(loginResponse.getMsg().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressbarLiveData.postValue(View.INVISIBLE);
                mLoginLiveData.postValue("Login Failed due to "+t.getLocalizedMessage());

            }
        });
    }
    public void pin(String username,String pass,String device){
        mProgressbarLiveData.postValue(View.VISIBLE);
        loginRepo.pinRemote(new LoginRequestBody(username, pass,device), new IPinchk() {
            @Override
            public void onResponse(PinChkResponse loginResponse) {
                mProgressbarLiveData.postValue(View.INVISIBLE);
                if (loginResponse.getError()==0) {
                    mPinLiveData.postValue(loginResponse);
                }else {
                    mPinLiveData.postValue(loginResponse);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressbarLiveData.postValue(View.INVISIBLE);

            }
        });
    }

    public LiveData<Integer>getProgress(){
        return mProgressbarLiveData;
    }
    public LiveData<String>getLogin(){
        return mLoginLiveData;
    }
    public LiveData<PinChkResponse>getPin(){
        return mPinLiveData;
    }

}
