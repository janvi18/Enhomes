package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SuggestionActivity extends AppCompatActivity {

    EditText edt_mname,edt_suggestion,edt_reason,edt_acknowledgement;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        edt_mname = findViewById(R.id.edt_mname);
        edt_suggestion = findViewById(R.id.edt_suggestions);
        edt_reason = findViewById(R.id.edt_reason);
        edt_acknowledgement = findViewById(R.id.edt_acknowledgement);

        btn_add = findViewById(R.id.btn_addSuggestion);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SuggestionActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
    }
}