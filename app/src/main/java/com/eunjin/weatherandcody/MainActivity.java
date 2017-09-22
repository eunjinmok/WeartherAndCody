package com.eunjin.weatherandcody;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eunjin.weatherandcody.model.current.CurrentWeather;
import com.eunjin.weatherandcody.retrofit.WeatherUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView mWeatherImageView;
    private TextView mWeatherTextView;
    private TextView mTempTextView;
    private TextView mUvTextView;
    private TextView mDustTextView;
    private ImageView mCodyImageView;
    private WeatherUtil mWeatherUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mWeatherImageView = (ImageView) findViewById(R.id.weather_image_view);
        mWeatherTextView = (TextView) findViewById(R.id.weather_text_view);
        mTempTextView = (TextView) findViewById(R.id.temp_text_view);
        mUvTextView = (TextView) findViewById(R.id.uv_text_view);
        mDustTextView = (TextView) findViewById(R.id.dust_text_view);
        mCodyImageView = (ImageView) findViewById(R.id.cody_image_view);
        mWeatherUtil = new WeatherUtil();

        Intent intent = getIntent();
        String intentCityName = intent.getStringExtra("cityName");

        search(intentCityName);

    }

    private void search(String cityName) {
        mWeatherUtil.getApiService().getCurrentWeather(cityName).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                String url = "http://openweathermap.org/img/w/" + response.body().getWeather().get(0).getIcon() + ".png";
                Glide.with(MainActivity.this).load(url).into(mWeatherImageView);
                mWeatherTextView.setText("날씨 : " + response.body().getWeather().get(0).getDescription());
                mTempTextView.setText("기온 : " + response.body().getMain().getTemp());
                mUvTextView.setText("자외선 : ");
                mDustTextView.setText("미세먼지 : ");


            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {

            }
        });
    }
}
