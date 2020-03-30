package com.vaatu.tripmate.ui.home.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaatu.tripmate.R;
import com.vaatu.tripmate.data.remote.network.FirebaseDB;
import com.vaatu.tripmate.utils.CardviewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mTripsRef;
    private List<CardviewModel> tripDetails = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mTripsRef = FirebaseDatabase.getInstance().getReference().child("trip-mate").child(currentUser.getUid()).child("trips");

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rev = root.findViewById(R.id.recycler);
        RecAdaptor adpater = new RecAdaptor(tripDetails, getActivity());
        rev.setLayoutManager(new LinearLayoutManager(getContext()));
        rev.setAdapter(adpater);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.i("DataSnapshot Loop" ,"##" );
                tripDetails.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    tripDetails.add(ds.getValue(CardviewModel.class));
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

//        List<CardviewModel> mylist = new ArrayList<CardviewModel>();
//        CardviewModel c1 = new CardviewModel("smouha", "loran", "1/2/2020", "9:30", "WorkTrip", "");
//
//        CardviewModel c = new CardviewModel("Loran", "loran", "2/1/2020", "5:30", "HomeTrip", "");
//        mylist.add(c);
//        mylist.add(c1);
//        fireb.saveTripToDatabase(mylist.get(0));


        // getSupportActionBar().setTitle("Home");
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.starttrip:
//                Toast.makeText(HomeFragment.this,"hhdh",Toast.LENGTH_LONG).show();
//                // Displays an image of the Swiss Alps
//                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//                return true;
//            case R.id.edittrip:
//
//                return true;
//            case R.id.cancel:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
