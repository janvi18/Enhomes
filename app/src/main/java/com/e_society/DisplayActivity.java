package com.e_society;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.e_society.adapter.MyRoleAdapter;
import com.e_society.model.RoleModel;
import com.e_society.utils.VolleySingleton;
import com.e_society.utils.Utils;

public class DisplayActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        listView = findViewById(R.id.ls_listview);

        ArrayList<RoleModel> arrayList = new ArrayList<RoleModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Utils.ROLE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Login Response ===", "onResponse: " + response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int roleId = jsonObject.getInt("roleId");
                        String roleName = jsonObject.getString("roleName");

                        RoleModel roleModel = new RoleModel();
                        roleModel.setRoleId(roleId);
                        roleModel.setRoleName(roleName);
                        arrayList.add(roleModel);
                    }
                    MyRoleAdapter myListAdapter = new MyRoleAdapter(DisplayActivity.this, arrayList);
                    listView.setAdapter(myListAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(DisplayActivity.this).addToRequestQueue(stringRequest);


    }
}