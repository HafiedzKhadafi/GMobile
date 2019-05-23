package com.example.githubmobile.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.example.githubmobile.Adapter.ReposAdapter;
import com.example.githubmobile.Model.Login.GetUserResponse;
import com.example.githubmobile.Model.Repos.ReposResponse;
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

public class TabRepo extends  Fragment implements GetUserView{
    ListView repoList;
    static ArrayList<ReposResponse> reposResponses = new ArrayList();
    ReposAdapter reposAdapter;
    SharedPreferencesConfig config;
    GetUserPresenter getUserPresenter;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_repo, container, false);
        repoList = rootView.findViewById(R.id.repo_list);

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

        Call<List<ReposResponse>> allRepos = service.loadRepo(username);

        allRepos.enqueue(new Callback<List<ReposResponse>>() {
            @Override
            public void onResponse(Call<List<ReposResponse>> call, Response<List<ReposResponse>> response) {
                TabRepo.reposResponses = (ArrayList<ReposResponse>) response.body();

                System.out.println(TabRepo.reposResponses.get(0).getName());

                reposAdapter = new ReposAdapter(getContext(), TabRepo.reposResponses);
                reposAdapter.notifyDataSetChanged();
                repoList.setAdapter(reposAdapter);
            }

            @Override
            public void onFailure(Call<List<ReposResponse>> call, Throwable t) {
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
