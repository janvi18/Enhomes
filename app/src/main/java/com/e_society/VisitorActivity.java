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

public class VisitorActivity extends AppCompatActivity {

    EditText edtVName, edtTime;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        edtVName = findViewById(R.id.edt_vname);
        edtTime = findViewById(R.id.edt_ATime);
        btnAdd = findViewById(R.id.btn_addVisitor);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strVName = edtVName.getText().toString();
                String strVTime = edtTime.getText().toString();
                visitorApi(strVName, strVTime);

            }

        });
    }

    private void visitorApi(String strVName, String strVTime) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.VISITOR_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Visitor Response ===", "onResponse: " + response);
                Intent i = new Intent(VisitorActivity.this, HomeActivity.class);
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
                map.put("visitorName", strVName);
                map.put("arrivingTime", strVTime);


                return map;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}


