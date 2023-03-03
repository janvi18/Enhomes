package com.e_society.update;

import android.app.TimePickerDialog;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.e_society.R;
import com.e_society.display.StaffDisplayActivity;
import com.e_society.model.StaffLangModel;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StaffUpdateAcivity extends AppCompatActivity {

    EditText edtStaffName, edtContact, edtAddress, edtAgencyName, edtAgencyContact;
    EditText edtEntryTime, edtExitTime;

    Button btnStaff, btnDelStaff;

    Spinner spinnerType;
    String strTypes[] = {"Select a Type", "SecurityGuard", "Sweeper", "PumpOperator", "Gardener"};


    ImageButton btnEntry, btnExit;
    TextView tvEntry, tvExit;
    private int hour;
    private int minute;
    private String strStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        Intent i = getIntent();

        edtStaffName = findViewById(R.id.et_name);
        edtContact = findViewById(R.id.et_contact);
        edtAddress = findViewById(R.id.et_add);
        edtAgencyName = findViewById(R.id.et_agencyName);
        edtAgencyContact = findViewById(R.id.et_agencyContact);
        edtEntryTime = findViewById(R.id.tv_entry);
        edtExitTime = findViewById(R.id.tv_exit);
        btnStaff = findViewById(R.id.btn_staff);
        btnDelStaff = findViewById(R.id.btn_del_staff);
        spinnerType = findViewById(R.id.spinner_type);

        btnDelStaff.setVisibility(View.VISIBLE);


        String staffId = i.getStringExtra("STAFF_ID");
        String strStaffName = i.getStringExtra("STAFF_NAME");
        String strContact = i.getStringExtra("CONTACT");
        String strAddress = i.getStringExtra("ADDRESS");
        String strAgencyName = i.getStringExtra("AGENCY_NAME");
        String strAgencyContact = i.getStringExtra("AGENCY_CONTACT");
        String strEntryTime = i.getStringExtra("ENTRY_TIME");
        String strExitTime = i.getStringExtra("EXIT_TIME");
//        String strType = i.getStringExtra("TYPE");

//        Log.e("type: ",strType);

        Log.e("Data: ", staffId + " " + strStaffName + " " + strContact + " " + strAddress + " " + strEntryTime + " " + strExitTime + " " + strAgencyName + " " + strAgencyContact);
        StaffLangModel staffLangModel = new StaffLangModel();
        edtStaffName.setText(strStaffName);
        edtContact.setText(strContact);
        edtAddress.setText(strAddress);
        edtAgencyName.setText(strAgencyName);
        edtAgencyContact.setText(strAgencyContact);
        edtEntryTime.setText(strEntryTime);
        edtExitTime.setText(strExitTime);


//        time
        tvEntry = findViewById(R.id.tv_entry);
        tvExit = findViewById(R.id.tv_exit);
        btnEntry = findViewById(R.id.btn_entry);
        btnExit = findViewById(R.id.btn_exit);


        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        //entry time
        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(StaffUpdateAcivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvEntry.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);

                timePickerDialog.show();
            }
        });

        //exit time
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(StaffUpdateAcivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        tvExit.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);

                timePickerDialog.show();
            }
        });

        //spinner
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strTypes) {
                    @Override
                    public View getDropDownView(int position, @Nullable View convertView,
                                                @NonNull ViewGroup parent) {

                        TextView tvData1 = (TextView) super.getDropDownView(position, convertView, parent);
                        tvData1.setTextColor(Color.WHITE);
                        tvData1.setTextSize(20);
                        return tvData1;

                    }

                };
        spinnerType.setAdapter(arrayAdapter);
        Log.e("Selected Id: ", String.valueOf(spinnerType.getSelectedItemId()));
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strStaff = strTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnDelStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAPI(staffId);
            }
        });

        btnStaff.setText("Update Staff");
        btnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStaffName = edtStaffName.getText().toString();
                String strContact = edtContact.getText().toString();
                String strAddress = edtAddress.getText().toString();
                String strAgencyName = edtAgencyName.getText().toString();
                String strAgencyContact = edtAgencyContact.getText().toString();
                String strEntryTime = edtEntryTime.getText().toString();
                String strExitTime = edtExitTime.getText().toString();

                if (strStaffName.length() == 0) {
                    edtStaffName.requestFocus();
                    edtStaffName.setError("FIELD CANNOT BE EMPTY");
                } else if (!strStaffName.matches("[a-zA-Z ]+")) {
                    edtStaffName.requestFocus();
                    edtStaffName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                } else if (strEntryTime.length() == 0) {
                    tvEntry.requestFocus();
                    tvEntry.setError("FIELD CANNOT BE EMPTY");
                } else if (strExitTime.length() == 0) {
                    tvExit.requestFocus();
                    tvExit.setError("FIELD CANNOT BE EMPTY");
                } else if (strContact.length() == 0) {
                    edtContact.requestFocus();
                    edtContact.setError("FIELD CANNOT BE EMPTY");
                } else if (!strContact.matches("^[0-9]{10}$")) {
                    edtContact.requestFocus();
                    edtContact.setError("PLEASE ENTER 10 DIGITS ONLY");
                } else if (strAddress.length() == 0) {
                    edtAddress.requestFocus();
                    edtAddress.setError("FIELD CANNOT BE EMPTY");
                } else if (!strAddress.matches("[a-zA-Z ]+")) {
                    edtAddress.requestFocus();
                    edtAddress.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                } else if (strAgencyName.length() == 0) {
                    edtAgencyName.requestFocus();
                    edtAgencyName.setError("FIELD CANNOT BE EMPTY");
                } else if (!strAgencyName.matches("[a-zA-Z ]+")) {
                    edtAgencyName.requestFocus();
                    edtAgencyName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                } else if (strAgencyContact.length() == 0) {
                    edtAgencyContact.requestFocus();
                    edtAgencyContact.setError("FIELD CANNOT BE EMPTY");
                } else if (!strAgencyContact.matches("^[0-9]{10}$")) {
                    edtAgencyContact.requestFocus();
                    edtAgencyContact.setError("PLEASE ENTER 10 DIGITS ONLY");
                } else {
                    Toast.makeText(StaffUpdateAcivity.this, "Validation Successful", Toast.LENGTH_LONG).show();
                    Log.e("Data", staffId + " " + strStaffName + " " + strContact + " " + strAddress + " " + strAgencyName + " " + strAgencyContact + " " + strEntryTime + " " + strExitTime);
                    apicall(staffId, strStaffName, strContact, strAddress, strAgencyName, strAgencyContact, strEntryTime, strExitTime);
                }
            }
        });


    }

    private void deleteAPI(String staffId) {
        Log.e("TAG****", "deleteAPI  " + staffId);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, Utils.STAFF_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(StaffUpdateAcivity.this, StaffDisplayActivity.class);
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
                hashMap.put("_id", staffId);
                return hashMap;


            }
        };
        VolleySingleton.getInstance(StaffUpdateAcivity.this).addToRequestQueue(stringRequest);


    }

    private void apicall(String staffId, String strStaffName, String strContact, String strAddress, String strAgencyName, String strAgencyContact, String strEntryTime, String strExitTime) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, Utils.STAFF_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(StaffUpdateAcivity.this, StaffDisplayActivity.class);
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
                hashMap.put("staffId", staffId);
                hashMap.put("staffMemberName", strStaffName);
                hashMap.put("contactNo", strContact);
                hashMap.put("address", strAddress);
                hashMap.put("entryTime", strEntryTime);
                hashMap.put("exitTime", strExitTime);
                hashMap.put("agencyName", strAgencyName);
                hashMap.put("agencyContactNumber", strAgencyContact);
                return hashMap;


            }
        };
        VolleySingleton.getInstance(StaffUpdateAcivity.this).addToRequestQueue(stringRequest);
    }
}