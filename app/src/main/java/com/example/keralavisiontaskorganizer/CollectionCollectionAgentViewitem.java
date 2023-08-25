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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CollectionCollectionAgentViewitem extends RecyclerView.ViewHolder{


    private Context context;

    TextView nametextview;

    TextView phoneNumber;

    Button goButton,viewCollection;

    ConstraintLayout priority ,mainBox;

    FirebaseAuth mAuth;

    public CollectionCollectionAgentViewitem(Context context , @NonNull View itemView, List<String> name) {
        super(itemView);
        this.context = context;

        nametextview = itemView.findViewById(R.id.collectionCunsumerNumber);


        phoneNumber = itemView.findViewById(R.id.collectionPhoneNumber);
        priority = itemView.findViewById(R.id.collectionprioritycolorchecklayout);
        mainBox = itemView.findViewById(R.id.collectiomainlayout);


        viewCollection = itemView.findViewById(R.id.viewcollectionCollectionAgentbutton2);

        viewCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ViewFullCollectionOperatorField.class);
                int position = getBindingAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    String itemName = name.get(position);

                    intent.putExtra("itemName",itemName);
                    intent.putExtra("rootitemName","newCollection");
                }

                //    intent.putExtra("uid",);
                context.startActivity(intent);

            }
        });

        goButton = itemView.findViewById(R.id.dothisTaskcollectionCollectionAgentButton);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth = FirebaseAuth.getInstance();

                FirebaseUser currentUser = mAuth.getCurrentUser();

                Intent intent = new Intent(context,GoSureActivity.class);

                int position = getBindingAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    String itemName = name.get(position);

                    String roleName = "newCollection";


                    intent.putExtra("currentUser", currentUser.getUid());
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("roleName", roleName);

                }

                context.startActivity(intent);


            }


        });









    }

}


