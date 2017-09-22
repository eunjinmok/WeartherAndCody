package com.eunjin.weatherandcody;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView mWeatherImageView;
    private TextView mWeatherTextView;
    private TextView mTempTextView;
    private TextView mUvTextView;
    private TextView mDustTextView;
    private ImageView mCodyImageView;

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
    }
}
