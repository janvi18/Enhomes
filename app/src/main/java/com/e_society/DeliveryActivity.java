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
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class DeliveryActivity extends AppCompatActivity {

    EditText edtType,edtStatus;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        edtType = findViewById(R.id.edt_ctype);
        edtStatus = findViewById(R.id.edt_status);
        btnAdd = findViewById(R.id.btn_addDelivery);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strType = edtType.getText().toString();
                String strStatus = edtStatus.getText().toString();
                deliveryApi(strType, strStatus);

            }

        });
    }

    private void deliveryApi(String strType, String strStatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.DELIVERY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Delivery Response ===", "onResponse: " + response);
                Intent i = new Intent(DeliveryActivity.this, HomeActivity.class);
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
                map.put("couriertype", strType);
                map.put("status", strStatus);


                return map;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}


