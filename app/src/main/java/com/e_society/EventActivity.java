package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.e_society.display.EventDisplayActivity;

import java.util.Calendar;

public class EventActivity extends AppCompatActivity {

    EditText edtEventDate, edtEndDate, edtEventDetails, edtRent;
    Button btnAddEvent;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        edtEventDate = findViewById(R.id.edt_eventDate);
        edtEndDate = findViewById(R.id.edt_eventEndDate);
        edtEventDetails = findViewById(R.id.edt_eventDetail);
        edtRent = findViewById(R.id.edt_rent);

        btnAddEvent = findViewById(R.id.btn_event);

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDate = edtEventDate.getText().toString();
                String strEndDate = edtEndDate.getText().toString();
                String strEventDetails = edtEventDetails.getText().toString();
                String strRent = edtRent.getText().toString();

                Intent intent = new Intent(EventActivity.this, EventDisplayActivity.class);

                intent.putExtra("eventDate", strDate);
                intent.putExtra("eventEndDate", strEndDate);
                intent.putExtra("eventDetails", strEventDetails);
                intent.putExtra("rent", strRent);

                startActivity(intent);
            }
        });


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edtEventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edtEventDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edtEndDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
    }
}





