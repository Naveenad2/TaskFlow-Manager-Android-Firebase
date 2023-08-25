package com.example.keralavisiontaskorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    TabLayout tablayout;
  private   ViewPager2 viewPager2;
  private   PagerAdapter pagerAdapter;
   private Button SwitchUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

//----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
        tablayout =  findViewById(R.id.Tablayout);
        viewPager2 = findViewById(R.id.ViewPager);

        FragmentManager fm = getSupportFragmentManager();
        pagerAdapter = new PagerAdapter(fm,getLifecycle());

        viewPager2.setAdapter(pagerAdapter);

//---------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------

        SwitchUser = (Button)findViewById(R.id.AdminSwitchUserbutton) ;

        SwitchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminActivity.this, ChangeUserActivity.class));
                finish();

            }
        });

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