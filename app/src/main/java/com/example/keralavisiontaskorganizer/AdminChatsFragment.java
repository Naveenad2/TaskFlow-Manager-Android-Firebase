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
 * Use the {@link AdminChatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminChatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

     Button logOut;
    RecyclerView recyclerView;

    RecyclerAdapterAdminChats recyclerAdapterAdminChats;
    public List<String> userdata = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("users");

    public AdminChatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminChatsFragment.
     */
    // TODO: Rename and change types and number of parameters


    public static AdminChatsFragment newInstance(String param1, String param2) {
        AdminChatsFragment fragment = new AdminChatsFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_admin_chats, container, false);

     //=========================================================================================================
        // here is where the recycler view code starts

        recyclerView = rootView.findViewById(R.id.chatsAdminRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        List<String> user =  getUsersData();
        recyclerAdapterAdminChats = new RecyclerAdapterAdminChats(getData(user),getContext());
        recyclerView.setAdapter(recyclerAdapterAdminChats);

        return rootView;

    }

    private List<String> getData(List<String> user) {
        //Toast.makeText(getContext(), user.get(1), Toast.LENGTH_SHORT).show();

        return user;
    }


    private List<String> getUsersData() {

        List<String> data = new ArrayList<>();

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Iterate through all child nodes under "users"
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve user data and do something with it
                    String userId = userSnapshot.getKey();
                    User user = userSnapshot.getValue(User.class);


                    assert user != null;
                    if (user.getWaiting().equals("false")) {
                        data.add((String) user.getUsername());
                       // Toast.makeText(getContext(), user.getUid(), Toast.LENGTH_SHORT).show();
                    }


                }
                recyclerAdapterAdminChats.setData(getData(data));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that may occur during the retrieval process
                Log.e("Firebase", "Error retrieving users: " + databaseError.getMessage());
            }
        });

        return data;

    }
}