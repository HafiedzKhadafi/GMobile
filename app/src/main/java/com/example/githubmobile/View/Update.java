package com.example.githubmobile.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubmobile.Model.Login.GetUserResponse;
import com.example.githubmobile.Model.Login.UpdateResponse;
import com.example.githubmobile.Presenter.LoginPresenter.GetUserPresenter;
import com.example.githubmobile.Presenter.LoginPresenter.GetUserView;
import com.example.githubmobile.Presenter.UpdatePresenter;
import com.example.githubmobile.Presenter.UpdateView;
import com.example.githubmobile.R;
import com.example.githubmobile.Remote.ApiGithub;
import com.example.githubmobile.Remote.ApiLogin;
import com.example.githubmobile.Remote.BaseApp;
import com.example.githubmobile.Remote.SharedPreferencesConfig;

import retrofit2.Call;

public class Update extends AppCompatActivity implements GetUserView,UpdateView {
    TextView tvName, tvEmail, tvPassword;
    Button btnSubmit;
    SharedPreferencesConfig config;
    GetUserPresenter getUserPresenter;
    UpdatePresenter updatePresenter;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getUserPresenter = new GetUserPresenter(getApplicationContext(), (GetUserView) this);
        config = new SharedPreferencesConfig(getApplicationContext());
        getUserPresenter.ambil(config.getToken());

        tvName = findViewById(R.id.u_usn);
        tvEmail = findViewById(R.id.u_email);
        tvPassword = findViewById(R.id.u_psw);
    }

    public void updateUser(View view){
        String usn = tvName.getText().toString();
        String email = tvEmail.getText().toString();

        updatePresenter = new UpdatePresenter(getApplicationContext(), this);
        updatePresenter.update(config.getToken(), usn, email);
    }

    @Override
    public void onSucces(GetUserResponse loginResponse) {
        tvName.setText(loginResponse.getNama());
        tvEmail.setText(loginResponse.getEmail());
    }

    @Override
    public void onSucces(UpdateResponse update) {
        Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Profil.class));
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
