package com.vaatu.tripmate.ui.login;

/**
 * Handling action from the View and updating the UI
 */
public class LoginPresenter implements LoginContract.MvpPresenter {
    private LoginContract.MvpView mView;

    LoginPresenter(LoginContract.MvpView view){
        mView = view;
    }


}
