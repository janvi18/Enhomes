package com.e_society;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.e_society.display.HouseDisplayActivity;
import com.e_society.display.NonMemberDisplayActivity;
import com.e_society.model.HouseLangModel;
import com.e_society.model.NonMemberLangModel;
import com.e_society.model.UserLangModel;
import com.e_society.utils.Utils;
import com.e_society.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NonMemberActivity extends AppCompatActivity {

    EditText edtVisitorName;
    TextView tvArrivingTime, tvHouseId;
    ImageButton imgBtnArrivingTime;
    RadioGroup radioGroup, pickup_radioGroup, deliver_RadioGroup;
    Button btnAddNonMember;
    String strSelectedHouse, houseId,strHouseDeets;
    Spinner spinnerNonMember;
    private int hour;
    private int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_member);

        tvHouseId = findViewById(R.id.tv_houseID);
        edtVisitorName = findViewById(R.id.edt_visitorName);
        tvArrivingTime = findViewById(R.id.tv_arrivingTime);
        imgBtnArrivingTime = findViewById(R.id.btn_arrivingTime);
        spinnerNonMember = findViewById(R.id.spinner_nonMember);

        radioGroup = findViewById(R.id.visited_RadioGroup);
        pickup_radioGroup = findViewById(R.id.pickup_radiogroup);
        deliver_RadioGroup = findViewById(R.id.deliver_radiogroup);

        btnAddNonMember = findViewById(R.id.btn_nonMember);

        btnAddNonMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strHouseId = tvHouseId.getText().toString();
                String strVisitorName = edtVisitorName.getText().toString();
                String strArrivingTime = tvArrivingTime.getText().toString();


                if (strVisitorName.length() == 0) {
                    edtVisitorName.requestFocus();
                    edtVisitorName.setError("FIELD CANNOT BE EMPTY");
                } else if (!strVisitorName.matches("[a-zA-Z ]+")) {
                    edtVisitorName.requestFocus();
                    edtVisitorName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                } else if (strArrivingTime.length() == 0) {
                    tvArrivingTime.requestFocus();
                    tvArrivingTime.setError("FIELD CANNOT BE EMPTY");
                } else {

                    int id = radioGroup.getCheckedRadioButtonId();
                    int pid = pickup_radioGroup.getCheckedRadioButtonId();
                    int sid = deliver_RadioGroup.getCheckedRadioButtonId();

                    RadioButton radioButton = findViewById(id);
                    RadioButton pRadioButton = findViewById(pid);
                    RadioButton sRadioButton = findViewById(sid);

                    String strRadioButton = radioButton.getText().toString();
                    String strPRadioButton = pRadioButton.getText().toString();
                    String strSRadioButton = sRadioButton.getText().toString();

                    Log.e("radioButtons: ", strRadioButton + " " + strPRadioButton + " " + strSRadioButton);

                    Toast.makeText(NonMemberActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();
                    houseApi(strHouseDeets,houseId);
                    apiCall(strHouseId, strVisitorName, strArrivingTime, strRadioButton, strPRadioButton, strSRadioButton);
                }
            }
        });
        imgBtnArrivingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(NonMemberActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        tvArrivingTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);

                timePickerDialog.show();
            }
        });
        DisplayNonMemberApi();

    }

    private void DisplayNonMemberApi() {
        ArrayList<HouseLangModel> arrayList = new ArrayList<HouseLangModel>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Utils.NONMEMBER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("in Display Users", "Display--onResponse:" + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    int j = 1;
                    String strHouse[] = new String[jsonArray.length() + 1];
                    strHouse[0] = "Select Your Name";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strUserId = jsonObject1.getString("_id");
                        String strHouseDeets = jsonObject1.getString("houseDeets");


                        strHouse[j] = strHouseDeets;
                        j++;
                    }
                    ArrayAdapter<String> arrayAdapter = new
                            ArrayAdapter<String>(NonMemberActivity.this, android.R.layout.simple_list_item_1, strHouse) {
                                @Override
                                public View getDropDownView(int position, @Nullable View convertView,
                                                            @NonNull ViewGroup parent) {

                                    TextView tvData = (TextView) super.getDropDownView(position, convertView, parent);
                                    tvData.setTextColor(Color.BLACK);
                                    tvData.setTextSize(20);
                                    return tvData;
                                }

                            };
                    spinnerNonMember.setAdapter(arrayAdapter);

                    spinnerNonMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strSelectedHouse = strHouse[position];
                            Log.e("selected house", strSelectedHouse);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", String.valueOf(error));
            }
        });
        VolleySingleton.getInstance(NonMemberActivity.this).addToRequestQueue(stringRequest);
    }

    private void houseApi(String strHouseDeets, String userId) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.HOUSE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("House Response ===", "onResponse: " + response);
                Intent i = new Intent(NonMemberActivity.this, HouseDisplayActivity.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("houseDetails", strHouseDeets);
                map.put("user", userId);

                return map;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    private void apiCall(String strHouseId, String strVisitorName, String strArrivingTime, String strRadioButton, String strPRadioButton, String strDRadioButton) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.NONMEMBER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Place Response ===", "onResponse: " + response);
                Intent i = new Intent(NonMemberActivity.this, NonMemberDisplayActivity.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("house", strHouseId);
                hashMap.put("name", strVisitorName);
                hashMap.put("arrivingTime", strArrivingTime);
                hashMap.put("isVisited", strRadioButton);
                hashMap.put("pickup", strPRadioButton);
                hashMap.put("deliver", strDRadioButton);


                return hashMap;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
