package com.e_society.update;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.e_society.R;

public class PlaceUpdateActivity extends AppCompatActivity {

    EditText edtPlaceDeets;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        edtPlaceDeets = findViewById(R.id.edt_placeDetails);
        btnAdd = findViewById(R.id.btn_addPlace);

        Intent i = getIntent();



    }
}