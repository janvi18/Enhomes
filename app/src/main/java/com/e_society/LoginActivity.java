package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.e_society.adapter.AdminListAdapter;
import com.e_society.adapter.StaffListAdapter;
import com.e_society.display.AdminDisplayActivity;
import com.e_society.display.StaffDisplayActivity;
import com.e_society.model.AdminLangModel;
import com.e_society.model.StaffLangModel;
import com.e_society.model.UserLangModel;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static String name;
    private static String strInputEmail, strInputPassword;
    EditText edt_email, edt_password;
    Button btn_login;
    TextView tvSignup, tvForgetPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    int check = 0;
    private ProgressBar progressBar;

    public static String getName() {
        Log.e(name, "Name in getName");
        return name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        tvSignup = findViewById(R.id.tv_signup);
        tvForgetPassword = findViewById(R.id.forget_pwd);

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btn_login = findViewById(R.id.btn_login);


        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strInputEmail = edt_email.getText().toString();
                strInputPassword = edt_password.getText().toString();


                if (strInputEmail.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                } else if (!strInputEmail.matches(emailPattern)) {
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();
                } else if (strInputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (strInputEmail.equals("admin@gmail.com") && strInputPassword.equals("Admin@123")) {
                        name = "admin";
                        loginApi("admin@gmail.com", "Admin@123");
                        Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
                        startActivity(i);
                    } else {
                        Log.e(strInputEmail, strInputPassword);
                        getUsersApi();
                    }
                }

            }
        });
    }

    private void getUsersApi() {
        ArrayList<UserLangModel> arrayList = new ArrayList<UserLangModel>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Utils.SIGNUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "Display--onResponse:" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strEmail = jsonObject1.getString("email");
                        String strPassword = jsonObject1.getString("password");

                        if ((strEmail.equals(strInputEmail)) && (strPassword.equals(strInputPassword))) {
                            Log.e("checking", "email and password");
                            name = "user";
                            loginApi(strInputEmail, strInputPassword);
                            check = 1;
                        }
                    }
                    if (check == 1) {
                        Intent intent = new Intent(LoginActivity.this, UserDashBoardActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login Done Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        getStaffApi();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", String.valueOf(error));
            }
        });

        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);

    }

    private void getStaffApi() {
        ArrayList<StaffLangModel> arrayList = new ArrayList<StaffLangModel>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Utils.STAFF_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("data: ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strEmail = jsonObject1.getString("email");
                        String strPassword = jsonObject1.getString("password");

                        if ((strEmail.equals(strInputEmail)) && (strPassword.equals(strInputPassword))) {
                            Log.e("checking", "email and password");
                            name = "security";
                            loginApi(strInputEmail, strInputPassword);
                            check = 1;
                        }
                    }
                    if (check == 1) {
                        Intent intent = new Intent(LoginActivity.this, SecurityDashboardActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login Done Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        AdminApi();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", String.valueOf(error));
            }
        });

        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);

    }

    private void AdminApi() {
        ArrayList<AdminLangModel> arrayList = new ArrayList<AdminLangModel>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Utils.ADMIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strAdminId = jsonObject1.getString("_id");
                        String strEmail = jsonObject1.getString("email");
                        String strPassword = jsonObject1.getString("password");

                        JSONObject jsonObject2 = jsonObject1.getJSONObject("role");
                        String roleId = jsonObject2.getString("_id");

                        if ((strEmail.equals(strInputEmail)) && (strPassword.equals(strInputPassword))) {
                            Log.e("checking", "email and password");
                            name = "admin";
                            loginApi(strInputEmail, strInputPassword);
                            check = 1;
                        }

                    }
                    if (check == 1) {
                        Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login Done Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        //call admin api
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Credentials .", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);

    }

    private void loginApi(String strEmail, String strPassword) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Login Response ===", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("email", strEmail);
                map.put("password", strPassword);

                return map;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}


