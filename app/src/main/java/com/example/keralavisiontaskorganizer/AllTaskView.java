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

public class AllTaskView  extends RecyclerView.ViewHolder {
    private Context context;

    TextView nametextview;

    TextView addresstextview;
    TextView tasktextview;

    Button delete ,viewDetail;

    ConstraintLayout priority,mainBox;

    public AllTaskView(Context context ,@NonNull View itemView,List<String> name) {
        super(itemView);
        this.context = context;

        nametextview = itemView.findViewById(R.id.nameNewConnectionAllTask);

        addresstextview = itemView.findViewById(R.id.addressNewConnectionAllTaskTextView);
        tasktextview = itemView.findViewById(R.id.whichTaskAllTaskTextView);

        priority = itemView.findViewById(R.id.prioritycolorchecklayout);

        delete = (Button)itemView.findViewById(R.id.newconnectionDeleteButton);

        viewDetail = (Button)itemView.findViewById(R.id.Operatorviewdetailsbutton2);

        mainBox = itemView.findViewById(R.id.ConnectionMainLayout);

        viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ViewFullConnectionDetailsController.class);
                int position = getBindingAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    String itemName = name.get(position);

                    intent.putExtra("itemName",itemName);
                    intent.putExtra("rootitemName","newConnection");
                }

                //    intent.putExtra("uid",);
                context.startActivity(intent);


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("newConnection");

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
