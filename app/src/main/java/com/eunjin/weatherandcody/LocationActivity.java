package com.eunjin.weatherandcody;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.cityname_edit_view)
    EditText citynameEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
    }

    public void onClickedEnterButton(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("cityName",citynameEditView.getText().toString());
        startActivity(intent);
    }
}
