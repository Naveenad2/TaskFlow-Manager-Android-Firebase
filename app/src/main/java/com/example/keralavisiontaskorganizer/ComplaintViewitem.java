package com.example.keralavisiontaskorganizer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ComplaintViewitem  extends RecyclerView.ViewHolder {


    private Context context;

    TextView nametextview;

    TextView addresstextview;
    TextView tasktextview;

    Button delete,viewComplaint;

    ConstraintLayout priority,mainBox;

    public ComplaintViewitem(Context context , @NonNull View itemView, List<String> name) {
        super(itemView);
        this.context = context;

        nametextview = itemView.findViewById(R.id.complaintnameNewComplaint);

        addresstextview = itemView.findViewById(R.id.complaintaddressTextView);
        tasktextview = itemView.findViewById(R.id.complaintwhichTaskTextView);

        priority = itemView.findViewById(R.id.complaintprioritycolorchecklayout);
        mainBox = itemView.findViewById(R.id.complaintmainlayout);


        viewComplaint = itemView.findViewById(R.id.viewcomplaintOperatorbutton2);

        viewComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ViewFullConnectionDetailsController.class);
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

        delete = (Button)itemView.findViewById(R.id.complaintDeleteButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("newComplaint");

                int position = getBindingAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    String itemName = name.get(position);
                    usersRef.child(itemName).removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();



                                    // Toast.makeText(getApplicationContext(), "Node deleted successfully", Toast.LENGTH_LONG).show();
                                    // Node deleted successfully
                                    // Proceed with any additional operations
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //  Toast.makeText(getApplicationContext(), "Failed to delete node", Toast.LENGTH_LONG).show();
                                    // Failed to delete node
                                    // Handle the error
                                }
                            });

                }
            }
        });

    }

}

