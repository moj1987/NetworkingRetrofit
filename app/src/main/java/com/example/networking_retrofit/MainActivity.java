package com.example.networking_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" https://api.openweathermap.org/")
                // .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherMapApi openWeatherMapApi = retrofit.create(OpenWeatherMapApi.class);

        Call<CurrentWeather> call =
                openWeatherMapApi.getPosts("London", "17e3b4694f77e6f9a54f2a1012cce0a6");

        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                CurrentWeather currentWeathers = response.body();

                //for (CurrentWeather currentWeather : currentWeathers) {
                String content = "";
                content += "ID" + currentWeathers.getId() + "\n";
                content += "main" + currentWeathers.getDescription() + "\n";


                textViewResult.append(content);

                //}
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });

    }
}
