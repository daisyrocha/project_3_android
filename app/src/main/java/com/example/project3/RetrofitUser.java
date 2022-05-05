package com.example.project3;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is a class for retrofit instance
 */
public class RetrofitUser {

    private static Retrofit userRetrofit;
    private static final String USER_BASE_URL = "https://shielded-retreat-83853.herokuapp.com/api";

    public static Retrofit getUserRetrofitInstance() {
        if(userRetrofit == null) {
            userRetrofit = new Retrofit.Builder()
                    .baseUrl(USER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return userRetrofit;
    }
}
