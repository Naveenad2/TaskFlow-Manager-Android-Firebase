package com.example.keralavisiontaskorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    EditText getpassword;
    EditText getrepeatPassword;

    EditText UsernameEditText;
    EditText PhonenumberEditText;

    TextView passwordMatchError;
    Button registerButton, gotologinbutton;

    RadioGroup UserRoleRadio;

    String StringUserRole;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


            registerButton = (Button) findViewById(R.id.registerbutton);
            gotologinbutton = (Button) findViewById(R.id.gotologinbutton);

            //load the login after registration page when goto login button is clicked
        gotologinbutton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(login.this,LoginAfterRegisteration.class);
                startActivity(intent1);
            }
        }));


            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //get the password


                    //Stringify the password


                    //get the username
                    UsernameEditText = (EditText) findViewById(R.id.usernameLogin);
                    String StringUserName = UsernameEditText.getText().toString();
                    System.out.println(StringUserName);

                    //get the phone number
                    PhonenumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
                    String IntphoneNumber = PhonenumberEditText.getText().toString();
                    System.out.println(IntphoneNumber);

                    //get the user role
                    UserRoleRadio = (RadioGroup) findViewById(R.id.userRole);
                    int UserRole = (int) UserRoleRadio.getCheckedRadioButtonId();

                    //find the user role and assign it
                    if (UserRole == R.id.Operator) {
                        StringUserRole = "Operator";
                    } else if (UserRole == R.id.CollectionAgent) {
                        StringUserRole = "CollectionAgent";
                    } else if (UserRole == R.id.Technician) {
                        StringUserRole = "CollectionAgent";
                    }

                    //error message textview
                    passwordMatchError = (TextView) findViewById(R.id.passwordMatchError);

                    //create an intend to switch between screen
                    Intent otppage = new Intent(login.this, GetOpt.class);
                    otppage.putExtra("phonenumber", IntphoneNumber);
                    otppage.putExtra("username", StringUserName );

                    otppage.putExtra("role", StringUserRole);

                    startActivity(otppage);
                }
            });


        }
   // }
}