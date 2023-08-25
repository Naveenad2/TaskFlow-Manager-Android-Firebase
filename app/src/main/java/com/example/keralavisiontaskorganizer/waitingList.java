package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class waitingList extends AppCompatActivity {

    String phonenumber,username,userRole,password,userUid;
    TextView succsessmessage;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_list);

       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        succsessmessage = (TextView)findViewById(R.id.successmessage);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        assert currentUser != null;
        Toast.makeText(getApplicationContext(),(String) currentUser.getUid(), Toast.LENGTH_SHORT).show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");


        if(currentUser != null){
            Query query = usersRef.orderByChild("uid").equalTo((String) currentUser.getUid());

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Retrieve the user data
                        User user = userSnapshot.getValue(User.class);


                        assert user != null;
                        if (user.getWaiting().equals("true")) {

                            Intent intent = new Intent(waitingList.this, MainActivity2.class);
                            Toast.makeText(getApplicationContext(), user.getWaiting(), Toast.LENGTH_LONG).show();

                            startActivity(intent);


                        } else if (user.getRole().equals("Operator")) {
                            Toast.makeText(getApplicationContext(), "operator", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), user.getWaiting(), Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(waitingList.this, OperatorMainActivity.class);
                            startActivity(intent);

                        } else if (user.getRole().equals("Technician")) {
                            Toast.makeText(getApplicationContext(), "Technician ", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(waitingList.this, TechnicianMainActivity.class);
                            startActivity(intent);

                        } else if (user.getRole().equals("CollectionAgent")) {
                            Toast.makeText(getApplicationContext(), "Collection agent ", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(waitingList.this, CollectionAgentMainActivity.class);
                            startActivity(intent);

                        }


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }


            });
        }




    }

    }
