package com.e_society.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e_society.R;
import com.e_society.model.RoleModel;

import java.util.ArrayList;

public class MyRoleAdapter extends BaseAdapter {

    Context context;
    ArrayList<RoleModel> roleModelArrayList;

    public MyRoleAdapter(Context context, ArrayList<RoleModel> roleModelArrayList) {
        this.context = context;
        this.roleModelArrayList = roleModelArrayList;
    }

    @Override
    public int getCount() {
        return roleModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return roleModelArrayList.get(position);
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

        tvData.setText(roleModelArrayList.get(position).getRoleId() + " " + roleModelArrayList.get(position).getRoleName());

        return view;
    }
}





