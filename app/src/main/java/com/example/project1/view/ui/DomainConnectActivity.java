package com.example.project1.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.project1.service.FHelper.API;
import com.example.project1.service.FHelper.ConstantValues;
import com.example.project1.service.FHelper.User;
import com.example.project1.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class DomainConnectActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout domainName;
    TextInputEditText domainNameEditText;
    AppCompatButton connectButton;
    View connectPage;
    int urlMatchStatus = 0;
    String deviceID="";
    String domainUrl ="";
    static API api;
    static User user;


    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_connect);
        api= ConstantValues.getAPI();
        user =  new User();

       /* if (!user.getSession().getUserDetails().equals("")) {
            if(user.getSession().isLoggedIn()) {
                startActivity(new Intent(this, PinActivity.class));
            }else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();
        }*/


        domainName = findViewById(R.id.dNameId);
        domainNameEditText = findViewById(R.id.domainNameEditTextId);
        connectPage = findViewById(R.id.ConnectActivity);
        connectButton = findViewById(R.id.contBtn2);

        connectButton.setOnClickListener(this);





        deviceID= Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        domainNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (Patterns.WEB_URL.matcher(Objects.requireNonNull(domainNameEditText.getText()).toString()).matches()) {
                    urlMatchStatus = 1;
                    domainNameEditText.setError(null);
                } else {
                    // otherwise show error of invalid url
                    domainNameEditText.setError("Invalid Url");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }


    @Override
    public void onClick(View view) {
        //------deletable---------
        domainUrl = domainNameEditText.getText().toString();
       // user.getSession().userDomain(domainUrl);
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
        //------------

        int id=view.getId();
        if (id == R.id.contBtn2) {
            domainUrl = domainNameEditText.getText().toString();

            String subUrl= domainUrl.substring(0,4);


            if(subUrl.equals("www.")){
                domainUrl=domainUrl.substring(4,domainUrl.length());
            }


            if (!domainUrl.equals("") && urlMatchStatus==1) {
                checkDomain();
            }else {
                Snackbar.make(connectPage,"Invalid Domain Name !!",Snackbar.LENGTH_SHORT).show();

            }
        }

    }

    private void checkDomain() {

      /*  Json.addRequests(API.domainName(domainUrl).success(response -> {
            try{

                String name=response.getString(Constant.Login.MESSAGE);
                if(name.equals("Domain Verified")) {
                    User.getSession().userDomain(domainUrl);
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }
                Snackbar.make(connectPage,name,Snackbar.LENGTH_SHORT).show();
            }catch (Exception e) {
                e.printStackTrace();
            }


        }));
*/


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            }
            assert imm != null;
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}