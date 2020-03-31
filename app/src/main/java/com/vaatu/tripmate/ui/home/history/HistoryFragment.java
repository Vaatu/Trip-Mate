package com.vaatu.tripmate.ui.home.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.vaatu.tripmate.R;
import com.vaatu.tripmate.utils.TripModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mTripsRef;
    private List<TripModel> tripDetails = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mTripsRef = FirebaseDatabase.getInstance().getReference().child("trip-mate").child(currentUser.getUid()).child("historytrips");


        View root = inflater.inflate(R.layout.history_fragment, container, false);
//        List<TripModel> mylist = new ArrayList<TripModel>();
//        TripModel c1= new TripModel("smouha","loran","1/2/2020","9:30","WorkTrip","Canceled");
//        TripModel c2= new TripModel("smouha","loran","1/2/2020","9:30","WorkTrip","Canceled");
//
//        TripModel c3= new TripModel("Loran","loran","2/1/2020","5:30","HomeTrip","Done");
//        TripModel c4= new TripModel("Loran","loran","2/1/2020","5:30","HomeTrip","Done");
//        mylist.add(c1);
//        mylist.add(c2);
//        mylist.add(c3);
//        mylist.add(c4);
        RecyclerView rev = root.findViewById(R.id.recycler);
       HistoryAdaptor adpater = new HistoryAdaptor(tripDetails, getActivity());
        rev.setLayoutManager(new LinearLayoutManager(getContext()));
        rev.setAdapter(adpater);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.i("DataSnapshot Loop" ,"##" );
                tripDetails.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    tripDetails.add(ds.getValue(TripModel.class));
                }
                adpater.notifyDataSetChanged();
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Database", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mTripsRef.addValueEventListener(postListener);

        return root;
    }


}
