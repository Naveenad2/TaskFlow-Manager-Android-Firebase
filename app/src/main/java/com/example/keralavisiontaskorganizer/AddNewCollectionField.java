package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AddNewCollectionField extends AppCompatActivity {

    private TextView consumerNumber,PhoneNumber,discription;
   private RadioGroup priority;

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_collection_field);

        consumerNumber = findViewById(R.id.newCollectionConsumerNumber);
        PhoneNumber = findViewById(R.id.newCollectionPhoneNumberNumber);
        priority = findViewById(R.id.newCollectionPriorityGroup);
        discription = findViewById(R.id.newCollectionDiscription);



        submit = (Button) findViewById(R.id.newCollectionConfirmButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getName = consumerNumber.getText().toString();
                String getphoneNumber = PhoneNumber.getText().toString();
                String getdiscription = discription.getText().toString();

                String getpriority ;

                // get the data from the radio group
                int getpriorityGroup = (int)priority.getCheckedRadioButtonId();

                // check which data is in the priority
                if(getpriorityGroup==R.id.High){
                    getpriority = "High";

                } else if (getpriorityGroup == R.id.Medium) {
                    getpriority = "Medium";
                }else{
                    getpriority = "Low";

                }
                //this is the data

                String allreadyDone = "null";
                String allreadyDoneUid = "null";

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("newCollection");

                NewCollectionModel model = new NewCollectionModel(getName,getphoneNumber, getdiscription, getpriority,allreadyDone ,allreadyDoneUid);
                usersRef.child(getName).setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "New collection Successfully Added", Toast.LENGTH_LONG).show();



                                // User information stored successfully
                                // Proceed with the next steps or operations
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Failed to store user information
                                // Handle the error

                                Toast.makeText(getApplicationContext(), "Failed to add new connection ", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),  e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                startActivity(new Intent(AddNewCollectionField.this,OperatorMainActivity.class));
                finish();

            }
        });


    }
}