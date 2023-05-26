package com._99Recharge.customer.view.ui.MobileRecharge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com._99Recharge.R;
import com._99Recharge.customer.service.ModelClasses.ContactModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHoler> implements Filterable {
    ArrayList<ContactModel>allContacts;
    public List<ContactModel> allItems;
    Context context;

    public ContactListAdapter(ArrayList<ContactModel> allContacts, Context context) {
        this.allContacts = allContacts;
        this.allItems = new ArrayList<>(allContacts);
        this.context = context;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_single_row,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        ContactModel contactModel=allContacts.get(position);
        holder.name.setText(contactModel.getName());
        holder.number.setText(contactModel.getNumber());
        holder.conBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,FlexiloadActivity.class);
                intent.putExtra("conVal",contactModel.getNumber());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allContacts.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {

        //RUn on a background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<ContactModel> filteredList = new ArrayList<>();
            if (constraint.toString().isEmpty()) {
                filteredList.addAll(allItems);

            } else {
                for (ContactModel item : allItems) {
                    String searchableText=item.getName()+item.getNumber();
                    if (searchableText.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //on a UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            allContacts.clear();
            allContacts.addAll((Collection<? extends ContactModel>) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView name,number;
        LinearLayout conBtnLayout;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.contactNameId);
            conBtnLayout=itemView.findViewById(R.id.conBtnLayoutId);
            number=itemView.findViewById(R.id.contactNumberId);


        }
    }
}
