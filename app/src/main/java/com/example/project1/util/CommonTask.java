package com.example.project1.util;

import android.content.Context;

import com.example.project1.R;
import com.kaopiz.kprogresshud.KProgressHUD;

public class CommonTask {

    KProgressHUD progressDialog ;


    public void controlProgressBar(Context context, boolean isShowProgressBar) {
        if (isShowProgressBar) {
            try {
                if (this.progressDialog != null && this.progressDialog.isShowing()) {
                    this.progressDialog.dismiss();
                }
                progressDialog.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            } catch (Exception e) {
            }
        } else if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }
}
