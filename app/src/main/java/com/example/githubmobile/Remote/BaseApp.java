package com.example.githubmobile.Remote;

import android.app.Application;
import com.example.githubmobile.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApp extends Application {
    public  static ApiLogin serviceLogin;
    public  static  ApiGithub serviceGitHub;
    private String url1 = "https://yutub-api.herokuapp.com";
    private String url2 = "https://api.github.com";

    @Override
    public void onCreate(){
        super.onCreate();
        serviceLogin = getRetrofit1().create(ApiLogin.class);
        serviceGitHub = getRetrofit2().create(ApiGithub.class);
    }

    private Retrofit getRetrofit1(){
        return new Retrofit.Builder()
                .baseUrl(url1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private Retrofit getRetrofit2(){
        return new Retrofit.Builder()
                .baseUrl(url2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getHttpLogInterceptor())
                .build();
    }

    private Interceptor getHttpLogInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level;
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY;
        } else {
            level = HttpLoggingInterceptor.Level.NONE;
        } loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}