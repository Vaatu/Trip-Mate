package com.vaatu.tripmate.utils.AlarmManagerReciever;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyRingingService extends Service {

    MediaPlayer player;

    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(MyRingingService.this, "music plyn from on start command ", Toast.LENGTH_LONG).show();
        //player = MediaPlayer.create(this,R.raw.sound);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }


    public  void stopmusic(){

        player.stop();
    }
}
