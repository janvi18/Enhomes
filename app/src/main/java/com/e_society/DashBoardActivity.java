package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.e_society.adapter.MyListAdapter;
import com.e_society.display.AdminDisplayActivity;
import com.e_society.display.EventDisplayActivity;
import com.e_society.display.FeedbackDisplayActivity;
import com.e_society.display.HouseDisplayActivity;
import com.e_society.display.MaintenanceDisplayActivity;
import com.e_society.display.MaintenanceMasterDisplayActivity;
import com.e_society.display.MemberDisplayActivity;
import com.e_society.display.NonMemberDisplayActivity;
import com.e_society.display.PlaceDisplayActivity;
import com.e_society.display.RoleDisplayActivity;
import com.e_society.display.StaffDisplayActivity;
import com.e_society.display.UserDisplayActivity;
import com.e_society.model.LangModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    String name;
    GridView gridView;
    FloatingActionButton floatingActionButton;
    String strData[] = {"Role","Admin", "User", "Member", "Event", "Maintenance", "Master","Security", "FeedBack"
            , "NonMember", "House", "Place"};
    int imgData[] = {R.drawable.role,R.drawable.user, R.drawable.user, R.drawable.member, R.drawable.event,
            R.drawable.maintanence,R.drawable.maintanence, R.drawable.staff, R.drawable.ic_feedback, R.drawable.ic_nonmember, R.drawable.ic_house, R.drawable.place};

    ArrayList<LangModel> langModelArrayList;

    @Override
    public void onBackPressed() {
        name= LoginActivity.getName();
        Log.e(name,"name in user Display");
       if(name.equals("admin")) {
            Intent i = new Intent(DashBoardActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dash_board);
        floatingActionButton = findViewById(R.id.btn_logout);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardActivity.this, MainActivity.class);
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
                if (i == 0) {
                    Intent intent = new Intent(DashBoardActivity.this, RoleDisplayActivity.class);
                    startActivity(intent);
                }else if (i == 1) {
                    Intent intent = new Intent(DashBoardActivity.this, AdminDisplayActivity.class);
                    startActivity(intent);
                }
                else if (i == 2) {
                    Intent intent = new Intent(DashBoardActivity.this, UserDisplayActivity.class);
                    startActivity(intent);
                } else if (i == 3) {
                    Intent intent = new Intent(DashBoardActivity.this, MemberDisplayActivity.class);
                    startActivity(intent);

                } else if (i == 4) {
                    Intent intent = new Intent(DashBoardActivity.this, EventDisplayActivity.class);
                    startActivity(intent);

                } else if (i == 5) {
                    Intent intent = new Intent(DashBoardActivity.this, MaintenanceDisplayActivity.class);
                    startActivity(intent);

                }else if (i == 6) {
                    Intent intent = new Intent(DashBoardActivity.this, MaintenanceMasterDisplayActivity.class);
                    startActivity(intent);

                } else if (i == 7) {
                    Intent intent = new Intent(DashBoardActivity.this, StaffDisplayActivity.class);
                    startActivity(intent);

                } else if (i == 8) {
                    Intent intent = new Intent(DashBoardActivity.this, FeedbackDisplayActivity.class);
                    startActivity(intent);

                } else if (i == 9) {
                    Intent intent = new Intent(DashBoardActivity.this, NonMemberDisplayActivity.class);
                    startActivity(intent);

                } else if (i == 10) {
                    Intent intent = new Intent(DashBoardActivity.this, HouseDisplayActivity.class);
                    startActivity(intent);

                } else if (i == 11) {
                    Intent intent = new Intent(DashBoardActivity.this, PlaceDisplayActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}