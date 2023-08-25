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

public class OperatorMainActivity extends AppCompatActivity {

   private   TabLayout tablayout;
   private ViewPager2 viewPager2;
   OperatorPagerAdapter operatorPagerAdapter;
   //Switch user button
   private Button switchUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_main);

        //====================================================================================================
        //===========================================================================================

        tablayout =  findViewById(R.id.OperatorTablayout);
        viewPager2 = findViewById(R.id.OperatorViewPager);

        FragmentManager fm = getSupportFragmentManager();
        operatorPagerAdapter = new OperatorPagerAdapter(fm,getLifecycle());

        viewPager2.setAdapter(operatorPagerAdapter);
//---------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------
       switchUserButton = (Button)findViewById(R.id.SwitchUserbutton);

       switchUserButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(OperatorMainActivity.this,ChangeUserActivity.class);
               startActivity(intent);

           }
       });

        //logOut = (Button)findViewById(R.id.SwitchUserbutton) ;

      /*  logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(OperatorMainActivity.this, MainActivity.class));
                finish();

            }
        });                      */

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