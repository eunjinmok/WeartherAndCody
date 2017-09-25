package com.eunjin.weatherandcody;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eunjin.weatherandcody.model.current.CurrentWeather;
import com.eunjin.weatherandcody.model.uv.CurrentUV;
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
    private double mTemp;

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

        // 현재 날씨와 현재 기온을 가져온다
        mWeatherUtil.getApiService().getCurrentWeather(cityName).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                String url = "http://openweathermap.org/img/w/"
                        + response.body().getWeather().get(0).getIcon() + ".png";
                Glide.with(MainActivity.this).load(url).into(mWeatherImageView);
                mWeatherTextView.setText("날씨 : " + response.body().getWeather().get(0).getDescription());

                mTemp = response.body().getMain().getTemp();
                mTempTextView.setText("기온 : " + mTemp);

            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {

            }
        });

        // 현재 자외선 지수를 가져온다.
        // 현재 값은 서울시 위도 경도로 임시로 값을 넣음
        mWeatherUtil.getApiService().getCurrentUV(37.56,126.97).enqueue(new Callback<CurrentUV>() {
            @Override
            public void onResponse(Call<CurrentUV> call, Response<CurrentUV> response) {
                mUvTextView.setText("자외선 : " + response.body().getValue());
            }

            @Override
            public void onFailure(Call<CurrentUV> call, Throwable t) {

            }
        });
    }

    // 기온별로 옷차림 메세지 등 변경...
    // 이미지 없음.
    private void setWeatherCody() {
        if (mTemp <= 5) { // 온도가 5도 이하
            mCodyImageView.setImageResource(R.drawable.cody5);
        } else if (5 < mTemp && mTemp <= 9) {
            mCodyImageView.setImageResource(R.drawable.cody9);
        } else if (9 < mTemp && mTemp <= 11) {
            mCodyImageView.setImageResource(R.drawable.cody11);
        } else if (11 < mTemp && mTemp <= 16) {
            mCodyImageView.setImageResource(R.drawable.cody16);
        } else if (16 < mTemp && mTemp <= 19) {
            mCodyImageView.setImageResource(R.drawable.cody19);
        } else if (19 < mTemp && mTemp <= 22) {
            mCodyImageView.setImageResource(R.drawable.cody22);
        } else if (22 < mTemp && mTemp < 27) {
            mCodyImageView.setImageResource(R.drawable.cody26);
        } else {
            mCodyImageView.setImageResource(R.drawable.cody27);
        }
    }

}
