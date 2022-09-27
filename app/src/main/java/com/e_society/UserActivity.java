package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import com.e_society.utils.VolleySingleton;
import com.e_society.utils.Utils;

public class UserActivity extends AppCompatActivity {

    EditText edtRoleId, edtFirstName, edtLastName, edtDateOfBirth, edtAge, edtGender, edtContactNo, edtEmail, edtPassword;
    Button btnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnUser = findViewById(R.id.btn_addUser);
        edtRoleId = findViewById(R.id.edt_roleId);
        edtFirstName = findViewById(R.id.edt_firstName);
        edtLastName = findViewById(R.id.edt_lastName);
        edtDateOfBirth = findViewById(R.id.edt_dob);
        edtAge = findViewById(R.id.edt_age);
        edtGender = findViewById(R.id.edt_gender);
        edtContactNo = findViewById(R.id.edt_contactNo);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRoleId = edtRoleId.getText().toString();
                String strFirstName = edtFirstName.getText().toString();
                String strLastName = edtLastName.getText().toString();
                String strDateOfBirth = edtDateOfBirth.getText().toString();
                String strAge = edtAge.getText().toString();
                String strGender = edtGender.getText().toString();
                String strContactNo = edtContactNo.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.ROLE_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent i = new Intent(UserActivity.this, DisplayActivity.class);
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> hashMap = new HashMap<>();
                        hashMap.put("role_id", strRoleId);
                        hashMap.put("first_name", strFirstName);
                        hashMap.put("last_name", strLastName);
                        hashMap.put("date_of_birth", strDateOfBirth);
                        hashMap.put("age", strAge);
                        hashMap.put("gender", strGender);
                        hashMap.put("contact_no", strContactNo);
                        hashMap.put("email", strEmail);
                        hashMap.put("password", strPassword);

                        return hashMap;
                    }
                };
                VolleySingleton.getInstance(UserActivity.this).addToRequestQueue(stringRequest);
            }
        });
    }
}