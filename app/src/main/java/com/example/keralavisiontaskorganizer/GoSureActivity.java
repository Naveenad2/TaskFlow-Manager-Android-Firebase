package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class GoSureActivity extends AppCompatActivity {

    Button yesButton,backButton;

    String currentUserUid,itemName;

    FirebaseAuth mAuth;

    String UserName,roleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_sure);

        yesButton = (Button) findViewById(R.id.yesButton);

        Intent intent = getIntent();

        currentUserUid = intent.getStringExtra("currentUser");
        itemName = intent.getStringExtra("itemName");
        roleName = intent.getStringExtra("roleName");



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference rootReference = database.getReference();

        //================================================================================================================================



   //     FirebaseUser currentUser = mAuth.getCurrentUser();

        assert currentUserUid != null;
        //  Toast.makeText(getApplicationContext(),(String) currentUser.getUid(), Toast.LENGTH_SHORT).show();


        DatabaseReference usersRef = database.getReference("users");
        Query query = usersRef.orderByChild("uid").equalTo((String) currentUserUid);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve the user data
                    User user = userSnapshot.getValue(User.class);

                    UserName = user.getUsername();




                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }


        });






        //============================================================================///===================================================

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference allreadyDoneReference = rootReference.child(roleName).child(itemName).child("allreadyDone");

                DatabaseReference allreadyDoneUidReference = rootReference.child(roleName).child(itemName).child("allreadyDoneUid");

                allreadyDoneUidReference.setValue(currentUserUid);

                allreadyDoneReference.setValue(UserName).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(getApplicationContext(), "You Assigned it Successfully", Toast.LENGTH_LONG).show();

                        finish();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



            }
        });
    }
}