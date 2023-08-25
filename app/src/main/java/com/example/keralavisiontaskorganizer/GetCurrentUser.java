package com.example.keralavisiontaskorganizer;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class GetCurrentUser {

    private FirebaseAuth mAuth;
    private String userRole;

    public String getUser() {

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        assert currentUser != null;
        //  Toast.makeText(getApplicationContext(),(String) currentUser.getUid(), Toast.LENGTH_SHORT).show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");
        Query query = usersRef.orderByChild("uid").equalTo((String) currentUser.getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve the user data
                    User user = userSnapshot.getValue(User.class);

                    assert user != null;
                    if (user.getRole().equals("Operator")) {

                        userRole = "Operator";



                     //   Intent intent = new Intent(TechnicianMainActivity.this, ChangeUserActivity.class);
                        // Toast.makeText(getApplicationContext(), user.getWaiting(), Toast.LENGTH_LONG).show();

                      //  startActivity(intent);
                    } else if (user.getRole().equals("Technician")) {

                        userRole = "Technician";

                    }
                    else if (user.getRole().equals("Collection Agent")) {

                        userRole = "Technician";

                    }
                    {
                       // Intent intentt = new Intent(TechnicianMainActivity.this, ChangeUserActivityTechnician.class);
                        // Toast.makeText(getApplicationContext(), user.getWaiting(), Toast.LENGTH_LONG).show();

                      //  startActivity(intentt);
                    }

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }


        });
        return userRole;
    }

}



