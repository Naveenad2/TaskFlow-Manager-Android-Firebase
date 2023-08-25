package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class GetOpt extends AppCompatActivity {

    TextView verificationTextView ;
    EditText otp;
    Button otpVerifyButton;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth;

    String phonenumber,username,userRole,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_opt);


        mAuth = FirebaseAuth.getInstance();



        otp = (EditText)findViewById(R.id.otpfield);

        //set the phone to view
       verificationTextView = (TextView) findViewById(R.id.verificationTextview);
       phonenumber = getIntent().getStringExtra("phonenumber");
       username = getIntent().getStringExtra("username");

       userRole = getIntent().getStringExtra("role");

      //add the phonenumber to the text
      String modifiedVerificationContent = "Enter The OTP Code Received in +91 "+phonenumber;
      String countryCodeWithPhoneNmuber = "+91"+phonenumber.replace(" ","");
      //change the text
      verificationTextView.setText(modifiedVerificationContent);

      OtpVerification(countryCodeWithPhoneNmuber);

     //get the button to verify
        otpVerifyButton = (Button)findViewById(R.id.otpVerifyButton);

        otpVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,otp.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void OtpVerification(String countryCodeWithPhoneNmuber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(countryCodeWithPhoneNmuber)  // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS)  // Timeout and unit
                        .setActivity(this)  // (Optional) Activity for callback binding
                        .setCallbacks(mCallbacks)  // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {
                    // This callback will be invoked in two situations:
                    // 1 - Instant verification.
                    // 2 - Auto-retrieval.
                    signInWithPhoneAuthCredential(credential);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    // Handle verification failure
                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        // Invalid request
                    } else if (e instanceof FirebaseTooManyRequestsException) {
                        // The SMS quota for the project has been exceeded
                    }

                    // Show a message and update the UI
                }

                @Override
                public void onCodeSent(String verificationId,
                                       PhoneAuthProvider.ForceResendingToken token) {
                    // The SMS verification code has been sent to the provided phone number
                    // Save verification ID and resending token for later use
                    mVerificationId = verificationId;
                    mResendToken = token;
                }
            };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = task.getResult().getUser();
                     //   FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                        assert user != null;
                        String uid = user.getUid();
                        Intent intent = new Intent(GetOpt.this,waitingList.class);
                        // add essential details to pass
                        intent.putExtra("uid",uid);
                        intent.putExtra("username",username);

                        intent.putExtra("phonenumber",phonenumber);
                        intent.putExtra("userrole",userRole);

                        String waiting = "true";
                        // create realtime database
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference usersRef = database.getReference("users");

                        // save the user in the phone itself

                        //create the realtime database and child nodes

                        User newUser = new User(uid, phonenumber, username, userRole, waiting);
                        usersRef.child(uid).setValue(newUser)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {



                                        // User information stored successfully
                                        // Proceed with the next steps or operations
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Failed to store user information
                                        // Handle the error
                                    }
                                });

                        startActivity(intent);
                        // Update UI
                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                });
    }
}


























