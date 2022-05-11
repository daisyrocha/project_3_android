package com.example.project3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @FormUrlEncoded

    @GET("/allUsers")
    // not sure about this..
    Call<List<User>> getAllUsers();

    @POST("/addUser")
    Call<User> createUser(@Field("username") String username, @Field("password") String password);
}
