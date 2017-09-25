package com.eunjin.weatherandcody.retrofit;


import com.eunjin.weatherandcody.model.current.CurrentWeather;
import com.eunjin.weatherandcody.model.uv.CurrentUV;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jinx9 on 2017-09-20.
 */

public interface WeatherApi {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    String APP_ID = "3316b092a59a6858f30444012e97edc9";

    @GET("weather?lang=kr&units=metric&appid=" + APP_ID)
    Call<CurrentWeather> getCurrentWeather(@Query("q") String cityName);

    @GET("uvi?&appid=" + APP_ID)
    Call<CurrentUV> getCurrentUV (@Query("lat") double lat, @Query("lon") double lon);
}
