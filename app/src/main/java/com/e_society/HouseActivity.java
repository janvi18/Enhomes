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
import com.e_society.display.HouseDisplayActivity;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;
//import com.e_society.display.HouseDisplayActivity;

import java.util.HashMap;
import java.util.Map;

public class HouseActivity extends AppCompatActivity {

    EditText edtDeets;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        edtDeets = findViewById(R.id.edt_House_Deets);
        btnAdd = findViewById(R.id.btn_addHouse);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHouseDeets = edtDeets.getText().toString();
                houseApi(strHouseDeets);
            }
        });
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

