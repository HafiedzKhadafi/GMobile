package com.example.githubmobile.Presenter;

import android.content.Context;

import com.example.githubmobile.Model.Login.UpdateResponse;
import com.example.githubmobile.Remote.BaseApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePresenter {
    private Context context;
    private  UpdateView updateView;

    public UpdatePresenter(Context context, UpdateView updateView) {
        this.context = context;
        this.updateView = updateView;
    }

    public void update(String token, String name, String email){
        BaseApp.serviceLogin.updateUser(token, name, email).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.isSuccessful()) {
                    updateView.onSucces(response.body());
                } else {
                    updateView.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                updateView.onFailure(t.getMessage());
            }
        });
    }
}
