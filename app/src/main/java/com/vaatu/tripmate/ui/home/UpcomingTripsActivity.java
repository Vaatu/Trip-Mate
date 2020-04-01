package com.vaatu.tripmate.ui.home;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vaatu.tripmate.R;
import com.vaatu.tripmate.data.remote.network.FirebaseDB;
import com.vaatu.tripmate.ui.home.addButtonActivity.AddBtnActivity;
import com.vaatu.tripmate.ui.splash.SplashActivity;
import com.vaatu.tripmate.ui.user.SignUp;
import com.vaatu.tripmate.ui.user.UserCycleActivity;
import com.vaatu.tripmate.utils.TripModel;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UpcomingTripsActivity extends AppCompatActivity {
    android.app.AlertDialog alert;

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDB fbdb;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_trips);
        Intent i = getIntent();
        String username = i.getStringExtra(SignUp.username);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        //Overlay Permission
        checkPermission();
        fbdb = FirebaseDB.getInstance();
        fbdb.saveUserToFirebase(currentUser.getEmail(), username);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent addButtonActivity = new Intent(UpcomingTripsActivity.this, AddBtnActivity.class);
//                startActivity(addButtonActivity);
                Intent i = new Intent(UpcomingTripsActivity.this, AddBtnActivity.class);
                startActivityForResult(i, 55);

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_sync, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, mAppBarConfiguration);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    //TODO       ProgressBar
                if (menuItem.getItemId() == R.id.nav_sync) {
                    Toast.makeText(UpcomingTripsActivity.this, "I'm sync", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_nav_home_to_nav_sync);
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    fab.hide();
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_logout) {
                    //Navigation here
                    fab.hide();
                    Toast.makeText(UpcomingTripsActivity.this, "I'm logout", Toast.LENGTH_SHORT).show();
                    signOut();
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    Intent mainIntent = new Intent(UpcomingTripsActivity.this, UserCycleActivity.class);
                    startActivity(mainIntent);
                    finish();

                    return true;
                } else if (menuItem.getItemId() == R.id.nav_home) {
                    //Navigation here

                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_history) {
                    //Navigation here
                    navController.navigate(R.id.action_HomeFragment_to_History);
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    fab.hide();
                    return true;
                }

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.upcoming_trips, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.hide();
        fab.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (55): {
                if (resultCode == Activity.RESULT_OK) {
                    TripModel newtrip = (TripModel) data.getSerializableExtra("NEWTRIP");
                    if (newtrip != null) {
                        fbdb.saveTripToDatabase(newtrip);
                    } else {
                        Toast.makeText(this, "Something wend wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
            case  RESULT_OK: {
                if (checkPermission()) {

                } else {
                    reqPermission();
                }
            }
        }
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
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
        final android.app.AlertDialog.Builder alertBuilder = new android.app.AlertDialog.Builder(this);
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
}
