package com.example.project1.allFragmnts;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.FHelper.API;
import com.example.project1.FHelper.ConstantValues;
import com.example.project1.FHelper.User;
import com.example.project1.MobileRecharge.ContactListActivity;
import com.example.project1.MobileRecharge.RechargeSubmitFragment;
import com.example.project1.ModelClasses.ContactModel;
import com.example.project1.R;
import com.google.android.material.snackbar.Snackbar;

public class FlexiLoadFragment extends Fragment implements View.OnClickListener {

    ImageView operatorLogo;
    ImageView operatorEditLogo;
    ImageButton contactBtnId;
    private EditText amount, number;
    RadioGroup radioGroup;
    RadioButton prepaidBtn;
    RadioButton postpaidBtn;
    RadioButton skittoBtn;
    TextView balanceView;
    TextView shortageView;
    LinearLayout shortageViewLayout;
    double operatorCode = 0, balance = 0, newBalance = 0, charge = 0;
    String NumberType = "", operator = "";
    int numberValidation = 0;
    RelativeLayout allOperators;
    AppCompatButton flexiloadSubmitBtn;
    View view1;
    String selectedNumber;

    static API api;
    static User user;

    public FlexiLoadFragment() {// Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flexi_load, container, false);

        number = view.findViewById(R.id.numberId);
        operatorLogo = view.findViewById(R.id.operatorLogoId);
        operatorEditLogo = view.findViewById(R.id.operatorEditLogoId);
        amount = view.findViewById(R.id.AmountId);
        prepaidBtn = view.findViewById(R.id.radioPrepaidBtn);
        postpaidBtn = view.findViewById(R.id.radioPostBtn);
        skittoBtn = view.findViewById(R.id.radioSkittoBtn);
        radioGroup = view.findViewById(R.id.operatorsId);
        balanceView = view.findViewById(R.id.id_netBalance);
        shortageView = view.findViewById(R.id.id_shortageView);
        shortageViewLayout = view.findViewById(R.id.id_shortageViewLayout);
        contactBtnId = view.findViewById(R.id.contactBtnLogoId);

        view1 = view.findViewById(R.id.flexiload_fragment);


        allOperators = view.findViewById(R.id.mLogosId);
        flexiloadSubmitBtn = view.findViewById(R.id.flexiSubmitId);
        prepaidBtn.setChecked(true);
        NumberType = prepaidBtn.getText().toString();

        api = ConstantValues.getAPI();
        user = new User();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            selectedNumber = getArguments().getString("arg").trim();
            selectedNumber=selectedNumber.replaceAll("\\s+","");
            selectedNumber= selectedNumber.replaceAll("-","");
            numVal(selectedNumber);
            number.setText(selectedNumber);

        }

           /* if(getArguments().getString("arg")!=null){
                selectedNumber=getArguments().getString("arg");
                number.setText(selectedNumber);
            }*/


        allOperators.setOnClickListener(this);
        flexiloadSubmitBtn.setOnClickListener(this);
        contactBtnId.setOnClickListener(this);

        balanceView.setText(balance + "৳");

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String num = number.getText().toString();
                char c;
                if (num.length() != 0) {
                    if (num.length() == 3) {
                        if (charSequence.charAt(0) == '0' && charSequence.charAt(1) == '1') {
                            c = charSequence.charAt(2);
                            if (c == '8') {
                                numberValidation = 1;
                                operatorLogo.setBackgroundResource(R.drawable.robi_logo2);
                                operatorEditLogo.setBackgroundResource(R.drawable.choperator);

                                operatorCode = 18;
                                operator = "robi";
                                skittoBtn.setVisibility(View.GONE);
                            } else if (c == '4' || c == '9') {
                                numberValidation = 1;
                                operatorLogo.setBackgroundResource(R.drawable.banglalink_logo2);
                                operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                                if (c == '4') {
                                    skittoBtn.setVisibility(View.GONE);
                                    operatorCode = 14;
                                } else {
                                    skittoBtn.setVisibility(View.GONE);
                                    operatorCode = 19;
                                }

                                operator = "bl";
                            } else if (c == '3' || c == '7') {
                                numberValidation = 1;
                                operatorLogo.setBackgroundResource(R.drawable.gp_logo);
                                operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                                if (c == '3') {
                                    skittoBtn.setVisibility(View.VISIBLE);
                                    operatorCode = 13;
                                } else {
                                    skittoBtn.setVisibility(View.VISIBLE);
                                    operatorCode = 17;
                                }
                                operator = "gp";
                            } else if (c == '6') {
                                numberValidation = 1;
                                operatorLogo.setBackgroundResource(R.drawable.airtel_logo2);
                                operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                                operatorCode = 16;
                                operator = "air";
                                skittoBtn.setVisibility(View.GONE);
                            } else if (c == '5') {
                                numberValidation = 1;
                                operatorLogo.setBackgroundResource(R.drawable.teletalk_logo2);
                                operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                                operatorCode = 15;
                                operator = "tel";
                                skittoBtn.setVisibility(View.GONE);

                            } else {
                                Toast.makeText(getContext(), "This is not Valid Number", Toast.LENGTH_SHORT).show();
                                numberValidation = 0;
                                skittoBtn.setVisibility(View.GONE);
                            }
                        } else {
                            numberValidation = 0;
                            skittoBtn.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "This is not Valid Number", Toast.LENGTH_SHORT).show();
                        }


                    } else if (num.length() < 3) {
                        operatorCode = 0;
                        skittoBtn.setVisibility(View.GONE);
                        operatorLogo.setBackgroundResource(R.color.Trans);
                        operatorEditLogo.setBackgroundResource(R.color.Trans);
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioPrepaidBtn:
                        // do operations specific to this selection
                        NumberType = prepaidBtn.getText().toString();

                        break;
                    case R.id.radioPostBtn:
                        // do operations specific to this selection
                        NumberType = "postpaid";
                        break;
                    case R.id.radioSkittoBtn:
                        // do operations specific to this selection
                        NumberType = "Skitto";
                        break;
                }
            }
        });
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    double amnt = Double.parseDouble(charSequence.toString());
                    newBalance = balance - (amnt + charge);
                    if (newBalance < 0) {
                        shortageView.setText(Math.abs(newBalance) + "");
                        shortageViewLayout.setVisibility(View.VISIBLE);
                    } else {
                        shortageViewLayout.setVisibility(View.GONE);
                    }
                } else {
                    shortageViewLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }

    private void numVal(String num) {
        char c;
        if (num.length() != 0) {
            if (num.charAt(0) == '0' && num.charAt(1) == '1') {
                c = num.charAt(2);
                if (c == '8') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.robi_logo2);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);

                    operatorCode = 18;
                    operator = "robi";
                    skittoBtn.setVisibility(View.GONE);
                } else if (c == '4' || c == '9') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.banglalink_logo2);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                    if (c == '4') {
                        skittoBtn.setVisibility(View.GONE);
                        operatorCode = 14;
                    } else {
                        skittoBtn.setVisibility(View.GONE);
                        operatorCode = 19;
                    }

                    operator = "bl";
                } else if (c == '3' || c == '7') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.gp_logo);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                    if (c == '3') {
                        skittoBtn.setVisibility(View.VISIBLE);
                        operatorCode = 13;
                    } else {
                        skittoBtn.setVisibility(View.VISIBLE);
                        operatorCode = 17;
                    }
                    operator = "gp";
                } else if (c == '6') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.airtel_logo2);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                    operatorCode = 16;
                    operator = "air";
                    skittoBtn.setVisibility(View.GONE);
                } else if (c == '5') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.teletalk_logo2);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                    operatorCode = 15;
                    operator = "tel";
                    skittoBtn.setVisibility(View.GONE);

                } else {
                    Toast.makeText(getContext(), "This is not Valid Number", Toast.LENGTH_SHORT).show();
                    numberValidation = 0;
                    skittoBtn.setVisibility(View.GONE);
                }
            } else if (num.charAt(0) == '+' && num.charAt(1) == '8' && num.charAt(2) == '8') {
                c = num.charAt(5);//+88016
                if (c == '8') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.robi_logo2);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);

                    operatorCode = 18;
                    operator = "robi";
                    skittoBtn.setVisibility(View.GONE);
                } else if (c == '4' || c == '9') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.banglalink_logo2);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                    if (c == '4') {
                        skittoBtn.setVisibility(View.GONE);
                        operatorCode = 14;
                    } else {
                        skittoBtn.setVisibility(View.GONE);
                        operatorCode = 19;
                    }

                    operator = "bl";
                } else if (c == '3' || c == '7') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.gp_logo);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                    if (c == '3') {
                        skittoBtn.setVisibility(View.VISIBLE);
                        operatorCode = 13;
                    } else {
                        skittoBtn.setVisibility(View.VISIBLE);
                        operatorCode = 17;
                    }
                    operator = "gp";
                } else if (c == '6') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.airtel_logo2);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                    operatorCode = 16;
                    operator = "air";
                    skittoBtn.setVisibility(View.GONE);
                } else if (c == '5') {
                    numberValidation = 1;
                    operatorLogo.setBackgroundResource(R.drawable.teletalk_logo2);
                    operatorEditLogo.setBackgroundResource(R.drawable.choperator);
                    operatorCode = 15;
                    operator = "tel";
                    skittoBtn.setVisibility(View.GONE);

                } else {
                    Toast.makeText(getContext(), "This is not Valid Number", Toast.LENGTH_SHORT).show();
                    numberValidation = 0;
                    skittoBtn.setVisibility(View.GONE);
                }
            } else {
                numberValidation = 0;
                skittoBtn.setVisibility(View.GONE);
                Toast.makeText(getContext(), "This is not Valid Number", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        String num1 = number.getText().toString();


        switch (id) {
            case R.id.mLogosId:
                if (num1.length() >= 3 && numberValidation == 1) {
                    showDialog();
                }
                break;
            case R.id.flexiSubmitId:
                //  if (newBalance > 0) {
                String flexiAmount = amount.getText().toString();
                String flexiNumber = number.getText().toString();
                if (flexiNumber.length() == 11 && !flexiAmount.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("number", flexiNumber);
                    bundle.putString("amount", flexiAmount);
                    bundle.putString("type", NumberType);
                    bundle.putString("operator", operator);
                    bundle.putString("newBalance", String.valueOf(newBalance));
                    RechargeSubmitFragment rechargeSubmitFragment = new RechargeSubmitFragment();
                    rechargeSubmitFragment.setArguments(bundle);
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.id_fragment_Container, rechargeSubmitFragment, "rechargeSubmitFragment").addToBackStack("rechargeSubmitFragment").commit();

                    //  }
                }
                break;
            case R.id.contactBtnLogoId:
                checkPermission();
                break;
        }

    }


    private void showDialog() {

        RelativeLayout dialogGPBtn;
        RelativeLayout dialogRobiBtn;
        RelativeLayout dialogBLBtn;
        RelativeLayout dialogAirBtn;
        RelativeLayout dialogTelBtn;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.mobile_operators_dialog, null);

        dialogGPBtn = dialogView.findViewById(R.id.dialogGPBtnId);
        dialogRobiBtn = dialogView.findViewById(R.id.dialogRobiBtnId);
        dialogBLBtn = dialogView.findViewById(R.id.dialogBLBtnId);
        dialogAirBtn = dialogView.findViewById(R.id.dialogairBtnId);
        dialogTelBtn = dialogView.findViewById(R.id.dialogTelBtnId);

        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        dialogGPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                operatorLogo.setBackgroundResource(R.drawable.gp_logo);
                operatorCode = 17;

            }
        });
        dialogRobiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                operatorLogo.setBackgroundResource(R.drawable.robi_logo2);
                operatorCode = 18;

            }
        });
        dialogBLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                operatorLogo.setBackgroundResource(R.drawable.banglalink_logo2);
                operatorCode = 19;

            }
        });
        dialogAirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                operatorLogo.setBackgroundResource(R.drawable.airtel_logo2);
                operatorCode = 16;

            }
        });
        dialogTelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                operatorLogo.setBackgroundResource(R.drawable.teletalk_logo2);
                operatorCode = 15;

            }
        });


    }


    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(requireContext()
                , Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity()
                    , new String[]{Manifest.permission.READ_CONTACTS}, 100);
        } else {
            startActivity(new Intent(getContext(), ContactListActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check condition
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            startActivity(new Intent(getContext(), ContactListActivity.class));

        } else {
            Toast.makeText(getContext(), "Permission Denied.", Toast.LENGTH_LONG).show();
            checkPermission();
        }
    }

}