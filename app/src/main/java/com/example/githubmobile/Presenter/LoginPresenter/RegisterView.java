package com.example.githubmobile.Presenter.LoginPresenter;

public interface RegisterView {
    void onSucces(String regis);
    void onError(String errorMessage);
    void onFailure(String failureMessage);
}
