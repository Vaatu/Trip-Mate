package com.vaatu.tripmate.ui.home.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vaatu.tripmate.R;
import com.vaatu.tripmate.ui.home.UpcomingTripsActivity;
import com.vaatu.tripmate.utils.CardviewModel;
import com.vaatu.tripmate.utils.RecAdaptor;

import java.util.ArrayList;
import java.util.List;

//import com.vaatu.tripmate.ui.home.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        List<CardviewModel> mylist = new ArrayList<CardviewModel>();
        CardviewModel c1= new CardviewModel("smouha","loran","1/2/2020","9:30","WorkTrip");

        CardviewModel c= new CardviewModel("smouha","loran","1/2/2020","9:30","WorkTrip");
        mylist.add(c);
        mylist.add(c1);

        RecyclerView rev = root.findViewById(R.id.recycler);
        RecAdaptor adpater = new RecAdaptor(mylist, getActivity());
        rev.setLayoutManager(new LinearLayoutManager(getContext()));
        rev.setAdapter(adpater);
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
