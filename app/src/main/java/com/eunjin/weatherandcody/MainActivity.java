package com.eunjin.weatherandcody;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            @Override // 현재 날씨 불러오기 성공
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.body() != null) {
                    String url = "http://openweathermap.org/img/w/"
                            + response.body().getWeather().get(0).getIcon() + ".png";
                    Glide.with(MainActivity.this).load(url).into(mWeatherImageView);
                    mWeatherTextView.setText("날씨 : " + response.body().getWeather().get(0).getDescription());

                    mTemp = response.body().getMain().getTemp();
                    mTempTextView.setText("기온 : " + mTemp);
                    setWeatherCody(mTemp);

                    // 현재 자외선 지수를 가져온다.
                    mWeatherUtil.getApiService().getCurrentUV(response.body().getCoord().getLat(),
                            response.body().getCoord().getLon()).enqueue(new Callback<CurrentUV>() {
                        @Override // 현재 자외선 지수 불러오기 성공
                        public void onResponse(Call<CurrentUV> call, Response<CurrentUV> response) {
                            mUvTextView.setText("자외선 : " + response.body().getValue());
                        }

                        @Override // 현재 자외선 지수 불러오기 실패
                        public void onFailure(Call<CurrentUV> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "에러", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, "도시이름을 다시 확인해주세요~", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override// 현재 날씨 불러오기 실패
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "에러", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 기온별로 옷차림 메세지 등 변경...
    // 이미지 없음.
    private void setWeatherCody(double temp) {
        if (temp <= 5) { // 온도가 5도 이하
            mCodyImageView.setImageResource(R.drawable.cody5);
        } else if (5.0 < temp && temp <= 9.0) {
            mCodyImageView.setImageResource(R.drawable.cody6);
        } else if (9.0 < temp && temp <= 11.0) {
            mCodyImageView.setImageResource(R.drawable.cody10);
        } else if (11.0 < temp && temp <= 16.0) {
            mCodyImageView.setImageResource(R.drawable.cody12);
        } else if (16.0 < temp && temp <= 19.0) {
            mCodyImageView.setImageResource(R.drawable.cody17);
        } else if (19.0 < temp && temp <= 22.0) {
            mCodyImageView.setImageResource(R.drawable.cody20);
        } else if (22.0 < temp && temp < 27.0) {
            mCodyImageView.setImageResource(R.drawable.cody23);
        } else {
            mCodyImageView.setImageResource(R.drawable.cody27);
        }
    }

    // 기온별 코멘트

    //private void

}


