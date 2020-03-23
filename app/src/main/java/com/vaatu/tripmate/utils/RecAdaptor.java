package com.vaatu.tripmate.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vaatu.tripmate.R;

import java.util.ArrayList;
import java.util.List;

public class RecAdaptor extends RecyclerView.Adapter<RecAdaptor.ViewHolder> {

    List<CardviewModel> list = new ArrayList<>();
    Context cntxt;


    public RecAdaptor(List<CardviewModel> list, Context cntxt) {
        this.list = list;
        this.cntxt = cntxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View v = inflate.inflate(R.layout.custom_card_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.start.setText(list.get(position).startloc);
        holder.name.setText(list.get(position).tripname);
        holder.date.setText(list.get(position).date);
       // holder.status.setText(list.get(position).status);
        holder.time.setText(list.get(position).time);
        holder.end.setText(list.get(position).endloc);
        holder.popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(cntxt,"gg",Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu= new PopupMenu(cntxt,holder.popup);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });

               popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardview;
        TextView start, end, date, time,name;
        Button popup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           // status = itemView.findViewById(R.id.status_id);
            start = itemView.findViewById(R.id.start_loc_id);
            end = itemView.findViewById(R.id.end_loc_id);
            time = itemView.findViewById(R.id.Time_id);
            name = itemView.findViewById(R.id.trip_name_id);
            date = itemView.findViewById(R.id.Date_id);
            popup= itemView.findViewById(R.id.pop_menu_id);
        }
    }
}
