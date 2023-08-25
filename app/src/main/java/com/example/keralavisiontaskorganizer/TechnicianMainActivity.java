package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TechnicianMainActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager2 viewPager2;
    private TechnicianPagerAdapter technicianPagerAdapter;

    private Button SwitchUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_main);

        mAuth = FirebaseAuth.getInstance();


        //====================================================================================================
        //===========================================================================================

        tablayout =  findViewById(R.id.TechnicianTablayout);
        viewPager2 = findViewById(R.id.TechnicianViewPager);

        FragmentManager fm = getSupportFragmentManager();
        technicianPagerAdapter = new TechnicianPagerAdapter(fm,getLifecycle());

        viewPager2.setAdapter(technicianPagerAdapter);
//---------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------

        SwitchUser = (Button) findViewById(R.id.TechnicianSwitchUserbutton);

        SwitchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                                    Intent intent = new Intent(TechnicianMainActivity.this, ChangeUserActivity.class);
                                    // Toast.makeText(getApplicationContext(), user.getWaiting(), Toast.LENGTH_LONG).show();

                                    startActivity(intent);
                                } else if (user.getRole().equals("Admin")) {
                                    Intent intent = new Intent(TechnicianMainActivity.this, ChangeUserActivityAdmin.class);

                                    startActivity(intent);
                                } else{
                                        Intent intentt = new Intent(TechnicianMainActivity.this, ChangeUserActivityTechnician.class);
                                        // Toast.makeText(getApplicationContext(), user.getWaiting(), Toast.LENGTH_LONG).show();

                                        startActivity(intentt);
                                    }

                                    }

                            }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    });
                }

        });

      /*  logOut = (Button)findViewById(R.id.SwitchUserbutton) ;

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(TechnicianMainActivity.this, MainActivity.class));
                finish();

            }
        });       */
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}