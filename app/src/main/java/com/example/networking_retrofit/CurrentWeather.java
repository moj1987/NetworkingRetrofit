package com.example.networking_retrofit;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {

    private int id;

    @SerializedName("main")
    private String current;

    private String description;

    public int getId() {
        return id;
    }

    public String getCurrent() {
        return current;
    }

    public String getDescription() {
        return description;
    }
}
