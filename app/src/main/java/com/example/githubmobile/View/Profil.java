package com.example.githubmobile.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubmobile.Model.Login.GetUserResponse;
import com.example.githubmobile.Presenter.LoginPresenter.GetUserPresenter;
import com.example.githubmobile.Presenter.LoginPresenter.GetUserView;
import com.example.githubmobile.R;
import com.example.githubmobile.Remote.ApiGithub;
import com.example.githubmobile.Remote.BaseApp;
import com.example.githubmobile.Remote.SharedPreferencesConfig;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profil extends AppCompatActivity implements GetUserView{
    SharedPreferencesConfig config;
    static  com.example.githubmobile.Model.User.GetUserResponse userResponse;
    GetUserPresenter getUserPresenter;
    String username;
    TextView tvUser, tvBio, tvRepo, tvFollowing, tvFollowers;
    ImageView imgAvatar;
    Button btnLogout, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        imgAvatar = findViewById(R.id.profil_avatar);
        tvUser = findViewById(R.id.profil_username);
        tvBio = findViewById(R.id.profil_bio);
        tvRepo = findViewById(R.id.profil_repocount);
        tvFollowers = findViewById(R.id.profil_followerscount);
        tvFollowing = findViewById(R.id.profil_followingcount);
        btnLogout = findViewById(R.id.profil_logout);
        btnUpdate = findViewById(R.id.profil_update);

        getUserPresenter = new GetUserPresenter(getApplicationContext(), (GetUserView) this);
        config = new SharedPreferencesConfig(getApplicationContext());
        getUserPresenter.ambil(config.getToken());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Login.class);
                v.getContext().startActivity(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                config.logout(config.getToken());
                Intent i = new Intent(v.getContext(), Update.class);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public void onSucces(GetUserResponse loginResponse) {
        username = loginResponse.getNama();
        ApiGithub service = BaseApp.serviceGitHub;

        Call<com.example.githubmobile.Model.User.GetUserResponse> user = service.loadUser(username);

        user.enqueue(new Callback<com.example.githubmobile.Model.User.GetUserResponse>() {
            @Override
            public void onResponse(Call<com.example.githubmobile.Model.User.GetUserResponse> call, Response<com.example.githubmobile.Model.User.GetUserResponse> response) {
                Profil.userResponse = (com.example.githubmobile.Model.User.GetUserResponse) response.body();

                System.out.println(Profil.userResponse.getLogin());
                System.out.println(Profil.userResponse.getBio());
                System.out.println(Profil.userResponse.getPublicRepos());
                System.out.println(Profil.userResponse.getFollowers());
                System.out.println(Profil.userResponse.getFollowing());

                String repo = Integer.toString(userResponse.getPublicRepos());
                String followers = Integer.toString(userResponse.getFollowers());
                String following = Integer.toString(userResponse.getFollowing());

                tvUser.setText(userResponse.getLogin());
                tvBio.setText(userResponse.getBio());
                tvRepo.setText(repo);
                tvFollowers.setText(followers);
                tvFollowing.setText(following);
                Picasso.get()
                        .load(userResponse.getAvatarUrl())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(imgAvatar);
            }

            @Override
            public void onFailure(Call<com.example.githubmobile.Model.User.GetUserResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String failureMessage) {
        Toast.makeText(getApplicationContext(), failureMessage, Toast.LENGTH_SHORT).show();
    }
}
