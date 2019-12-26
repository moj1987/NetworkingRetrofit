package com.example.networking_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapApi {

    //write the relative URL in the parentheses
    @GET("data/2.5/weather")
    Call<CurrentWeather> getPosts(@Query("q") String location,
                                  @Query("APPID") String api_id);




}
