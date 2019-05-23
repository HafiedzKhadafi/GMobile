package com.example.githubmobile.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.githubmobile.Presenter.LoginPresenter.RegisterPresenter;
import com.example.githubmobile.Presenter.LoginPresenter.RegisterView;
import com.example.githubmobile.R;

public class CreateAccount extends AppCompatActivity implements RegisterView {
    EditText etNama, etEmail, etPassword;
    Button btnSubmit;
    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        etNama = findViewById(R.id.ca_usn);
        etEmail = findViewById(R.id.ca_email);
        etPassword = findViewById(R.id.ca_psw);
        btnSubmit = findViewById(R.id.ca_button);

        registerPresenter = new RegisterPresenter(getApplicationContext(), this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.create(etNama.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    @Override
    public void onSucces(String regis) {
        Toast.makeText(getApplicationContext(), "Akun Berhasil Dibuat!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Login.class));
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