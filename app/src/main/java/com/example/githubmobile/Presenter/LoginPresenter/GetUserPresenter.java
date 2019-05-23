package com.example.githubmobile.Presenter.LoginPresenter;

import android.content.Context;
import com.example.githubmobile.Model.Login.GetUserResponse;
import com.example.githubmobile.Remote.BaseApp;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserPresenter {
    private Context context;
    private GetUserView getUserView;

    public GetUserPresenter(Context context, GetUserView getUserView) {
        this.context = context;
        this.getUserView = getUserView;
    }

    public void  ambil(String token){
        BaseApp.serviceLogin.getUser(token).enqueue(new Callback<List<GetUserResponse>>() {
            @Override
            public void onResponse(Call<List<GetUserResponse>> call, Response<List<GetUserResponse>> response) {
                getUserView.onSucces(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<GetUserResponse>> call, Throwable t) {
                getUserView.onFailure(t.getMessage());
            }
        });
    }
}
