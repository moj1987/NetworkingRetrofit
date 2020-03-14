package com.example.networking_retrofit;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {

    private Main main;

    Main getMain() {
        return main;
    }


    class Main {
        double temp;
        double pressure;

        @NonNull
        @Override
        public String toString() {
            return "temp=" + temp
                    +", \n"
                    +"pressure=" + pressure;
        }
    }
}
