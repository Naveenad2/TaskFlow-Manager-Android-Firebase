package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewComplaintField extends AppCompatActivity {

    EditText consumerNumber,phoneNumber,address,discription;
    RadioGroup priorityGroup,taskGroup;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint_field);

        consumerNumber = (EditText) findViewById(R.id.newComplaintName);
        phoneNumber = (EditText) findViewById(R.id.newComplaintPhoneNumber);
        address = (EditText) findViewById(R.id.newComplaintLocation);
        discription = (EditText) findViewById(R.id.newComplaintDiscription);

        priorityGroup = (RadioGroup) findViewById(R.id.newComplaintPriorityGroup);
        taskGroup = (RadioGroup) findViewById(R.id.newComplaintTask);

        confirm = (Button) findViewById(R.id.newComplaintConfirmButton);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getName = consumerNumber.getText().toString();
                String getphoneNumber = phoneNumber.getText().toString();
                String getlocation = address.getText().toString();
                String getdiscription = discription.getText().toString();

                String getpriority ;

                // get the data from the radio group
                int getpriorityGroup = (int)priorityGroup.getCheckedRadioButtonId();
                int gettaskGroup = (int)taskGroup.getCheckedRadioButtonId();

                // check which data is in the priority
                if(getpriorityGroup==R.id.High){
                    getpriority = "High";

                } else if (getpriorityGroup == R.id.Medium) {
                    getpriority = "Medium";
                }else{
                    getpriority = "Low";

                }
                //this is the data
                String getTask;

                if(gettaskGroup==R.id.CableNet){
                    getTask = "Net";

                } else if (gettaskGroup == R.id.Wire) {
                    getTask = "Cable";
                }else{
                    getTask = "Other";

                }

                String allreadyDone = "null";
                String allreadyDoneUid = "null";

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("newComplaint");

                NewComplaintModel model = new NewComplaintModel( getName,getphoneNumber, getlocation, getdiscription, getpriority, getTask,allreadyDone,allreadyDoneUid);
                usersRef.child(getName).setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "New Complaint Successfully Added", Toast.LENGTH_LONG).show();



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

                startActivity(new Intent(NewComplaintField.this,OperatorMainActivity.class));
                finish();

            }
        });


    }
}