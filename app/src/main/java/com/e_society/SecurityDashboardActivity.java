package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.e_society.adapter.MyListAdapter;
import com.e_society.display.NonMemberDisplayActivity;
import com.e_society.display.StaffDisplayActivity;
import com.e_society.model.LangModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SecurityDashboardActivity extends AppCompatActivity {
    String name;
    GridView gridView;
    FloatingActionButton floatingActionButton;

    String strData[] = {"NonMember"};

    int imgData[] = {R.drawable.ic_nonmember};

    ArrayList<LangModel> langModelArrayList;

    @Override
    public void onBackPressed() {
        name= LoginActivity.getName();
        Log.e(name,"name in user Display");
        if(name.equals("security")) {
            Intent i = new Intent(SecurityDashboardActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_dashboard);
        floatingActionButton = findViewById(R.id.btn_security_logout);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityDashboardActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        gridView = findViewById(R.id.grid_view);
        langModelArrayList = new ArrayList<LangModel>();

        for (int i = 0; i < strData.length; i++) {
            LangModel langModel = new LangModel(strData[i], imgData[i]);
            langModelArrayList.add(langModel);
        }

        MyListAdapter myListAdapter = new MyListAdapter(this, langModelArrayList);
        gridView.setAdapter(myListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    Intent intent = new Intent(SecurityDashboardActivity.this, NonMemberDisplayActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
