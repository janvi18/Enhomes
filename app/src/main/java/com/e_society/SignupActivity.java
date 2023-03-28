package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText edt_fname, edt_lname, edt_age, edt_cn, edt_email, edt_password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView tvLogin, tv_forgetpwd;
    RadioGroup radioGender;
    Button btn_signup;

    TextView tvDob;
    ImageButton btnDob;
    private int date;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edt_fname = findViewById(R.id.fname);
        edt_lname = findViewById(R.id.lname);
        tvDob = findViewById(R.id.tv_dob);
        edt_age = findViewById(R.id.age);
        radioGender = findViewById(R.id.radiogroup);
        edt_cn = findViewById(R.id.cn);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.password);
        tv_forgetpwd = findViewById(R.id.forget_pwd);
        tvLogin = findViewById(R.id.tv_login);

        btnDob = findViewById(R.id.btn_dob);
        Calendar calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);


        btn_signup = findViewById(R.id.btn_signup);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFname = edt_fname.getText().toString();
                String strLname = edt_lname.getText().toString();
                String strDob = tvDob.getText().toString();
                String strAge = edt_age.getText().toString();

                int id = radioGender.getCheckedRadioButtonId();
                RadioButton rd = findViewById(id);
                String strRadioButton = rd.getText().toString();


                String strCN = edt_cn.getText().toString();
                String strEmail = edt_email.getText().toString();
                String strPassword = edt_password.getText().toString();


                if (strFname.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (strLname.length() < 2) {
                    Toast.makeText(SignupActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (strDob.length() < 2) {
                    Toast.makeText(SignupActivity.this, "Please Enter Date Of Birth", Toast.LENGTH_SHORT).show();
                } else if (strAge.length() < 2) {
                    Toast.makeText(SignupActivity.this, "Please Enter Age", Toast.LENGTH_SHORT).show();
                } else if (strCN.length() < 2) {
                    Toast.makeText(SignupActivity.this, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                } else if (strEmail.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                } else if (!strEmail.matches(emailPattern)) {
                    Toast.makeText(SignupActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();
                } else if (strPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                } else if (strPassword.length() < 2) {
                    Toast.makeText(SignupActivity.this, "PLease Enter Your Valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Name is " + strFname, Toast.LENGTH_LONG).show();

                    loadData(strFname, strLname, strDob, strAge, strCN, strEmail, strPassword, strRadioButton);
                }
            }
        });
        btnDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        Log.e("year: ", String.valueOf(year));
                        Log.e("month: ", String.valueOf(month));
                        Log.e("day: ", String.valueOf(dayOfMonth));

                        long dtDob = chosenDate.toMillis(true);

                        strDate = DateFormat.format("yyyy/MM/dd", dtDob);

                        tvDob.setText(strDate);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

    }


    private void loadData(String strFname, String strLname, String strDob, String strAge, String strCn, String strEmail, String strPassword, String strRadioButton) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.SIGNUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Signup Response ===", "onResponse: " + response);
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("firstName", strFname);
                map.put("lastName", strLname);
                map.put("dateOfBirth", strDob);
                map.put("age", strAge);
                map.put("gender", strRadioButton);
                map.put("contactNo", strCn);
                map.put("email", strEmail);
                map.put("password", strPassword);

                Log.e("Signup Response ===", "onResponse: " + map);

                return map;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}