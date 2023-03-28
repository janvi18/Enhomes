package com.e_society.update;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.e_society.R;
import com.e_society.display.EventDisplayActivity;
import com.e_society.display.NonMemberDisplayActivity;
import com.e_society.model.EventLangModel;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class NonMemberUpdateActivity extends AppCompatActivity {


    EditText edtHouseId, edtVisitorName;
    TextView tvArrivingTime;
    ImageButton imgBtnArrivingTime;
    RadioGroup radioGroup, pickup_radioGroup, status_RadioGroup;
    Button btnAddNonMember, btnDeleteNonMember;
    private int hour;
    private int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_member);

        Intent i = getIntent();

        edtHouseId = findViewById(R.id.edt_houseId);
        edtVisitorName = findViewById(R.id.edt_visitorName);
        tvArrivingTime = findViewById(R.id.tv_arrivingTime);
        imgBtnArrivingTime = findViewById(R.id.btn_arrivingTime);
        radioGroup = findViewById(R.id.nonMemberRadioGroup);
        pickup_radioGroup = findViewById(R.id.pickup_radiogroup);
        status_RadioGroup = findViewById(R.id.status_radiogroup);
        btnDeleteNonMember = findViewById(R.id.btn_delete_non_member);

        btnAddNonMember = findViewById(R.id.btn_nonMember);

        String strNonMemberId = i.getStringExtra("ID");
        String strHouseId = i.getStringExtra("HOUSE_ID");
        String strName = i.getStringExtra("NAME");
        String strTime = i.getStringExtra("ARRIVING_TIME");
        String strVisited = i.getStringExtra("VISITED");
        String strPickUp = i.getStringExtra("PICKUP");
        String strStatus = i.getStringExtra("STATUS");

        int id = radioGroup.getCheckedRadioButtonId();
        int id1 = radioGroup.getCheckedRadioButtonId();
        int id2 = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(id);
        RadioButton pRadioButton = findViewById(id1);
        RadioButton sRadioButton = findViewById(id2);
//        String strRadioButton = radioButton.getText().toString();
//        String strPRadioButton = radioButton.getText().toString();
//        String strSRadioButton = radioButton.getText().toString();

        //set text
        EventLangModel eventLangModel = new EventLangModel();

        edtHouseId.setText(strHouseId);
        edtVisitorName.setText(strName);

        btnAddNonMember.setText("Update NonMember");
        btnAddNonMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strHouseId = edtHouseId.getText().toString();
                String strName = edtVisitorName.getText().toString();
//                String strradio = radioButton.getText().toString();
//                String strpradio = pRadioButton.getText().toString();
//                String strsradio = sRadioButton.getText().toString();


                apiCall(strNonMemberId, strHouseId, strName);

            }
        });

        btnDeleteNonMember.setVisibility(View.VISIBLE);
        btnDeleteNonMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAPI(strNonMemberId);
            }
        });


    }

    private void apiCall(String strNonMemberId, String strHouseId, String strName) {

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, Utils.EVENT_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(NonMemberUpdateActivity.this, NonMemberDisplayActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", String.valueOf(error));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                Log.e("id in update map:", strNonMemberId);
                hashMap.put("nonMemberId", strNonMemberId);
                hashMap.put("name", strName);
               // hashMap.put("isVisited", strRadioButton);
//                hashMap.put("pickup", strPRadioButton);
//                hashMap.put("status", strSRadioButton);

                return hashMap;


            }
        };
        VolleySingleton.getInstance(NonMemberUpdateActivity.this).addToRequestQueue(stringRequest);

    }

    private void deleteAPI(String id) {

        Log.e("TAG****", "deleteAPI Update " + id);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, Utils.NONMEMBER_URL + "/" + id, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(NonMemberUpdateActivity.this, NonMemberDisplayActivity.class);
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
                hashMap.put("nonMemberId", id);
                return hashMap;

            }
        };
        VolleySingleton.getInstance(NonMemberUpdateActivity.this).addToRequestQueue(stringRequest);


    }
}
