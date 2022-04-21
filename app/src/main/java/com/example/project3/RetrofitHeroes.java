package com.example.project3;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHeroes {

    /**
     * Here we are creating a class for retrofit instance
     * We are making a singleton method to create a retrofit hero instance
     */
    private static Retrofit heroesRetrofit;
    private static String HERO_BASE_URL = "https://superheroapi.com/api.php/109324175078057/";

    public static Retrofit getRetrofitInstance() {
        if (heroesRetrofit == null) {
            heroesRetrofit = new Retrofit.Builder()
                    .baseUrl(HERO_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return heroesRetrofit;
    }
}
