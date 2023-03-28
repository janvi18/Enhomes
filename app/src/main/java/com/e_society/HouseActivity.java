package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.e_society.adapter.UserListAdapter;
import com.e_society.display.HouseDisplayActivity;
import com.e_society.display.UserDisplayActivity;
import com.e_society.model.UserLangModel;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;
//import com.e_society.display.HouseDisplayActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HouseActivity extends AppCompatActivity {

    EditText edtDeets;
    Button btnAdd;
    String strUsers[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        edtDeets = findViewById(R.id.edt_House_Deets);
        btnAdd = findViewById(R.id.btn_addHouse);



        DisplayUserApi();

        Log.e("users", String.valueOf(strUsers));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHouseDeets = edtDeets.getText().toString();
                houseApi(strHouseDeets);
            }
        });
    }

    private void DisplayUserApi() {
        ArrayList<UserLangModel> arrayList = new ArrayList<UserLangModel>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Utils.SIGNUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "Display--onResponse:" + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Log.e("i", String.valueOf(i));
                        int j=0;

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject role = jsonObject1.getJSONObject("role");
                        String roleId = role.getString("_id");
//                        Log.e(":inside","for loop");
                        String strUserId = jsonObject1.getString("_id");
                        strUsers[j]=strUserId;
                        Log.e("User: ",strUsers[j]);
                        j++;
                        String strFirstName = jsonObject1.getString("firstName");
                        String strLastName = jsonObject1.getString("lastName");
                        String strDateOfBirth = jsonObject1.getString("dateOfBirth");
                        String strAge = jsonObject1.getString("age");
                        String strGender = jsonObject1.getString("gender");
                        String strContactNo = jsonObject1.getString("contactNo");
                        String strEmail = jsonObject1.getString("email");
                        String strPassword = jsonObject1.getString("password");



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

        VolleySingleton.getInstance(HouseActivity.this).addToRequestQueue(stringRequest);

    }
    private void houseApi(String strHouseDeets) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.HOUSE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("House Response ===", "onResponse: " + response);
                Intent i = new Intent(HouseActivity.this, HouseDisplayActivity.class);
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
                map.put("houseDetails", strHouseDeets);

                return map;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}

