package com.vaatu.tripmate.ui.home.history;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vaatu.tripmate.R;
import com.vaatu.tripmate.utils.CardviewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdaptor extends RecyclerView.Adapter<HistoryAdaptor.ViewHolder> {

    List<CardviewModel> list = new ArrayList<>();
   // List<CardviewModel> canceledlist =  new ArrayList<>();
    Context cntxt;


    public HistoryAdaptor(List<CardviewModel> list, Context cntxt) {
        this.list = list;
        this.cntxt = cntxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View v = inflate.inflate(R.layout.history_card_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.start.setText(list.get(position).startloc);
        holder.name.setText(list.get(position).tripname);
        holder.date.setText(list.get(position).date);
        holder.status.setText(list.get(position).status);
        holder.time.setText(list.get(position).time);
        holder.end.setText(list.get(position).endloc);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardview;
        TextView start, end, date, time, name,status;
       // Button popup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            start = itemView.findViewById(R.id.start_loc_id);
            end = itemView.findViewById(R.id.end_loc_id);
            time = itemView.findViewById(R.id.Time_id);
            name = itemView.findViewById(R.id.trip_name_id);
            date = itemView.findViewById(R.id.Date_id);
            status =itemView.findViewById(R.id.status);

        }
    }
}