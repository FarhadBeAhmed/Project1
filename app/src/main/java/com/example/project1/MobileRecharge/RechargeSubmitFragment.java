package com.example.project1.MobileRecharge;

import static android.view.MotionEvent.ACTION_BUTTON_PRESS;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.FHelper.API;
import com.example.project1.FHelper.ConstantValues;
import com.example.project1.FHelper.User;
import com.example.project1.R;
import com.google.android.material.snackbar.Snackbar;

public class RechargeSubmitFragment extends Fragment {

    EditText pin;
    ImageButton submit;
    String number, amount, type, operator,newBalance;
    double charge = 00, totalAmount = 00;
    TextView numberView, amountView, chargeView, totalView;
    static API api;
    static User user;

    public RechargeSubmitFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recharge_submit, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pin = view.findViewById(R.id.id_pin);
        submit = view.findViewById(R.id.id_submit);
        numberView = view.findViewById(R.id.id_text_view_number);
        amountView = view.findViewById(R.id.id_amount_txtView);
        chargeView = view.findViewById(R.id.id_chargeTxtView);
        totalView = view.findViewById(R.id.id_totalAmountTxtView);
        api = ConstantValues.getAPI();
        user = new User();

        submit.setClickable(false);
        submit.setBackgroundResource(R.drawable.ic_baseline_arrow_forward_disable);
        submit.setOnClickListener(this::buttonClicked);

        Bundle bundle = this.getArguments();

        if (bundle.getString("number") != null) {
            number = bundle.getString("number");
            amount = bundle.getString("amount");
            type = bundle.getString("type");
            operator = bundle.getString("operator");
            newBalance = bundle.getString("newBalance");
            numberView.setText(number);
            amountView.setText(amount);
        }

        pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String txt = pin.getText().toString();
                if (charSequence.length() >= 4) {
                    submit.setClickable(true);
                    submit.setBackgroundResource(R.drawable.ic_baseline_arrow_forward_24);
                } else {
                    submit.setClickable(false);
                    submit.setBackgroundResource(R.drawable.ic_baseline_arrow_forward_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        chargeView.setText("+" + charge + "৳");
        totalAmount = Integer.parseInt(amount) + charge;
        totalView.setText(totalAmount + "৳");

    }

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    private void buttonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
        String user_id = user.getUsername();
        String pass = user.getPassword();

        if (!flexiAmount.equals("") &&  !flexiNumber.equals("") && !user_id.equals("") && !pass.equals("") ){

        }else {

        }



    }
}
