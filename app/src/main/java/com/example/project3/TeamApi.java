package com.example.project3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamApi {
    @GET("/allTeams")
    Call<List<Team>> getAllTeams();
}
