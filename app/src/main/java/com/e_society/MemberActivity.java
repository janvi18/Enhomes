package com.e_society;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.e_society.display.MemberDisplayActivity;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MemberActivity extends AppCompatActivity {
    EditText edtHouseId, edtMemberName, edtAge, edtContactNo;
    TextView tv_dateOfBirth, tvGender;

    ImageButton btnDate;
    Button addMember;

    RadioGroup radioGroup;

    private int date;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        edtHouseId = findViewById(R.id.edt_houseId);
        edtMemberName = findViewById(R.id.edt_memberName);
        edtAge = findViewById(R.id.edt_age);

        tv_dateOfBirth = findViewById(R.id.tv_dateOfBirth);
        tvGender = findViewById(R.id.tv_gender);
        edtContactNo = findViewById(R.id.edt_contactNo);
        addMember = findViewById(R.id.btn_member);
        btnDate = findViewById(R.id.btn_memberDate);


        Calendar calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);


        //date
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MemberActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tv_dateOfBirth.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, date, month, year);
                datePickerDialog.show();

            }
        });

        //add button
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String houseId = edtHouseId.getText().toString();
                String memberName = edtMemberName.getText().toString();
                String age = edtAge.getText().toString();
                String contactNumber = edtContactNo.getText().toString();
                String dateOfBirth = tv_dateOfBirth.getText().toString();

//                int id = radioGroup.getCheckedRadioButtonId();
//                RadioButton radioButton = findViewById(id);
//                String strRadioButton = radioButton.getText().toString();


//                Intent intent = new Intent(memberLayout.this,MemberShowActivity.class);
//
//                intent.putExtra("houseId",houseId);
//                intent.putExtra("memberName",memberName);
//                intent.putExtra("age",age);
//               // intent.putExtra("gender",gn);
//                intent.putExtra("contactNo.",contactNumber);
//                intent.putExtra("dateOfBirth",dateOfBirth);

                apiCall(houseId, memberName, age, contactNumber, dateOfBirth);


            }
        });
    }


    private void apiCall(String strHouseId, String strMemberName, String strAge, String strContactNumber, String strDateOfBirt) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.MEMBER_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);

                Intent intent = new Intent(MemberActivity.this, MemberDisplayActivity.class);

                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("houseId", strHouseId);
                hashMap.put("memberName", strMemberName);
                hashMap.put("age", strAge);
                hashMap.put("contactNo", strContactNumber);
                hashMap.put("dateOfBirth", strDateOfBirt);
              //  hashMap.put("gender", strRadioButton);

                return hashMap;

            }
        };
        VolleySingleton.getInstance(MemberActivity.this).addToRequestQueue(stringRequest);

    }


}