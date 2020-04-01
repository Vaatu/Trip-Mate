package com.vaatu.tripmate.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;

import com.vaatu.tripmate.R;
import com.vaatu.tripmate.ui.home.UpcomingTripsActivity;
import com.vaatu.tripmate.ui.home.addButtonActivity.AddBtnActivity;
import com.vaatu.tripmate.ui.user.UserCycleActivity;

public class SplashActivity extends AppCompatActivity {
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 1500;
WindowManager windowManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Main-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, UserCycleActivity.class);
                //Intent mainIntent = new Intent(SplashActivity.this, AddBtnActivity.class);

                // Intent mainIntent = new Intent(SplashActivity.this, UpcomingTripsActivity.class);


                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
