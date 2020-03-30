package com.vaatu.tripmate.utils.AlarmManagerReciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmEventReciever extends BroadcastReceiver {
    private Context context;

    //recieve from other applications
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        //Toast.makeText(context,"service music",Toast.LENGTH_LONG).show();
        Log.i("service ", "serviceAlarmHasFired");
        Toast.makeText(context.getApplicationContext(), "Alarm Manager just ran", Toast.LENGTH_LONG).show();
        Intent myService = new Intent(context, MyRingingService.class);

        context.startService(myService);
        displayAlert();

    }

    private void displayAlert() {
      Intent i = new Intent(context,MyDialogActivity.class);

        i.setClass(context,MyDialogActivity.class);
       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       context.startActivity(i);
    }
}
