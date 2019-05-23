package com.example.githubmobile.Remote;

import com.example.githubmobile.Model.Login.GetUserResponse;
import com.example.githubmobile.Model.Login.LoginResponse;
import com.example.githubmobile.Model.Login.UpdateResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiLogin {
    @FormUrlEncoded
    @POST("/users")
    Call<Void> createUser(@Field("name") String name,
                                @Field("email") String email,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("/users/login")
    Call<LoginResponse> loginUser(@Field("email") String email,
                               @Field("password") String password);

    @GET("/users/")
    Call<List<GetUserResponse>> getUser(@Header("x-access-token") String token);

    @FormUrlEncoded
    @PUT("/users/")
    Call<UpdateResponse> updateUser(@Header("x-access-token") String token,
                                    @Field("name") String name,
                                    @Field("email") String email);

}
