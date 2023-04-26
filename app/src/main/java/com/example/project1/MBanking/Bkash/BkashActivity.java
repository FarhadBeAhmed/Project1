package com.example.project1.MBanking.Bkash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project1.FHelper.ConstantValues;
import com.example.project1.R;

public class BkashActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton submitBtn;
    Toolbar toolbar;
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
        setContentView(R.layout.activity_bkash);

        submitBtn = findViewById(R.id.submitBtnId);
        toolbar = findViewById(R.id.toolbar2Id);
        amountEditTxt = findViewById(R.id.AmountId);
        numberEditTxt = findViewById(R.id.numberId);
        shortageView = findViewById(R.id.id_shortageView);
        shortageViewLayout = findViewById(R.id.id_shortageViewLayout);

        radioType = findViewById(R.id.id_radioType);
        cashIn = findViewById(R.id.id_cashIn);
        sendMoney = findViewById(R.id.id_sendMoney);

        cashIn.setChecked(true);
        trxType=cashIn.getText().toString();

        toolbar.setTitle("Bkash");
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.bkashColor)));

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
                    case R.id.id_cashIn:
                        // do operations specific to this selection
                        trxType = cashIn.getText().toString();

                        break;
                    case R.id.id_sendMoney:
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

        if (id == R.id.submitBtnId) {// if(newBalance>=0) {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(BkashActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.mbanking_submit_dialogue, null);

        SendName = dialogView.findViewById(R.id.id_nameDialogue);
        sendNumber = dialogView.findViewById(R.id.id_numberDialogue);
        name1 = dialogView.findViewById(R.id.id_name1);
        name2 = dialogView.findViewById(R.id.id_name2);
        sendingAmount = dialogView.findViewById(R.id.id_amountViewInDialogue);
        newBalanceView = dialogView.findViewById(R.id.id_newBalance);
        sBtn = dialogView.findViewById(R.id.id_submitBtn);
        closeBtn = dialogView.findViewById(R.id.id_closeDialogueBtn);

        name1.setText("Bkash ");
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