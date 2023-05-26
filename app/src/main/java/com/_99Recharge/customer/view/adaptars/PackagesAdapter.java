package com._99Recharge.customer.view.adaptars;

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

import com._99Recharge.R;
import com._99Recharge.customer.service.CommonUrl;
import com._99Recharge.customer.service.model.responseBody.PackDatum;
import com._99Recharge.customer.util.ConstantValues;
import com._99Recharge.customer.view.ui.MobileRecharge.FlexiloadActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.ViewHolder> {

    Context context;
    ArrayList<PackDatum>packagesArrayList;
    private LayoutInflater mInflater;
    @LayoutRes
    int resLayout;

    public PackagesAdapter(Context context, ArrayList<PackDatum> packagesArrayList, @LayoutRes int resLayout) {
        this.context = context;
        this.packagesArrayList = packagesArrayList;
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
        PackDatum packagesModel=packagesArrayList.get(position);
        holder.amount.setText(packagesModel.getAmount());
        holder.value.setText(packagesModel.getDescription());

        Picasso.get()  //Here, this is context.
                .load(CommonUrl.BASE_URL+"/images/"+packagesModel.getImg()+".jpg")  //Url of the image to load.
                .into(holder.opImg);

        holder.packActiveBtn.setOnClickListener(view -> {
           Intent intent=new Intent(view.getContext(), FlexiloadActivity.class);
            intent.putExtra(ConstantValues.Flexiload.AMOUNT,packagesModel.getAmount());
            intent.putExtra(ConstantValues.Flexiload.PACK,packagesModel.getDescription());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return packagesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amount;
        ImageView opImg;
        TextView value;
        AppCompatButton packActiveBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount=itemView.findViewById(R.id.id_packValue);
            opImg=itemView.findViewById(R.id.loImgId);
            value=itemView.findViewById(R.id.id_value);
            packActiveBtn=itemView.findViewById(R.id.id_packActiveBtn);
        }
    }
}
