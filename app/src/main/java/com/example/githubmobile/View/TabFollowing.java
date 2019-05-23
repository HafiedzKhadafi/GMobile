package com.example.githubmobile.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.example.githubmobile.Adapter.FollowingAdapter;
import com.example.githubmobile.Model.Following.GetFollowingResponse;
import com.example.githubmobile.Model.Login.GetUserResponse;
import com.example.githubmobile.Presenter.LoginPresenter.GetUserPresenter;
import com.example.githubmobile.Presenter.LoginPresenter.GetUserView;
import com.example.githubmobile.R;
import com.example.githubmobile.Remote.ApiGithub;
import com.example.githubmobile.Remote.BaseApp;
import com.example.githubmobile.Remote.SharedPreferencesConfig;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabFollowing extends  Fragment implements GetUserView {
    ListView followingList;
    static ArrayList<GetFollowingResponse> getFollowingResponses = new ArrayList();
    FollowingAdapter followingAdapter;
    SharedPreferencesConfig config;
    GetUserPresenter getUserPresenter;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_following, container, false);
        followingList = rootView.findViewById(R.id.following_list);

        ApiGithub service = BaseApp.serviceGitHub;

        getUserPresenter = new GetUserPresenter(getContext(), (GetUserView) this);
        config = new SharedPreferencesConfig(getContext());
        getUserPresenter.ambil(config.getToken());
        return rootView;
    }

    @Override
    public void onSucces(GetUserResponse loginResponse) {
        username = loginResponse.getNama();
        ApiGithub service = BaseApp.serviceGitHub;

        Call<List<GetFollowingResponse>> allFollowing = service.loadFollowing(username);

        allFollowing.enqueue(new Callback<List<GetFollowingResponse>>() {
            @Override
            public void onResponse(Call<List<GetFollowingResponse>> call, Response<List<GetFollowingResponse>> response) {
                TabFollowing.getFollowingResponses = (ArrayList<GetFollowingResponse>) response.body();

                System.out.println(TabFollowing.getFollowingResponses.get(0).getLogin());

                followingAdapter = new FollowingAdapter(getContext(), TabFollowing.getFollowingResponses);
                followingAdapter.notifyDataSetChanged();
                followingList.setAdapter(followingAdapter);
            }

            @Override
            public void onFailure(Call<List<GetFollowingResponse>> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String failureMessage) {
        Toast.makeText(getContext(), failureMessage, Toast.LENGTH_SHORT).show();
    }
}

