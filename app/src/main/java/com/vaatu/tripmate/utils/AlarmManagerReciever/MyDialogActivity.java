package com.vaatu.tripmate.utils.AlarmManagerReciever;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.vaatu.tripmate.R;
import com.vaatu.tripmate.utils.TripModel;

public class MyDialogActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_dialog);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra(AlarmEventReciever.RECEIVED_TRIP);
        TripModel tm = (TripModel) b.getSerializable(AlarmEventReciever.RECEIVED_TRIP_SEND_SERIAL);
        if (tm != null) {
            AlertDialog.Builder Builder = new AlertDialog.Builder(this)
                    .setMessage("Your Trip is now on...")
                    .setTitle("Trip reminder")
                    .setIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setNegativeButton("Snooze", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MyDialogActivity.this, "Trip Snooze", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MyDialogActivity.this.finish();
                            Toast.makeText(MyDialogActivity.this, "Trip Will Start", Toast.LENGTH_SHORT).show();

                            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + tm.getEndloc());
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            startActivity(mapIntent);
                        }
                    }).setNeutralButton("Cancel Trip", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MyDialogActivity.this, "Trip Canceled", Toast.LENGTH_SHORT).show();
                        }
                    });

            AlertDialog alertDialog = Builder.create();
            alertDialog.show();
        } else {
            Toast.makeText(this, "Smth went wrong !", Toast.LENGTH_SHORT).show();
        }

    }
}
