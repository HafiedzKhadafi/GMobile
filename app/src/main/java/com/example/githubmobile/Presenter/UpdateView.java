package com.example.githubmobile.Presenter;

import com.example.githubmobile.Model.Login.UpdateResponse;

public interface UpdateView {
    void onSucces(UpdateResponse update);
    void onError(String errorMessage);
    void onFailure(String failureMessage);
}
