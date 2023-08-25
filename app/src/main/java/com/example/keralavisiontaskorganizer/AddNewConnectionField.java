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

public class AddNewConnectionField extends AppCompatActivity {

    EditText name,phoneNumber,location,discription;
    RadioGroup priorityGroup,taskGroup;

    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_connection_field);

        name = (EditText) findViewById(R.id.newConnectionName);
        phoneNumber = (EditText) findViewById(R.id.newConnectionPhoneNumber);
        location = (EditText) findViewById(R.id.newConnectionLocation);
        discription = (EditText) findViewById(R.id.newConnectionDiscription);

        priorityGroup = (RadioGroup) findViewById(R.id.newConnectionPriorityGroup);
        taskGroup = (RadioGroup) findViewById(R.id.newConnectionTask);

        //get the data from the form


        submit = (Button) findViewById(R.id.newConnectionConfirmButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getName = name.getText().toString();
                String getphoneNumber = phoneNumber.getText().toString();
                String getlocation = location.getText().toString();
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
                    getTask = "CableNet";

                } else if (gettaskGroup == R.id.Wire) {
                    getTask = "Wire";
                }else{
                    getTask = "Other";

                }
                String allreadyDone = "null";
                String allreadyDoneUid = "null";

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("newConnection");

                NewConnectionModel model = new NewConnectionModel( getName,getphoneNumber, getlocation, getdiscription, getpriority, getTask,allreadyDone,allreadyDoneUid );
                 usersRef.child(getName).setValue(model)
                         .addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {
                                 Toast.makeText(getApplicationContext(), "New connection Successfully Added", Toast.LENGTH_LONG).show();



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

                 startActivity(new Intent(AddNewConnectionField.this,OperatorMainActivity.class));
                 finish();

            }
        });


    }
}