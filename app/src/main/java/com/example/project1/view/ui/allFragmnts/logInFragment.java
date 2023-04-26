package com.example.project1.view.ui.allFragmnts;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project1.view.ui.DashboardActivity;
import com.example.project1.R;
import com.example.project1.view.ui.SignUpPage;


public class logInFragment extends Fragment implements View.OnClickListener {

    EditText userName, password;
    TextView signup;
    Button loginButton;


    public logInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        userName = view.findViewById(R.id.usernameId);
        password = view.findViewById(R.id.passwordId);
        signup = view.findViewById(R.id.signupTxtId);
        loginButton = view.findViewById(R.id.logInBtnId);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SignUpPage.class);
                //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(this);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.signupTxtId:
               // intent = new Intent(getContext(), SignUpPage.class);
              //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
             //   startActivity(intent);
            case R.id.logInBtnId:
                intent = new Intent(getContext(), DashboardActivity.class);
                startActivity(intent);

        }

    }
}