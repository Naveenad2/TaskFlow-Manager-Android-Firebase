package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class adminPermissionConfirmPage extends AppCompatActivity {

    Button confirmButton;
    Button BackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_permission_confirm_page);

        confirmButton = (Button) findViewById(R.id.confirmPermissionbutton);
        BackButton = (Button) findViewById(R.id.backPermissionbutton);

        String uid = getIntent().getStringExtra("uid");

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Update the "waiting" field using setValue() or updateChildren() method
                usersRef.child("waiting").setValue("false")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(adminPermissionConfirmPage.this,AdminActivity.class));
                                // Field update succeeded
                                // Handle any further actions or notifications
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Field update failed
                                // Handle any error or retry logic
                            }
                        });

            }
        });

    }
}