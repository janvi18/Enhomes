package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv_email = findViewById(R.id.tv_email);

        Intent i = getIntent();
        String strEmail = i.getStringExtra("KEY_EMAIL");

        tv_email.setText("Email : "+ strEmail);
    }
}