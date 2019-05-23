package com.example.githubmobile.View;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class DetailFollowing extends AppCompatActivity implements GetUserView{
    SharedPreferencesConfig config;
    static  com.example.githubmobile.Model.User.GetUserResponse userResponse;
    GetUserPresenter getUserPresenter;
    String username;
    TextView tvUser, tvBio, tvRepo, tvFollowing, tvFollowers, tvLink;
    ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfollowing);
        imgAvatar = findViewById(R.id.d_avatar);
        tvUser = findViewById(R.id.d_username);
        tvBio = findViewById(R.id.d_bio);
        tvRepo = findViewById(R.id.d_repocount);
        tvFollowers = findViewById(R.id.d_followerscount);
        tvFollowing = findViewById(R.id.d_followingcount);
        tvLink = findViewById(R.id.d_link);

        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(userResponse.getHtmlUrl()));
                v.getContext().startActivity(intent);
            }
        });

        ApiGithub service = BaseApp.serviceGitHub;

        username = getIntent().getStringExtra("usn");

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
    public void onSucces(GetUserResponse loginResponse) {

    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onFailure(String failureMessage) {

    }
}
