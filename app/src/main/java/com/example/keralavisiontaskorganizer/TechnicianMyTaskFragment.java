package com.example.keralavisiontaskorganizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TechnicianMyTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TechnicianMyTaskFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerTechmicianMyTaskAdapter recyclerTechmicianMyTaskAdapter;


    //  Button newConnectionButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("newConnection");


    List<String> name = new ArrayList<>();

    List<String> address = new ArrayList<>();
    List<String> priority = new ArrayList<>();
    List<String> task = new ArrayList<>();

    FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TechnicianMyTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TechnicianMyTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TechnicianMyTaskFragment newInstance(String param1, String param2) {
        TechnicianMyTaskFragment fragment = new TechnicianMyTaskFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_technician_my_task, container, false);

        recyclerView = rootView.findViewById(R.id.TechnicianMyTaskRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        List<String> user =  getNewConnectionData();

        recyclerTechmicianMyTaskAdapter = new RecyclerTechmicianMyTaskAdapter(getContext(),name,address,priority,task,"new Connection");
        recyclerView.setAdapter(recyclerTechmicianMyTaskAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }
    private List<String> getData(List<String> data) {
        //Toast.makeText(getContext(), user.get(1), Toast.LENGTH_SHORT).show();

        return data;
    }
    private List<String> getNewConnectionData() {

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                name.clear();
                address.clear();
                priority.clear();
                task.clear();

                // Iterate through all child nodes under "users"
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve user data and do something with it
                    String userId = userSnapshot.getKey();
                    NewConnectionModel model = userSnapshot.getValue(NewConnectionModel.class);

                    assert model != null;

                    if(model.getAllreadyDoneUid().equals(currentUser.getUid())){

                        name.add((String) model.getName());

                        address.add((String)model.getAddress());
                        priority.add((String)model.getPriority());
                        task.add((String)model.getTask());

                    }


                    // Toast.makeText(getContext(), model.getName(), Toast.LENGTH_SHORT).show();


                }

                recyclerTechmicianMyTaskAdapter.setData(getData(name));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that may occur during the retrieval process
                Log.e("Firebase", "Error retrieving data: " + databaseError.getMessage());
            }
        });

        return name;
    }



}