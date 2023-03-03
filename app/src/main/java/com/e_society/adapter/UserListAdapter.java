package com.e_society.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.BaseAdapter;

import com.e_society.R;
import com.e_society.model.UserLangModel;
import com.e_society.update.UserUpdateActivity;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {

    Context context;
    ArrayList<UserLangModel> userLangModelArrayList;

    public UserListAdapter(Context context, ArrayList<UserLangModel> langModelArrayList) {
        this.context = context;
        this.userLangModelArrayList = langModelArrayList;
    }

    @Override
    public int getCount() {
        return userLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userLangModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.raw_list, null);


        TextView tvData = view.findViewById(R.id.tv_data);
        tvData.setText(userLangModelArrayList.get(position).getRoleId() + " " + userLangModelArrayList.get(position).get_id() + " " +
                userLangModelArrayList.get(position).getFirstName() + " " + userLangModelArrayList.get(position).getLastName() + " "
                + userLangModelArrayList.get(position).getDateOfBirth() + " " + userLangModelArrayList.get(position).getAge() + " " +
                userLangModelArrayList.get(position).getGender() + " " + userLangModelArrayList.get(position).getContactNo() +
                " " + userLangModelArrayList.get(position).getEmail() + " " + userLangModelArrayList.get(position).getPassword());

        ImageView
                imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = userLangModelArrayList.get(position).get_id();
                Log.e("id in edit: ", id);

                Intent intent = new Intent(context, UserUpdateActivity.class);
                intent.putExtra("USER_ID", id);
                intent.putExtra("ROLE_ID", userLangModelArrayList.get(position).getRoleId());
                intent.putExtra("FIRST_NAME", userLangModelArrayList.get(position).getFirstName());
                intent.putExtra("LAST_NAME", userLangModelArrayList.get(position).getLastName());
                intent.putExtra("DATE_OF_BIRTH", userLangModelArrayList.get(position).getDateOfBirth());
                intent.putExtra("AGE", userLangModelArrayList.get(position).getAge());
                intent.putExtra("GENDER", userLangModelArrayList.get(position).getGender());
                intent.putExtra("CONTACT_NO", userLangModelArrayList.get(position).getContactNo());
                intent.putExtra("EMAIL", userLangModelArrayList.get(position).getEmail());
                intent.putExtra("PASSWORD", userLangModelArrayList.get(position).getPassword());

                context.startActivity(intent);
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = userLangModelArrayList.get(position).get_id();
                Log.e("id in edit: ", id);

                Intent intent = new Intent(context, UserUpdateActivity.class);
                intent.putExtra("USER_ID", id);
                intent.putExtra("ROLE_ID", userLangModelArrayList.get(position).getRoleId());
                intent.putExtra("FIRST_NAME", userLangModelArrayList.get(position).getFirstName());
                intent.putExtra("LAST_NAME", userLangModelArrayList.get(position).getLastName());
                intent.putExtra("DATE_OF_BIRTH", userLangModelArrayList.get(position).getDateOfBirth());
                intent.putExtra("Age", userLangModelArrayList.get(position).getAge());
                intent.putExtra("GENDER", userLangModelArrayList.get(position).getGender());
                intent.putExtra("CONTACT_NO", userLangModelArrayList.get(position).getContactNo());
                intent.putExtra("EMAIL", userLangModelArrayList.get(position).getEmail());
                intent.putExtra("PASSWORD", userLangModelArrayList.get(position).getPassword());

                context.startActivity(intent);
            }
        });
        return view;
    }
}