package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ComplaintActivity extends AppCompatActivity {

    EditText edt_userId, edt_CHouseId, edt_date, edt_complaint;
    Button btn_complaint;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        edt_userId = findViewById(R.id.edt_userId);
        edt_CHouseId = findViewById(R.id.edt_complaintHouseId);
        edt_date = findViewById(R.id.edt_date);
        btn_complaint = findViewById(R.id.btn_complaint);

        btn_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserId = edt_userId.getText().toString();
                String strCHouseId = edt_CHouseId.getText().toString();
                String strDate = edt_date.getText().toString();
                String strComplaint = edt_complaint.getText().toString();

                complaintApi(strUserId,strCHouseId,strDate,strComplaint);
            }

            private void complaintApi(String strUserId, String strCHouseId, String strDate, String strComplaint) {
            }

        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ComplaintActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edt_date.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
    }
}