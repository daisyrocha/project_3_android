package com.example.project3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PowerStatsApi {
    /**
     * This is a GET request which will get data from the server
     * We have the base URL in our RetrofitHeroes class
     * "powerstats" is our relative URL
     */

    @GET("powerstats")
    Call<PowerStats> getStats();
}
