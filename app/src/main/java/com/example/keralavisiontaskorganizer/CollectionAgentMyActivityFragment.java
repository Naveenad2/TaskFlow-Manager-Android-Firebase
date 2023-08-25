package com.example.keralavisiontaskorganizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
 * Use the {@link CollectionAgentMyActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionAgentMyActivityFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerCollectionCollectionAgentAdapter recyclerCollectionCollectionAgentAdapter;


    Button newCollectionButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("newCollection");

    List<String> ConsumerNumber = new ArrayList<>();

    List<String> priority = new ArrayList<>();

    List<String> phoneNumber = new ArrayList<>();

    List<String> allreadyDone = new ArrayList<>();

    private FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollectionAgentMyActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectionAgentMyActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionAgentMyActivityFragment newInstance(String param1, String param2) {
        CollectionAgentMyActivityFragment fragment = new CollectionAgentMyActivityFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_collection_agent_my_activity, container, false);
        recyclerView = rootView.findViewById(R.id.CollectionAgentRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        List<String> user =  getNewConnectionData();
        recyclerCollectionCollectionAgentAdapter = new RecyclerCollectionCollectionAgentAdapter(getContext(),ConsumerNumber,phoneNumber,priority,allreadyDone);
        recyclerView.setAdapter(recyclerCollectionCollectionAgentAdapter);
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

                ConsumerNumber.clear();
                phoneNumber.clear();
                priority.clear();
                allreadyDone.clear();


                // Iterate through all child nodes under "users"
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve user data and do something with it
                    String userId = userSnapshot.getKey();
                    NewCollectionModel model = userSnapshot.getValue(NewCollectionModel.class);


                    assert model != null;

                    if(model.getAllreadyDoneUid().equals(currentUser.getUid())) {

                        ConsumerNumber.add((String) model.getName());

                        phoneNumber.add((String) model.getPhoneNumber());
                        priority.add((String) model.getPriority());
                        allreadyDone.add((String) model.getAllreadyDone());
                    }


                    // Toast.makeText(getContext(), model.getName(), Toast.LENGTH_SHORT).show();




                }
                recyclerCollectionCollectionAgentAdapter.setData(getData(ConsumerNumber));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that may occur during the retrieval process
                Log.e("Firebase", "Error retrieving data: " + databaseError.getMessage());
            }
        });

        return ConsumerNumber;
    }


}