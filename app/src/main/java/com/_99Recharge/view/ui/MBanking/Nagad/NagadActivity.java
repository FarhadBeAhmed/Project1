package com._99Recharge.view.ui.MBanking.Nagad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com._99Recharge.R;
import com._99Recharge.service.FHelper.ConstantValues;

public class NagadActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    AppCompatButton submitBtn;
    EditText amountEditTxt, numberEditTxt;
    double balance = 0, newBalance = 0, amount = 0;
    TextView shortageView;
    LinearLayout shortageViewLayout;
    String number;
    RadioGroup radioType;
    RadioButton cashIn;
    RadioButton sendMoney;
    String trxType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nagad);

        submitBtn = findViewById(R.id.submitBtnIdNagad);
        toolbar = findViewById(R.id.nagadToolbarId);
        amountEditTxt = findViewById(R.id.AmountIdNagad);
        numberEditTxt = findViewById(R.id.numberIdNagad);
        shortageView = findViewById(R.id.id_shortageViewNagad);
        shortageViewLayout = findViewById(R.id.id_shortageViewLayoutNagad);

        radioType = findViewById(R.id.id_radioTypeNagad);
        cashIn = findViewById(R.id.id_cashInNagad);
        sendMoney = findViewById(R.id.id_sendMoneyNagad);

        cashIn.setChecked(true);
        trxType=cashIn.getText().toString();



        toolbar.setTitle("Nagad");
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.nagadColorRed)));

        setSupportActionBar(toolbar);

        submitBtn.setOnClickListener(this);
        amountEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    amount = Double.parseDouble(charSequence.toString());
                    newBalance = balance - amount;
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

        radioType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.id_cashInNagad:
                        // do operations specific to this selection
                        trxType = cashIn.getText().toString();

                        break;
                    case R.id.id_sendMoneyNagad:
                        // do operations specific to this selection
                        trxType =sendMoney.getText().toString();
                        break;

                }
            }
        });


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent = null;
        int id = view.getId();

        if (id == R.id.submitBtnIdNagad) {// if(newBalance>=0) {
            number = numberEditTxt.getText().toString();
            if (ConstantValues.validation2(numberEditTxt, amountEditTxt)) {
                showDialog();
            }
            //  }
        }
    }


    @SuppressLint("SetTextI18n")
    private void showDialog() {

        TextView name1;
        TextView name2;
        TextView SendName;
        TextView sendNumber;
        TextView sendingAmount;
        TextView newBalanceView;
        AppCompatButton sBtn;
        ImageView closeBtn;

        AlertDialog.Builder builder = new AlertDialog.Builder(NagadActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.mbanking_submit_dialogue, null);

        SendName = dialogView.findViewById(R.id.id_nameDialogue);
        sendNumber = dialogView.findViewById(R.id.id_numberDialogue);
        name1 = dialogView.findViewById(R.id.id_name1);
        name2 = dialogView.findViewById(R.id.id_name2);
        sendingAmount = dialogView.findViewById(R.id.id_amountViewInDialogue);
        newBalanceView = dialogView.findViewById(R.id.id_newBalance);
        sBtn = dialogView.findViewById(R.id.id_submitBtn);
        closeBtn = dialogView.findViewById(R.id.id_closeDialogueBtn);

        name1.setText("Nagad ");
        name2.setText(trxType);
        SendName.setText(number);
        sendNumber.setText(number);
        sendingAmount.setText(amount+"৳");
        newBalanceView.setText(newBalance + "৳");

        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();

            }
        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();

            }
        });


    }
}