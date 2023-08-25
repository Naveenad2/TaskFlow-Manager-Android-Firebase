package com.example.keralavisiontaskorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewFullCollectionOperatorField extends AppCompatActivity {

    private List<String> details = new ArrayList<>();
    String itemName,rootitemName;

    TextView consumerNumber,phonenumber,priority,discripton,whoIsDoingThisTask;

    Button goButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_collection_operator_field);

        consumerNumber = findViewById(R.id.CollectionConsumerNumberTextField);
        phonenumber = findViewById(R.id.CollectionPhoneNumberview);
        priority = findViewById(R.id.CollectionPriorityview);
        discripton = findViewById(R.id.CollectionDiscriptionview);
        whoIsDoingThisTask = findViewById(R.id.CollectionwhoIsDoingThisTaskTextView);

        Intent intent = getIntent();

        itemName = intent.getStringExtra("itemName");
        rootitemName = intent.getStringExtra("rootitemName");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference(rootitemName);

       goButton = findViewById(R.id.collectionDoThisTaskButton);

       goButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               mAuth = FirebaseAuth.getInstance();

               FirebaseUser currentUser = mAuth.getCurrentUser();

               Intent intent = new Intent(ViewFullCollectionOperatorField.this,GoSureActivity.class);

               //int position = getBindingAdapterPosition();

                  // String itemNamee = itemName;

                   String roleName = "newCollection";


                   intent.putExtra("currentUser", currentUser.getUid());
                   intent.putExtra("itemName", itemName);
                   intent.putExtra("roleName", roleName);



               startActivity(intent);

           }
       });



        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Check if the "athirapaly" node exists
                if (dataSnapshot.hasChild(itemName)) {
                    DataSnapshot snapshot = dataSnapshot.child(itemName);
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        // Here, each childSnapshot represents a child node under "athirapaly"
                        details.add(childSnapshot.getValue(String.class));

                    }
                    // Now that details list is populated, call setAllDetails to update the UI
                    setAllDetails();
                } else {
                    // The "athirapaly" node does not exist or is empty
                    Toast.makeText(getApplicationContext(), "No data found ", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error if the operation is cancelled
                // For example, show an error message in a Toast
                Toast.makeText(getApplicationContext(), "Failed to get data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setAllDetails() {
        // Now you can access the data in the details list and update the UI
        if (details.size() >= 1) {
            consumerNumber.setText(details.get(3));
            phonenumber.setText("+91 "+details.get(4));
            priority.setText(details.get(5));
            discripton.setText(details.get(2));
            whoIsDoingThisTask.setText(details.get(0));

            if(!Objects.equals(details.get(1), "null")){
                goButton.setVisibility(View.GONE);
            }
        } else {
            for( String data: details){
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
            }

        }
    }
}