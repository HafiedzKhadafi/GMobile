package com.example.githubmobile.Remote;

import com.example.githubmobile.Model.Following.GetFollowingResponse;
import com.example.githubmobile.Model.Repos.ReposResponse;
import com.example.githubmobile.Model.User.GetUserResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiGithub {

        @GET("/users/{user}/repos")
        Call<List<ReposResponse>> loadRepo(@Path("user") String user);

        @GET("/users/{user}/following")
        Call<List<GetFollowingResponse>> loadFollowing(@Path("user") String user);

        @GET("/users/{user}")
        Call<GetUserResponse> loadUser(@Path("user") String user);

}
