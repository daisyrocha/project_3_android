package com.example.project3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HeroesApi {
    @GET("/2/powerstats")
    Call<HeroesJson> getPowerStats();
}
