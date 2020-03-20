package com.vaatu.tripmate.ui.main;

public interface MainContract {

    interface MvpView{
        void showSignInScreen();

        void showSignUpScreen();

    }
    interface MvpPresenter{
        void handleSignInButtonClick();

        void handleSignUpButton();


    }
}
