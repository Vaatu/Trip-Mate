package com.vaatu.tripmate.ui.main;

public class MainPresenter implements MainContract.MvpPresenter {
    private MainContract.MvpView mView;

    MainPresenter (MainContract.MvpView view){
        mView = view;
    }


    //      Presenter Methods //
    @Override
    public void handleSignInButtonClick() {
        mView.showSignInScreen();
    }

    @Override
    public void handleSignUpButton() {
        mView.showSignUpScreen();
    }
}
