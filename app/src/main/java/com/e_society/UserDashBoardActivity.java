package com.e_society;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.e_society.adapter.MyListAdapter;
import com.e_society.model.LangModel;

import java.util.ArrayList;

public class UserDashBoardActivity extends AppCompatActivity {

    GridView gridView;

    String strData[]={"Role","User","Member","Event","Society","Maintenance","Complaint",
            "Suggestion", "Delivery","Visitor","House","Place"};

    int imgData[]={R.drawable.role,R.drawable.user,R.drawable.member,R.drawable.event,
           R.drawable.maintanence,R.drawable.complaint,
            R.drawable.suggestion,R.drawable.delivery,R.drawable.visitor,R.drawable.house,R.drawable.place};

    ArrayList<LangModel> langModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);

        gridView = findViewById(R.id.grid_view);
        langModelArrayList= new ArrayList<LangModel>();

        for(int i=0;i<strData.length;i++)
        {
            LangModel langModel = new LangModel(strData[i], imgData[i]);
            langModelArrayList.add(langModel);
        }

        MyListAdapter myListAdapter = new MyListAdapter(this, langModelArrayList);
        gridView.setAdapter(myListAdapter);

    }
}