package com.example.githubmobile.Remote;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.githubmobile.R;

public class SharedPreferencesConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesConfig(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_preference),status);
        editor.commit();
    }

    public void token(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token",token);
        editor.commit();
    }

    public String getToken(){
        return sharedPreferences.getString("Token",null);
    }

    public boolean readLoginStatus(){
        boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference), false);
        return status;
    }

    public  void logout(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
