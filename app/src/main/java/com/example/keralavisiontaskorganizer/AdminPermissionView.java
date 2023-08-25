package com.example.keralavisiontaskorganizer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AdminPermissionView extends RecyclerView.ViewHolder {

    public TextView itemTextUsername;
    public TextView itemTextPhoneNumber;
    public TextView itemTextRole;

    public Button itemButton;
    private Context context;

    public AdminPermissionView(@NonNull View itemView, List<String> item, Context context) {
        super(itemView);
        this.context = context;
        itemTextUsername = itemView.findViewById(R.id.adminPermissionitemText7);

        itemTextPhoneNumber = itemView.findViewById(R.id.adminPermissionitemTextPhoneNumber);
        itemTextRole = itemView.findViewById(R.id.adminPermissionRoleText);

        itemButton = (Button) itemView.findViewById(R.id.adminUserAcceptButton);
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getBindingAdapterPosition(); // or getAbsoluteAdapterPosition()
                if (position != RecyclerView.NO_POSITION) {
                    String itemName = item.get(position);
                    Query query = usersRef.orderByChild("username").equalTo(itemName);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Iterate through the filtered data
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                                User user = userSnapshot.getValue(User.class);
                                if (user != null) {

                                    Intent intent = new Intent(context,adminPermissionConfirmPage.class);

                                    intent.putExtra("uid",user.getUid());
                                    context.startActivity(intent);


                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle any errors that may occur during the retrieval process
                            Log.e("Firebase", "Error retrieving users: " + databaseError.getMessage());
                        }
                    });

                }
            }
        });

    }
}
