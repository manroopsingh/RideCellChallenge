package com.example.singh.ridecellchallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.singh.ridecellchallenge.R;
import com.example.singh.ridecellchallenge.activities.getroute.GetRouteActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, GetRouteActivity.class);
        startActivity(intent);
        finish();

    }

}
