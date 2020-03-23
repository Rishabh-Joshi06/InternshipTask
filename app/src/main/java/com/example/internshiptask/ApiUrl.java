package com.example.internshiptask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiUrl {
    String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("all")
    Call<List<CountriesDTO>> getName();

    @GET
    Call<List<CountriesDTO>>getInfo( @Url String url);


}

