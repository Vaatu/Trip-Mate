package com.vaatu.tripmate.utils.alarmManagerReciever;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.vaatu.tripmate.R;
import com.vaatu.tripmate.data.remote.network.FirebaseDB;
import com.vaatu.tripmate.service.DialognotificationService;
import com.vaatu.tripmate.utils.TripModel;

import static com.vaatu.tripmate.utils.alarmManagerReciever.AlarmEventReciever.RECEIVED_TRIP;
import static com.vaatu.tripmate.utils.alarmManagerReciever.AlarmEventReciever.RECEIVED_TRIP_SEND_SERIAL;

public class MyDialogActivity extends Activity {
    DialognotificationService mService;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_dialog);
        FirebaseDB firebaseDB = FirebaseDB.getInstance();

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra(RECEIVED_TRIP);
        TripModel tm = (TripModel) b.getSerializable(RECEIVED_TRIP_SEND_SERIAL);
        if (tm != null) {
            startAlarmRingTone(r);
            AlertDialog.Builder Builder = new AlertDialog.Builder(this)
                    .setMessage("Your Trip is now on...")
                    .setTitle("Trip reminder")
                    .setIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setNegativeButton("Snooze", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MyDialogActivity.this, "Trip Snooze", Toast.LENGTH_SHORT).show();
                            stopAlarmRingTone(r);
                            startDialogService(tm);

                            finish();
                        }
                    })
                    .setPositiveButton("Start Trip", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MyDialogActivity.this, "Trip Will Start", Toast.LENGTH_SHORT).show();
                            tm.setStatus("Done!");
                            firebaseDB.addTripToHistory(tm);
                            firebaseDB.removeFromUpcoming(tm);
                            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + tm.getEndloc());
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");

                            stopAlarmRingTone(r);
                            alertDialog.dismiss();
                            startActivity(mapIntent);
                            finish();
                        }
                    }).setNeutralButton("Cancel Trip", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MyDialogActivity.this, "Trip Canceled", Toast.LENGTH_SHORT).show();
                            tm.setStatus("Canceled!");
                            firebaseDB.addTripToHistory(tm);
                            firebaseDB.removeFromUpcoming(tm);

                            stopAlarmRingTone(r);
                            alertDialog.dismiss();

                            finish();
                        }
                    });

            alertDialog = Builder.create();
            alertDialog.show();

        } else {
            Toast.makeText(this, "Smth went wrong !", Toast.LENGTH_SHORT).show();
        }

    }

    public void startDialogService(TripModel tm) {
        Intent service = new Intent(this, DialognotificationService.class);

        service.putExtra(RECEIVED_TRIP_SEND_SERIAL, tm);
        service.putExtra("test","MEMO");
        startService(service);
        bindService(service,mServiceConnection,BIND_ADJUST_WITH_ACTIVITY);
        alertDialog.dismiss();
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ((DialognotificationService.MyBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void startAlarmRingTone(Ringtone r) {
        r.play();
    }

    public void stopAlarmRingTone(Ringtone r) {
        r.stop();
    }
}
