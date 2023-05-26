package com.app_99recharge.view.adaptars;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.app_99recharge.R;
import com.app_99recharge.databinding.MemAccSinglerowBinding;
import com.app_99recharge.service.model.responseBody.MemAccActionResponse;
import com.app_99recharge.service.model.responseBody.MemberAccountingData;
import com.app_99recharge.util.CommonTask;
import com.app_99recharge.util.ConstantValues;
import com.app_99recharge.view.ui.LoginActivity;
import com.app_99recharge.view.ui.PinActivity;
import com.app_99recharge.viewModel.MemAccActionViewModel;
import com.app_99recharge.viewModel.MemberAccountingViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MemberAccountingAdapter extends RecyclerView.Adapter<MemberAccountingAdapter.ViewHolder> {

    Context context;
    ArrayList<MemberAccountingData> accountingData = new ArrayList<>();
    String myID = "";

    MemAccSinglerowBinding binding;
    MemAccActionViewModel viewModel;
    MemberAccountingViewModel memberAccountingViewModel;
    KProgressHUD kProgressHUD;
    boolean isShowed = false;

    public MemberAccountingAdapter(Context context, ArrayList<MemberAccountingData> accountingData) {
        this.context = context;
        this.accountingData = accountingData;

    }

    @NonNull
    @Override
    public MemberAccountingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MemAccSinglerowBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAccountingAdapter.ViewHolder holder, int position) {
        MemberAccountingData data = accountingData.get(position);
        holder.name.setText(data.getUser());
        holder.number.setText(data.getMobile());
        holder.bal.setText(data.getNetBal());
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MemAccActionViewModel.class);
        memberAccountingViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MemberAccountingViewModel.class);
        if (!CommonTask.getPreferences(context, ConstantValues.user.USER_ID).equals("") && PinActivity.pinNumber.getPin() != null) {
            myID = CommonTask.getPreferences(context, ConstantValues.user.USER_ID);

        } else if (!LoginActivity.userId.equals("") && PinActivity.pinNumber.getPin() != null) {
            myID = LoginActivity.userId;
        }

        holder.addButton.setOnClickListener(v -> {
             isShowed = false;
            action(v, data, "Add");

        });
        holder.returnButton.setOnClickListener(v -> {
             isShowed = false;
            action(v, data, "Return");

        });

    }


    private void action(View v, MemberAccountingData data, String tran) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.balance_trnsfer_dialog, viewGroup, false);
        TextInputLayout amount = dialogView.findViewById(R.id.amount);
        TextInputLayout remark = dialogView.findViewById(R.id.remark);
        TextInputLayout pin = dialogView.findViewById(R.id.pin);
        RelativeLayout submit = dialogView.findViewById(R.id.submitButton);
        RelativeLayout cancel = dialogView.findViewById(R.id.cancelButton);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        submit.setOnClickListener(v1 -> {
            alertDialog.dismiss();
            controlProgressBar(true);
            if (!myID.equals("") && CommonTask.validation(amount, pin)) {
                viewModel.callForMemAcc(myID, data.getSerial(), amount.getEditText().getText().toString(), remark.getEditText().getText().toString(), pin.getEditText().getText().toString(), tran);
            }

            viewModel.getLiveData().observe((LifecycleOwner) context, new Observer<MemAccActionResponse>() {
                @Override
                public void onChanged(MemAccActionResponse memAccActionResponse) {
                    controlProgressBar(false);
                    if (memAccActionResponse.getError() == 0) {
                        if (!isShowed) {
                            isShowed = true;
                            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Success")
                                    .setContentText(memAccActionResponse.getMsg().toString())
                                    .setConfirmText(" OK ")
                                    .show();
                            memberAccountingViewModel.callForMemAcc(myID, "");
                        }


                    } else {
                        if (!isShowed) {
                            isShowed = true;
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Failed")
                                    .setContentText(memAccActionResponse.getMsg().toString())
                                    .setConfirmText(" OK ")
                                    .show();
                        }
                    }
                }
            });

        });

        cancel.setOnClickListener(v1 -> {
            alertDialog.dismiss();
        });


    }

    public void controlProgressBar(boolean isShowProgressBar) {
        if (isShowProgressBar) {
            try {
                if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
                    this.kProgressHUD.dismiss();
                }
                kProgressHUD = KProgressHUD.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            } catch (Exception e) {
            }
        } else if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
            this.kProgressHUD.dismiss();
        }
    }

    @Override
    public int getItemCount() {
        return accountingData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, number, bal;
        RelativeLayout addButton, returnButton;

        public ViewHolder(@NonNull MemAccSinglerowBinding itemView) {
            super(itemView.getRoot());
            name = itemView.name;
            number = itemView.number;
            bal = itemView.balance;
            addButton = itemView.addButton;
            returnButton = itemView.returnButton;

        }
    }
}
