package com.vaatu.tripmate.ui.home.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vaatu.tripmate.R;
import com.vaatu.tripmate.utils.TripModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

//    private HistoryViewModel mViewModel;
//
//    public static HistoryFragment newInstance() {
//        return new HistoryFragment();
//    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.history_fragment, container, false);
        List<TripModel> mylist = new ArrayList<TripModel>();
        TripModel c1= new TripModel("smouha","loran","1/2/2020","9:30","WorkTrip","Canceled");
        TripModel c2= new TripModel("smouha","loran","1/2/2020","9:30","WorkTrip","Canceled");

        TripModel c3= new TripModel("Loran","loran","2/1/2020","5:30","HomeTrip","Done");
        TripModel c4= new TripModel("Loran","loran","2/1/2020","5:30","HomeTrip","Done");
        mylist.add(c1);
        mylist.add(c2);
        mylist.add(c3);
        mylist.add(c4);
        RecyclerView rev = root.findViewById(R.id.recycler);
       HistoryAdaptor adpater = new HistoryAdaptor(mylist, getActivity());
        rev.setLayoutManager(new LinearLayoutManager(getContext()));
        rev.setAdapter(adpater);
        return root;
    }


}
