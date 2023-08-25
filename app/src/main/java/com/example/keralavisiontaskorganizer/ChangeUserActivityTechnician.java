package com.example.keralavisiontaskorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ChangeUserActivityTechnician extends AppCompatActivity {

    private Button TechnicianButton;
    private Button CollectionAgentButton;
    private  Button LogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_technician);
        TechnicianButton = (Button)findViewById(R.id.TECHNICIAN);
        CollectionAgentButton = (Button)findViewById(R.id.COLLECTION) ;
        LogoutButton = (Button) findViewById(R.id.LOGOUTBUTTON);


        TechnicianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeUserActivityTechnician.this,TechnicianMainActivity.class);
                startActivity(intent);
            }
        });

        CollectionAgentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeUserActivityTechnician.this,CollectionAgentMainActivity.class);
                startActivity(intent);
            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ChangeUserActivityTechnician.this, MainActivity.class));
                finish();

            }
        });







    }
}