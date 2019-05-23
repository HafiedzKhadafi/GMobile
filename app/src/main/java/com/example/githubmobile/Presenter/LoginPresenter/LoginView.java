package com.example.githubmobile.Presenter.LoginPresenter;

import com.example.githubmobile.Model.Login.LoginResponse;

public interface LoginView {
    void onSucces(LoginResponse login);
    void onError(String errorMessage);
    void onFailure(String failureMessage);
}
