package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.os.Handler;

import com.e_society.display.EventDisplayActivity;
import com.e_society.display.MaintenanceDisplayActivity;
import com.e_society.display.PlaceDisplayActivity;
import com.e_society.display.UserDisplayActivity;

public class MainActivity extends AppCompatActivity {

    int time = 3000;
    GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gifImageView = findViewById(R.id.gif);
        gifImageView.setGifImageResource(R.drawable.enhomes);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(MainActivity.this, NonMemberActivity.class);
                startActivity(i);
                finish();
            }
        }, time);
    }
}