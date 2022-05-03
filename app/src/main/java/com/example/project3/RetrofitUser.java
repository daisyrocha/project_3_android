package com.example.project3;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * I will implement this later, when I figure out how I am going to connect the
 * user DB to Android Studio
 */
public class RetrofitUser {

    private static Retrofit userRetrofit;
    private static String BASE_URL = "https://shielded-retreat-83853.herokuapp.com/api";

    public static Retrofit getUserRetrofitInstance() {
        if(userRetrofit == null) {
            userRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return userRetrofit;
    }
}
