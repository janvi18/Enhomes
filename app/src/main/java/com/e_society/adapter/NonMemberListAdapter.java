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

import com.e_society.R;
import com.e_society.model.NonMemberLangModel;
import com.e_society.model.PlaceLangModel;
import com.e_society.update.NonMemberUpdateActivity;
import com.e_society.update.PlaceUpdateActivity;

import java.util.ArrayList;

public class NonMemberListAdapter extends BaseAdapter {

    Context context;
    ArrayList<NonMemberLangModel> nonMemberLangModelArrayList;

    public NonMemberListAdapter(Context context, ArrayList<NonMemberLangModel> langModelArrayList) {
        this.context = context;
        this.nonMemberLangModelArrayList = langModelArrayList;
    }

    @Override
    public int getCount() {
        return nonMemberLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return nonMemberLangModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.nonmember_table, null);

        TextView tvName, tvArrivingTime, tvIsVisited, tvPickup, tvStatus;

        tvName = view.findViewById(R.id.tv_name);
        tvArrivingTime = view.findViewById(R.id.tvArrivingTime);
        tvIsVisited = view.findViewById(R.id.tv_isVisited);
        tvPickup = view.findViewById(R.id.tv_pickup);
        tvStatus = view.findViewById(R.id.tv_status);

        tvName.setText(nonMemberLangModelArrayList.get(position).getNonMemberName());
        tvArrivingTime.setText(nonMemberLangModelArrayList.get(position).getArrivingTime());
        tvIsVisited.setText(nonMemberLangModelArrayList.get(position).getIsVisited());
        tvPickup.setText(nonMemberLangModelArrayList.get(position).getPickup());
        tvStatus.setText(nonMemberLangModelArrayList.get(position).getStatus());

        ImageView imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = nonMemberLangModelArrayList.get(position).getId();

                Intent intent = new Intent(context, NonMemberUpdateActivity.class);
                intent.putExtra("ID", id);
                intent.putExtra("HOUSE_ID", nonMemberLangModelArrayList.get(position).getHouseId());
                intent.putExtra("NAME", nonMemberLangModelArrayList.get(position).getNonMemberName());
                intent.putExtra("ARRIVING_TIME", nonMemberLangModelArrayList.get(position).getArrivingTime());
                intent.putExtra("VISITED", nonMemberLangModelArrayList.get(position).getIsVisited());
                intent.putExtra("PICKUP", nonMemberLangModelArrayList.get(position).getPickup());
                intent.putExtra("STATUS", nonMemberLangModelArrayList.get(position).getStatus());


                context.startActivity(intent);
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1 = nonMemberLangModelArrayList.get(position).getId();

                Intent intent = new Intent(context, NonMemberUpdateActivity.class);
                intent.putExtra("ID", id1);
                intent.putExtra("HOUSE_ID", nonMemberLangModelArrayList.get(position).getHouseId());
                intent.putExtra("NAME", nonMemberLangModelArrayList.get(position).getNonMemberName());
                intent.putExtra("ARRIVING_TIME", nonMemberLangModelArrayList.get(position).getArrivingTime());
                intent.putExtra("VISITED", nonMemberLangModelArrayList.get(position).getIsVisited());
                intent.putExtra("PICKUP", nonMemberLangModelArrayList.get(position).getPickup());
                intent.putExtra("STATUS", nonMemberLangModelArrayList.get(position).getStatus());

                context.startActivity(intent);
            }
        });

        return view;
    }
}