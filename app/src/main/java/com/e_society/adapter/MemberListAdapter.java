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
import com.e_society.model.MemberLangModel;
import com.e_society.update.EventUpdateActivity;

import java.util.ArrayList;

public class MemberListAdapter extends BaseAdapter {

    Context context;
    ArrayList<MemberLangModel> memberLangModelArrayList;

    public MemberListAdapter(Context context, ArrayList<MemberLangModel> arrayList) {
        this.context = context;
        this.memberLangModelArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return memberLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberLangModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.raw_list, null);

        TextView TvData = view.findViewById(R.id.tv_data);
        TvData.setText(memberLangModelArrayList.get(position).get_id() + ""
                + memberLangModelArrayList.get(position).getHouseId()
                + memberLangModelArrayList.get(position).getMemberName() + ""
                + memberLangModelArrayList.get(position).getAge() + ""
                + memberLangModelArrayList.get(position).getContactNo() + ""
                + memberLangModelArrayList.get(position).getGender()
                + memberLangModelArrayList.get(position).getDateOfBirth());

        ImageView imgEdit = view.findViewById(R.id.img_edit);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = memberLangModelArrayList.get(position).get_id();
                Log.e("id in edit :", id);

                Intent intent = new Intent(context, EventUpdateActivity.class);
                intent.putExtra("MEMBER_ID", id);
                intent.putExtra("HOUSE ID", memberLangModelArrayList.get(position).getHouseId());
                intent.putExtra("MEMBER NAME", memberLangModelArrayList.get(position).getMemberName());
                intent.putExtra("MEMBER AGE", memberLangModelArrayList.get(position).getAge());
                intent.putExtra("CONTACT NUMBER:", memberLangModelArrayList.get(position).getContactNo());
                intent.putExtra("GENDER", memberLangModelArrayList.get(position).getGender());
                intent.putExtra("DATE OF BIRTH", memberLangModelArrayList.get(position).getDateOfBirth());

                context.startActivity(intent);
            }
        });

        return (view);


    }
        }

