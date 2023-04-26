package com.example.project1.view.adaptars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.service.ModelClasses.PackagesModel;
import com.example.project1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.ViewHolder> {

    Context context;
    ArrayList<PackagesModel>packagesArrayList;
    private LayoutInflater mInflater;
    @LayoutRes
    int resLayout;

    public PackagesAdapter(Context context, ArrayList<PackagesModel> packagesArrayList, @LayoutRes int resLayout) {
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
        PackagesModel packagesModel=packagesArrayList.get(position);
        Picasso.get().load("").into(holder.opImg);
        holder.packActiveBtn.setOnClickListener(view -> {

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
