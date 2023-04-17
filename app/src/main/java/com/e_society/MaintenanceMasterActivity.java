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
import com.e_society.display.MaintenanceDisplayActivity;
import com.e_society.display.MaintenanceMasterDisplayActivity;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class MaintenanceMasterActivity extends AppCompatActivity {

    EditText edtAmount,edtPenalty;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_master);

        edtAmount=findViewById(R.id.edt_maintenanceAmount);
        edtPenalty=findViewById(R.id.edt_penalty);

        btnAdd=findViewById(R.id.btn_addMaster);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strAmount=edtAmount.getText().toString();
                String strPenalty=edtPenalty.getText().toString();

                if (strAmount.length() == 0) {
                    edtAmount.requestFocus();
                    edtAmount.setError("FIELD CANNOT BE EMPTY");
                } else if (!strAmount.matches("^[0-9]+$")) {
                    edtAmount.requestFocus();
                    edtAmount.setError("PLEASE ENTER DIGITS ONLY");
                }else if (strPenalty.length() == 0) {
                    edtPenalty.requestFocus();
                    edtPenalty.setError("FIELD CANNOT BE EMPTY");
                } else if (!strPenalty.matches("^[0-9]+$")) {
                    edtPenalty.requestFocus();
                    edtPenalty.setError("PLEASE ENTER DIGITS ONLY");
                }
                else {
                    addMasterApi(strAmount, strPenalty);
                }
            }
        });



    }

    private void addMasterApi(String strAmount, String strPenalty) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Utils.MAINTENANCE_MASTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                Intent intent=new Intent(MaintenanceMasterActivity.this, MaintenanceMasterDisplayActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", String.valueOf(error));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("maintenanceAmount", strAmount);
                map.put("penalty", strPenalty);
                return map;
            }

        };
        VolleySingleton.getInstance(MaintenanceMasterActivity.this).addToRequestQueue(stringRequest);
    }
}