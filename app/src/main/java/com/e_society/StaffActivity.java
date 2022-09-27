package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StaffActivity extends AppCompatActivity {

    EditText edtName,edtType,edtEntryTime,edtExitTime,edtPaid,edtContact,edtAddress,edtAgencyName,edtAgencyContact;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        edtName = findViewById(R.id.edt_smname);
        edtType = findViewById(R.id.edt_type);
        edtEntryTime = findViewById(R.id.edt_entryTime);
        edtExitTime = findViewById(R.id.edt_exitTime);
        edtPaid = findViewById(R.id.edt_paid);
        edtContact = findViewById(R.id.edt_contact);
        edtAddress = findViewById(R.id.edt_address);
        edtAgencyName = findViewById(R.id.edt_agencyName);
        edtAgencyContact = findViewById(R.id.edt_agencyContact);
        btnAdd = findViewById(R.id.btn_addStaff);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StaffActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
    }
}