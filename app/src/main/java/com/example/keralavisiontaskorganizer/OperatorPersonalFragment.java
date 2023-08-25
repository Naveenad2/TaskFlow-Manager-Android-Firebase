package com.example.keralavisiontaskorganizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OperatorPersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OperatorPersonalFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerComplaintAdapter recyclerComplaintAdapter;


    Button newConnectionButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("newComplaint");

    List<String> name = new ArrayList<>();

    List<String> address = new ArrayList<>();
    List<String> priority = new ArrayList<>();
    List<String> task = new ArrayList<>();
    List<String> allreadyDone = new ArrayList<>();




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OperatorPersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OperatorPersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OperatorPersonalFragment newInstance(String param1, String param2) {
        OperatorPersonalFragment fragment = new OperatorPersonalFragment();
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




        View rootView = inflater.inflate(R.layout.fragment_operator_personal, container, false);


        newConnectionButton = rootView.findViewById(R.id.newComplaintButton);

        newConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),NewComplaintField.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        recyclerView = rootView.findViewById(R.id.complaintRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        List<String> user =  getNewConnectionData();
        recyclerComplaintAdapter = new RecyclerComplaintAdapter(getContext(),name,address,priority,task,allreadyDone);
        recyclerView.setAdapter(recyclerComplaintAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }


    private List<String> getData(List<String> data) {
        //Toast.makeText(getContext(), user.get(1), Toast.LENGTH_SHORT).show();

        return data;
    }

    private List<String> getNewConnectionData() {



        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                name.clear();
                address.clear();
                priority.clear();
                task.clear();
                allreadyDone.clear();


                // Iterate through all child nodes under "users"
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve user data and do something with it
                    String userId = userSnapshot.getKey();
                    NewComplaintModel model = userSnapshot.getValue(NewComplaintModel.class);


                    assert model != null;

                    name.add((String) model.getName());

                    address.add((String)model.getAddress());
                    priority.add((String)model.getPriority());
                    task.add((String)model.getTask());
                    allreadyDone.add((String)model.getAllreadyDone());
                    // Toast.makeText(getContext(), model.getName(), Toast.LENGTH_SHORT).show();




                }
                recyclerComplaintAdapter.setData(getData(name));


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