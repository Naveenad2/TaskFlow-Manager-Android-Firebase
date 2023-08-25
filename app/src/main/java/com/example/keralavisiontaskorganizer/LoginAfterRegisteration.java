package com.example.keralavisiontaskorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginAfterRegisteration extends AppCompatActivity {

    EditText EdittextUsername , EdittextPhoneNumber;
    Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_after_registeration);

        mAuth = FirebaseAuth.getInstance();


         //initiate login button
        loginButton = (Button) findViewById(R.id.loginbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EdittextUsername = (EditText) findViewById(R.id.editTextTextUsername);
                EdittextPhoneNumber = (EditText) findViewById(R.id.editTextPhonenumber);
                //convert the username and the phonenumber to string
                String UserName = (String) EdittextUsername.getText().toString();
                String PhoneNumber = (String) EdittextPhoneNumber.getText().toString();

            }
        });




    }
}