package com.example.keralavisiontaskorganizer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class TechnicianMyTaskView extends RecyclerView.ViewHolder  {


    private Context context;

    TextView nametextview;

    TextView addresstextview;
    TextView tasktextview;

    Button viewButton;

    ConstraintLayout priority;
    FirebaseAuth mAuth;

    public TechnicianMyTaskView(Context context , @NonNull View itemView, List<String> name) {
        super(itemView);
        this.context = context;

        nametextview = itemView.findViewById(R.id.tenameNewConnectiontextview);

        addresstextview = itemView.findViewById(R.id.teaddressNewConnectionAllTaskTextView);
        tasktextview = itemView.findViewById(R.id.tewhichTaskAllTaskTextView);

        priority = itemView.findViewById(R.id.teprioritycolorchecklayout);

        viewButton = itemView.findViewById(R.id.TechnicianViewDetailsButton);



        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ViewFullConnectionDetailsTechnician.class);
                int position = getBindingAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    String itemName = name.get(position);

                    intent.putExtra("itemName",itemName);
                    intent.putExtra("rootitemName","newComplaint");
                }

                //    intent.putExtra("uid",);
                context.startActivity(intent);
            }
        });





    }

}
