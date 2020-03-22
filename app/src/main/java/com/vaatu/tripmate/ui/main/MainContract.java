package com.vaatu.tripmate.ui.main;

public interface MainContract {

    interface MvpView{
       void showLoading();
       void hideLoading();
       void showErrorMsg(String errorMsg);

    }
    interface MvpPresenter{
        void handleAddButton();
        void handleNavHomeButton();
        void handleNavHistoryBtn();
        void handleNavSyncBtn();
        void handleNavLogoutBtn();


    }
}
