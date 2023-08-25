package com.example.keralavisiontaskorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFullConnectionDetailsController extends AppCompatActivity {
    private List<String> details = new ArrayList<>();
    String itemName,rootitemName;

    Button backButton;

    TextView name,phonenumber,address,task,priority,discripton,whoIsDoingThisTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_connection_details_controller);

        name = findViewById(R.id.Tecniciannameview);
        phonenumber = findViewById(R.id.TecnicianPhoneNumberview);
        address = findViewById(R.id.TecnicianAddressview);
        task = findViewById(R.id.TecnicianTaskview);
        priority = findViewById(R.id.TecnicianPriorityview);
        discripton = findViewById(R.id.TecnicianDiscriptionview);
        whoIsDoingThisTask = findViewById(R.id.whoIsDoingThisTaskTextView);

        Intent intent = getIntent();

        itemName = intent.getStringExtra("itemName");
        rootitemName = intent.getStringExtra("rootitemName");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference(rootitemName);

        backButton = findViewById(R.id.publicbackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

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
                    Toast.makeText(getApplicationContext(), "No data found under 'athirapaly'", Toast.LENGTH_SHORT).show();
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
            name.setText(details.get(4));
            phonenumber.setText("+91 "+details.get(5));
            address.setText(details.get(0));
            priority.setText(details.get(6));
            task.setText(details.get(7));
            discripton.setText(details.get(3));
            whoIsDoingThisTask.setText(details.get(1));
        } else {
            for( String data: details){
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
            }

        }
    }
}