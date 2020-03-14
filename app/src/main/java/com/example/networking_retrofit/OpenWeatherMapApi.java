package com.example.networking_retrofit;

import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OpenWeatherMapApi {

    //write the relative URL in the parentheses
    @GET("data/2.5/weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String location,
                                           @Query("APPID") String api_id);

    @GET("data/2.5/forcast/hourly")
    Call<CurrentWeather> getFourDaysForcas
    t(@Query("q") String location,
                                            @Query("APPID") String api_id);

    @GET("posts")
    Call<List<Post>> getPosts();


    @POST
    Call<List<Post>> creatPost(@Body Post post);


}
