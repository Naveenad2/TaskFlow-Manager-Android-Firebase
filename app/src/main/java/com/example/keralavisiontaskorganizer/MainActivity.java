package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();


        //--------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");
      //  usersRef.keepSynced(false);


        String uid = "yOkvR36EZ9eqvxIrNFua8XelRsB2"; // Replace with the actual user UID

        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser != null) {


            if (currentUser.getUid().equals("5BbH6nX3fac4NG6kKFVQCzfeVDE3")) {
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
                finish();

            }else {
                startActivity(new Intent(MainActivity.this,waitingList.class));
                finish();
            }

//-------------------------------------------------------------------------------------
//----------------------------------------------------------------------------

        } else {
            // User is not logged in, show the login screen
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
            // Initialize your login form and handle the login process
            // ...
        }
    }
}
