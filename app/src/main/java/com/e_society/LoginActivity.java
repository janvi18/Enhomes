package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edt_email, edt_password;
    Button btn_login;
    TextView tvSignup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        tvSignup = findViewById(R.id.tv_signup);

        btn_login = findViewById(R.id.btn_login);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edt_email.getText().toString();
                String strPassword = edt_password.getText().toString();

                if (strEmail.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                } else if (!strEmail.matches(emailPattern)) {
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();
                } else if (strPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                } else if (strPassword.length() < 2) {
                    Toast.makeText(LoginActivity.this, "PLease Enter Your Valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Email is " + strEmail, Toast.LENGTH_LONG).show();

                    Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
                    i.putExtra("KEY_EMAIL", strEmail);
                    startActivity(i);
                }

            }
        });
    }
}
