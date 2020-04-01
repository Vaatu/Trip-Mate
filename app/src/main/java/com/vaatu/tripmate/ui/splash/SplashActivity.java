package com.vaatu.tripmate.ui.splash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Button;

import com.vaatu.tripmate.R;
import com.vaatu.tripmate.service.FloatingWindowService;
import com.vaatu.tripmate.ui.home.UpcomingTripsActivity;
import com.vaatu.tripmate.ui.home.addButtonActivity.AddBtnActivity;
import com.vaatu.tripmate.ui.user.UserCycleActivity;

public class SplashActivity extends AppCompatActivity {
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    AlertDialog alert;
    boolean started = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        if (isMyServiceRunning(FloatingWindowService.class)){
            started = true;

        }
        start_stop();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent mainIntent = new Intent(SplashActivity.this, UserCycleActivity.class);
//                SplashActivity.this.startActivity(mainIntent);
//                SplashActivity.this.finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);

    }



    public void start_stop() {
        if (checkPermission()) {
            if (started) {
                stopService(new Intent(SplashActivity.this, FloatingWindowService.class));
               // start_stop.setText("Start");
                started = false;
            } else {
                startService(new Intent(SplashActivity.this, FloatingWindowService.class));
                //start_stop.setText("Stop");
                started = true;

            }
        }else {
            reqPermission();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            if (checkPermission()) {
                start_stop();
            } else {
                reqPermission();
            }
        }
    }


    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                reqPermission();
                return false;
            }
            else {
                return true;
            }
        }else{
            return true;
        }

    }

    private void reqPermission(){
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Screen overlay detected");
        alertBuilder.setMessage("Enable 'Draw over other apps' in your system setting.");
        alertBuilder.setPositiveButton("OPEN SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,RESULT_OK);
            }
        });

        alert = alertBuilder.create();
        alert.show();


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        alert.dismiss();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


}
