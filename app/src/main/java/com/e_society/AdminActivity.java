package com.e_society;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.e_society.adapter.MyRoleAdapter;
import com.e_society.display.AdminDisplayActivity;
import com.e_society.display.HouseDisplayActivity;
import com.e_society.display.RoleDisplayActivity;
import com.e_society.model.AdminLangModel;
import com.e_society.model.RoleLangModel;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    TextView tvRoleId;
    Spinner spinnerRole;
    String strSelectedRole, roleId;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button btnAddAdmin, btnDeleteAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        edtEmail = findViewById(R.id.edt_admin_email);
        edtPassword = findViewById(R.id.edt_admin_password);
        tvRoleId = findViewById(R.id.tv_roleId);
        spinnerRole = findViewById(R.id.spinner_roleId);
        btnAddAdmin = findViewById(R.id.btn_add_admin);
        btnDeleteAdmin = findViewById(R.id.btn_delete_admin);
        RoleApi();
        btnAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                if (strEmail.equals("")) {
                    Toast.makeText(AdminActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                } else if (!strEmail.matches(emailPattern)) {
                    Toast.makeText(AdminActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();
                } else if (strPassword.isEmpty()) {
                    Toast.makeText(AdminActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();
                    Log.e(strEmail+strPassword+roleId,"data");
                    addAdminApi(strEmail, strPassword, roleId);
                }

            }
        });
    }

    private void RoleApi() {
        ArrayList<RoleLangModel> arrayList = new ArrayList<RoleLangModel>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Utils.ROLE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    int j = 1;
                    String strRole[] = new String[jsonArray.length() + 1];
                    strRole[0] = "Select Your Role";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strRoleId = jsonObject1.getString("_id");
                        String strRoleName = jsonObject1.getString("roleName");

                        strRole[j] = strRoleName;
                        j++;

                    }
                    ArrayAdapter<String> arrayAdapter = new
                            ArrayAdapter<String>(AdminActivity.this, android.R.layout.simple_list_item_1, strRole) {
                                @Override
                                public View getDropDownView(int position, @Nullable View convertView,
                                                            @NonNull ViewGroup parent) {

                                    TextView tvData = (TextView) super.getDropDownView(position, convertView, parent);
                                    tvData.setTextColor(Color.BLACK);
                                    tvData.setTextSize(20);
                                    return tvData;
                                }

                            };
                    spinnerRole.setAdapter(arrayAdapter);

                    spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strSelectedRole = strRole[position];
                            Log.e("selected user", strSelectedRole);
                            getRoleId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(AdminActivity.this).addToRequestQueue(stringRequest);
    }

    private void getRoleId() {

        ArrayList<RoleLangModel> arrayList = new ArrayList<RoleLangModel>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Utils.ROLE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strRoleId = jsonObject1.getString("_id");
                        String strRoleName = jsonObject1.getString("roleName");

                        if (strRoleName.equals(strSelectedRole)) {
                            roleId = strRoleId;
                        }

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

        VolleySingleton.getInstance(AdminActivity.this).addToRequestQueue(stringRequest);
    }

    private void addAdminApi(String strEmail, String strPassword, String strRole) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.ADMIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("House Response ===", "onResponse: " + response);
                Intent i = new Intent(AdminActivity.this, AdminDisplayActivity.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                Log.e(strEmail+strPassword+strRole,"map");
                map.put("email", strEmail);
                map.put("password", strPassword);
                map.put("role", strRole);

                return map;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
