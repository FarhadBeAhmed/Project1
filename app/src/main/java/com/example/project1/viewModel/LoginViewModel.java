package com.example.project1.viewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project1.service.model.requestBody.LoginRequestBody;
import com.example.project1.service.model.responseBody.LoginResponse;
import com.example.project1.service.repository.ILoginResponse;
import com.example.project1.service.repository.LoginRepo;


public class LoginViewModel extends ViewModel {
    MutableLiveData<Integer>mProgressbarLiveData=new MutableLiveData<>();
    MutableLiveData<String>mLoginLiveData=new MutableLiveData<>();
    MutableLiveData<String>mPinLiveData=new MutableLiveData<>();

    LoginRepo loginRepo;

    public LoginViewModel() {
        mLoginLiveData.postValue("");
        mPinLiveData.postValue("Enter Pin");
        mProgressbarLiveData.postValue(View.INVISIBLE);
        loginRepo=new LoginRepo();
    }
    public void login(String username,String pass,String device){
        mProgressbarLiveData.postValue(View.VISIBLE);
        mLoginLiveData.postValue("Checking..");
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
        mPinLiveData.postValue("Checking..");
        loginRepo.pinRemote(new LoginRequestBody(username, pass,device), new ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                mProgressbarLiveData.postValue(View.INVISIBLE);
                if (loginResponse.getError()==0) {
                    mPinLiveData.postValue(loginResponse.getError().toString());
                }else {
                    mPinLiveData.postValue(loginResponse.getMsg().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressbarLiveData.postValue(View.INVISIBLE);
                mPinLiveData.postValue("Login Failed due to "+t.getLocalizedMessage());

            }
        });
    }

    public LiveData<Integer>getProgress(){
        return mProgressbarLiveData;
    }
    public LiveData<String>getLogin(){
        return mLoginLiveData;
    }
    public LiveData<String>getPin(){
        return mPinLiveData;
    }

}
