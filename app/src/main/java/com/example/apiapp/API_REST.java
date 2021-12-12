package com.example.apiapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface API_REST {
    String URL = "http://raw.githubusercontent.com/Zatuar/Projet4A_Mobile/master/data/";

    @GET("personnages_data.json")
    Call<List<Tasks>> loadChanges();
}
