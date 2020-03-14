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
    private OpenWeatherMapApi openWeatherMapApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view);

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl(" https://api.openweathermap.org/")
                .baseUrl(" https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        openWeatherMapApi = retrofit.create(OpenWeatherMapApi.class);

        //callCurrentWeather();
        //getPosts();
        creatPost();
    }

    private void creatPost() {

    }

    private void callCurrentWeather() {
        Call<CurrentWeather> call =
                openWeatherMapApi.getCurrentWeather("Toronto", "17e3b4694f77e6f9a54f2a1012cce0a6");

        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                CurrentWeather currentWeathers = response.body();

                //for (CurrentWeather currentWeather : currentWeathers)
                String content = "";
                content += "" + currentWeathers.getMain() + "\n";

                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }

    private void getPosts() {
        Call<List<Post>> call = openWeatherMapApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts){
                    String content = "";
                content += "ID= " + post.getId() + "\n";
                content += "User ID: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "body: " + post.getBody() + "\n\n";

                textViewResult.append(content);
            }
        }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
            });
    }
}
