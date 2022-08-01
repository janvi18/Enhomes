package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText edt_username, edt_email, edt_password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView tvLogin;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edt_username = findViewById(R.id.username);
        edt_email = findViewById(R.id.email);
        edt_password = findViewById(R.id.password);
        tvLogin = findViewById(R.id.tv_login);
        btn_signup = findViewById(R.id.btn_signup);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserName = edt_username.getText().toString();
                String strEmail = edt_email.getText().toString();
                String strPassword = edt_password.getText().toString();
                ;

                if (strUserName.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter UserName", Toast.LENGTH_SHORT).show();
                } else if (strUserName.length() < 2) {
                    Toast.makeText(SignupActivity.this, "Please Enter Valid Name", Toast.LENGTH_SHORT).show();
                } else if (strEmail.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                } else if (!strEmail.matches(emailPattern)) {
                    Toast.makeText(SignupActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();
                } else if (strPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                } else if (strPassword.length() < 2) {
                    Toast.makeText(SignupActivity.this, "PLease Enter Your Valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Name is " + strUserName, Toast.LENGTH_LONG).show();

                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(i);
                }

            }
        });
    }
}
