package com.example.keralavisiontaskorganizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterAdminPermission extends RecyclerView.Adapter<AdminPermissionView> {

    public List<String> items;
    public List<String> phoneNumber;
    public List<String> role;


    private Context context;



    public RecyclerAdapterAdminPermission(List<String> items,List<String> phoneNumber,List<String> role, Context context) {
        this.items = items;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.context = context;
    }

    public void setData(List<String> newData) {
        items = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminPermissionView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_permission, parent, false);
        return new AdminPermissionView(itemView,items,context);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminPermissionView holder, int position) {
        String item = items.get(position);
        String phonenumber = phoneNumber.get(position);
        String roleI = role.get(position);
        holder.itemTextUsername.setText(item);
        holder.itemTextPhoneNumber.setText(phonenumber);
        holder.itemTextRole.setText(roleI);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
