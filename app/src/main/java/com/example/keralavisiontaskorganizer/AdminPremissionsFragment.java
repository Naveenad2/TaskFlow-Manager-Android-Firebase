package com.example.keralavisiontaskorganizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminPremissionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */



public class AdminPremissionsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapterAdminPermission recyclerAdapterAdminPermission;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("users");

    List<String> username = new ArrayList<>();
    List<String> phoneNumber = new ArrayList<>();
    List<String> role = new ArrayList<>();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AdminPremissionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminPremissionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminPremissionsFragment newInstance(String param1, String param2) {
        AdminPremissionsFragment fragment = new AdminPremissionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_admin_premissions, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewPermission);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        List<String> user =  getUsersData();
        recyclerAdapterAdminPermission = new RecyclerAdapterAdminPermission(username,phoneNumber,role,getContext());
        recyclerView.setAdapter(recyclerAdapterAdminPermission);
        // Inflate the layout for this fragment
        return rootView;
    }


    private List<String> getData(List<String> user) {
        //Toast.makeText(getContext(), user.get(1), Toast.LENGTH_SHORT).show();

        return user;
    }

    private List<String> getUsersData() {



        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Iterate through all child nodes under "users"
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve user data and do something with it
                    String userId = userSnapshot.getKey();
                    User user = userSnapshot.getValue(User.class);


                    assert user != null;
                    if(user.getWaiting().equals("true")){
                        username.add((String) user.getUsername());
                        phoneNumber.add((String)user.getPhoneNumber());
                        role.add((String)user.getRole());
                        Toast.makeText(getContext(), user.getUsername(), Toast.LENGTH_SHORT).show();
                    }



                }
                recyclerAdapterAdminPermission.setData(getData(username));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that may occur during the retrieval process
                Log.e("Firebase", "Error retrieving users: " + databaseError.getMessage());
            }
        });

        return username;
    }
}