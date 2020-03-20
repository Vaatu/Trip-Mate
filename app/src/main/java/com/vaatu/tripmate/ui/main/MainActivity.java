package com.vaatu.tripmate.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.vaatu.tripmate.R;
import com.vaatu.tripmate.ui.login.LoginPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.MvpView  {

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
    }

    //      MvpView methods       //
    @Override
    public void showSignInScreen() {
        Toast.makeText(this, "Taking User to the SignIn Screen", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignUpScreen() {
        Toast.makeText(this, "Taking user to the Sign Up Screen", Toast.LENGTH_SHORT).show();
    }
}
