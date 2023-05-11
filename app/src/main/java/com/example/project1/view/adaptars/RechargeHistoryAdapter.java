package com.example.project1.view.adaptars;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.example.project1.service.CommonUrl;
import com.example.project1.service.model.responseBody.PackDatum;
import com.example.project1.service.model.responseBody.RechargeHistoryData;
import com.example.project1.util.ConstantValues;
import com.example.project1.view.ui.MobileRecharge.FlexiloadActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RechargeHistoryAdapter extends RecyclerView.Adapter<RechargeHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<RechargeHistoryData>rechargeHistoryData;
    private LayoutInflater mInflater;
    @LayoutRes
    int resLayout;

    public RechargeHistoryAdapter(Context context, ArrayList<RechargeHistoryData>rechargeHistoryData, @LayoutRes int resLayout) {
        this.context = context;
        this.rechargeHistoryData = rechargeHistoryData;
        this.resLayout = resLayout;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(resLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RechargeHistoryData historyData=rechargeHistoryData.get(position);
        holder.amount.setText(historyData.getAmount());
        holder.id_date.setText(historyData.getDate());
        holder.mNumberId.setText(historyData.getPhone());
        holder.transactionID.setText(historyData.getTransactionId());
        holder.id_Time.setText(historyData.getTime());


        holder.id_status.setText(historyData.getStatus());
        if (historyData.getStatus().equals("Pending")){ holder.id_status.setTextColor(context.getResources().getColor(R.color.pending)); }
        if (historyData.getStatus().equals("Success")){ holder.id_status.setTextColor(context.getResources().getColor(R.color.success)); }
        if (historyData.getStatus().equals("Cancelled")){ holder.id_status.setTextColor(context.getResources().getColor(R.color.cancelled)); }
        if (historyData.getStatus().equals("Failed")){ holder.id_status.setTextColor(context.getResources().getColor(R.color.failed)); }
        if (historyData.getStatus().equals("Waiting")){ holder.id_status.setTextColor(context.getResources().getColor(R.color.waiting)); }
        /*Picasso.get()  //Here, this is context.
                .load(CommonUrl.BASE_URL+"/images/"+packagesModel.getImg()+".jpg")  //Url of the image to load.
                .into(holder.opImg);*/

    }

    @Override
    public int getItemCount() {
        return rechargeHistoryData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amount;
        TextView id_status;
        TextView mNumberId;
        TextView transactionID;
        TextView id_date;
        TextView id_Time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_status=itemView.findViewById(R.id.id_status);
            mNumberId=itemView.findViewById(R.id.mNumberId);
            transactionID=itemView.findViewById(R.id.transactionID);
            id_date=itemView.findViewById(R.id.id_date);
            id_Time=itemView.findViewById(R.id.id_Time);
            amount=itemView.findViewById(R.id.amount);
        }
    }
}
