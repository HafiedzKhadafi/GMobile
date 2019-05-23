package com.example.githubmobile.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.githubmobile.Model.Login.LoginResponse;
import com.example.githubmobile.Presenter.LoginPresenter.LoginPresenter;
import com.example.githubmobile.Presenter.LoginPresenter.LoginView;
import com.example.githubmobile.R;
import com.example.githubmobile.Remote.SharedPreferencesConfig;

public class Login extends AppCompatActivity implements LoginView {
    private SharedPreferencesConfig sharedPreferencesConfig;
    private TextView tvCreateAccount;
    private LoginPresenter loginPresenter;
    private EditText edUsername, edPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sharedPreferencesConfig = new SharedPreferencesConfig(getApplicationContext());

        edUsername = (EditText) findViewById(R.id.login_username);
        edPassword = (EditText) findViewById(R.id.login_password);
        tvCreateAccount = findViewById(R.id.tv_createaccount);
        btnLogin = (Button) findViewById(R.id.login_button);

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CreateAccount.class);
                v.getContext().startActivity(i);
            }
        });
        if(sharedPreferencesConfig.readLoginStatus()==true)
            startActivity(new Intent(this, Tab.class));

    }

    public void loginUser(View view) {
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        loginPresenter = new LoginPresenter(getApplicationContext(),this);
        loginPresenter.login(username, password);
    }

    @Override
    public void onSucces(LoginResponse login) {
        Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_SHORT).show();
        sharedPreferencesConfig.writeLoginStatus(true);
        sharedPreferencesConfig.token(login.getToken());
        startActivity(new Intent(this, Tab.class));
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String failureMessage) {
        Toast.makeText(getApplicationContext(),failureMessage,Toast.LENGTH_SHORT).show();
    }
}