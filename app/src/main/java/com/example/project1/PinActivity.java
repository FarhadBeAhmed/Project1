package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.project1.FHelper.API;
import com.example.project1.FHelper.ConstantValues;
import com.example.project1.FHelper.User;
import com.example.project1.databinding.ActivityPinBinding;
import com.google.android.material.textfield.TextInputEditText;

public class PinActivity extends AppCompatActivity implements View.OnClickListener {

    API api;
    User user;
    ActivityPinBinding binding;
    String givenPin;
    View thisView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        thisView= findViewById(R.id.pinActivity);
        //String url= SessionHandler.getStaticDomain();



        api= ConstantValues.getAPI();
        user =  new User();

        binding.pinBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.pinBtn){
            String userID="",password="";
            //givenPin= pin.getText().toString();
            if(!user.getUsername().equalsIgnoreCase("")) {
                userID = user.getUsername().toString();
                password = user.getPassword().toString();
            }else {
                userID = LoginActivity.userId;
                password = LoginActivity.pass;
            }

            if (ConstantValues.validation(binding.pNameId)){
                //if (!user.getUsername().isEmpty())
                startActivity(new Intent(this,DashboardActivity.class));
            }



        }
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