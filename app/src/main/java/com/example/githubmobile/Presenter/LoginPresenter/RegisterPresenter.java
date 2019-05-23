package com.example.githubmobile.Presenter.LoginPresenter;

import android.content.Context;
import com.example.githubmobile.Remote.BaseApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private Context context;
    private RegisterView regisView;

    public RegisterPresenter(Context context, RegisterView regisView) {
        this.context = context;
        this.regisView = regisView;
    }

    public void create(String name, String email, String password){
        BaseApp.serviceLogin.createUser(name, email, password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    regisView.onSucces(response.message());
                } else {
                    regisView.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                regisView.onFailure(t.getMessage());
            }
        });

    }
}
