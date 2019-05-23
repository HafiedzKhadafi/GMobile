package com.example.githubmobile.Presenter.LoginPresenter;

import android.content.Context;
import com.example.githubmobile.Model.Login.LoginResponse;
import com.example.githubmobile.Remote.BaseApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private Context context;
    private LoginView loginView;

    public LoginPresenter(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    public void login(String email, String password) {
        BaseApp.serviceLogin.loginUser(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginView.onSucces(response.body());
                } else {
                    loginView.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginView.onFailure(t.getMessage());
            }
        });
    }
}
