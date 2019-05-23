package com.example.githubmobile.Presenter.LoginPresenter;

import com.example.githubmobile.Model.Login.GetUserResponse;

public interface GetUserView {
    void onSucces(GetUserResponse loginResponse);
    void onError(String errorMessage);
    void onFailure(String failureMessage);
}
