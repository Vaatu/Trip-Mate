package com.vaatu.tripmate.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vaatu.tripmate.R;

/**
 * Login Forum
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.MvpView {

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter = new LoginPresenter(this);

    }
}
