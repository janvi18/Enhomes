package com.e_society.update;

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
import com.e_society.R;
import com.e_society.display.HouseDisplayActivity;
import com.e_society.display.MaintenanceDisplayActivity;
import com.e_society.model.HouseLangModel;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class HouseUpdateActivity extends AppCompatActivity {

    EditText edtDeets;
    Button btnAddHouse, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        Intent i = getIntent();

        edtDeets = findViewById(R.id.edt_House_Deets);
        btnAddHouse = findViewById(R.id.btn_addHouse);
        btnDelete = findViewById(R.id.btn_delete_House);

        String strHouseDeets = i.getStringExtra("HOUSE_DEETS");
        String HouseId = i.getStringExtra("HOUSE_ID");


        HouseLangModel houseLangModel = new HouseLangModel();
        edtDeets.setText(strHouseDeets);
        Log.e(strHouseDeets, strHouseDeets);
        btnAddHouse.setText("Update House");
        btnDelete.setVisibility(View.VISIBLE);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAPI(HouseId);
            }
        });


        btnAddHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHouseDeets = edtDeets.getText().toString();
                apiCall(HouseId, strHouseDeets);

            }
        });

    }

    private void deleteAPI(String houseId) {
        Log.e("TAG****", "deleteAPI Update " + houseId);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, Utils.HOUSE_URL + "/" + houseId, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(HouseUpdateActivity.this, HouseDisplayActivity.class);
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
                hashMap.put("houseId", houseId);
                return hashMap;


            }
        };
        VolleySingleton.getInstance(HouseUpdateActivity.this).addToRequestQueue(stringRequest);


    }


    private void apiCall(String houseId, String strHouseDeets) {


        StringRequest stringRequest = new StringRequest(Request.Method.PUT, Utils.HOUSE_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", houseId + strHouseDeets);
                Intent intent = new Intent(HouseUpdateActivity.this, HouseDisplayActivity.class);
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
                hashMap.put("houseId", houseId);
                hashMap.put("houseDetails", strHouseDeets);

                return hashMap;

            }
        };
        VolleySingleton.getInstance(HouseUpdateActivity.this).addToRequestQueue(stringRequest);

    }
}