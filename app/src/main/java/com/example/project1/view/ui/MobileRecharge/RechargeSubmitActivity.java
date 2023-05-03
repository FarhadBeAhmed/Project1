package com.example.project1.view.ui.MobileRecharge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.databinding.ActivityRechargeSubmitBinding;
import com.example.project1.service.FHelper.API;
import com.example.project1.service.FHelper.User;
import com.example.project1.util.ConstantValues;

public class RechargeSubmitActivity extends AppCompatActivity {


    String number, amount, type, operator,newBalance;
    double charge = 00, totalAmount = 00;


    ActivityRechargeSubmitBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRechargeSubmitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.idSubmit.setClickable(false);
        binding.idSubmit.setBackgroundResource(R.drawable.ic_baseline_arrow_forward_disable);
        binding.idSubmit.setOnClickListener(this::buttonClicked);
        Intent intent = getIntent();
        if (intent!=null){
            number=intent.getStringExtra(ConstantValues.Flexiload.NUMBER);
            amount=intent.getStringExtra(ConstantValues.Flexiload.AMOUNT);
            type=intent.getStringExtra(ConstantValues.Flexiload.TYPE);
            operator=intent.getStringExtra(ConstantValues.Flexiload.OPERATOR);
            newBalance=intent.getStringExtra(ConstantValues.Flexiload.NEW_BALANCE);

            binding.idTextViewNumber.setText(number);
            binding.idAmountTxtView.setText(amount);
        }

        binding.idPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String txt =  binding.idPin.getText().toString();
                if (charSequence.length() >= 4) {
                    binding.idSubmit.setClickable(true);
                    binding.idSubmit.setBackgroundResource(R.drawable.ic_baseline_arrow_forward_24);
                } else {
                    binding.idSubmit.setClickable(false);
                    binding.idSubmit.setBackgroundResource(R.drawable.ic_baseline_arrow_forward_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        binding.idChargeTxtView.setText("+" + charge + "৳");
        totalAmount = Integer.parseInt(amount) + charge;
        binding.idTotalAmountTxtView.setText(totalAmount + "৳");
    }


    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    private void buttonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RechargeSubmitActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.recharge_submit_dialogue, null);

        AppCompatButton submitBtn = dialogView.findViewById(R.id.id_submitBtn);
        ImageView closeDialogueBtn = dialogView.findViewById(R.id.id_closeDialogueBtn);
        TextView dialogueTotal = dialogView.findViewById(R.id.id_totalViewInDialogue);
        TextView dialogueBalance = dialogView.findViewById(R.id.id_newBalance);
        TextView dialogueType = dialogView.findViewById(R.id.id_typeInDialogue);
        TextView dialogueOperator = dialogView.findViewById(R.id.id_operatorInDialogue);
        TextView dialogueName = dialogView.findViewById(R.id.id_nameDialogue);
        TextView dialogueNumber = dialogView.findViewById(R.id.id_numberDialogue);

        builder.setView(dialogView);
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        dialogueTotal.setText(totalAmount+"৳");
        dialogueType.setText(type);
        dialogueBalance.setText(newBalance);
        dialogueOperator.setText(operator);
        dialogueName.setText(number);
        dialogueNumber.setText(number);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });

        closeDialogueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void submitData() {

        String flexiAmount = amount;
        String flexiNumber = number;
        String user_id = "user.getUsername()";
        String pass = "user.getPassword()";

        if (!flexiAmount.equals("") &&  !flexiNumber.equals("") && !user_id.equals("") && !pass.equals("") ){

        }else {

        }



    }
}