package com.example.keralavisiontaskorganizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterAdminChats extends RecyclerView.Adapter<AdminChatView>{

    public List<String> items;
    private Context context;



    public RecyclerAdapterAdminChats(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public void setData(List<String> newData) {
        items = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminChatView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_user_item, parent, false);
        return new AdminChatView(itemView,items,context);
    }


    @Override
    public void onBindViewHolder(@NonNull AdminChatView holder, int position) {
        String item = items.get(position);
        holder.itemText.setText(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


