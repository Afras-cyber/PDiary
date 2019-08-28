package com.example.pdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class diaryAdapter extends BaseAdapter {

    Context context;
    ArrayList<DiarydataProvider> arrayList;

    public diaryAdapter(Context context,ArrayList<DiarydataProvider> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         if (convertView==null){

             LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(R.layout.view_record,null);

             TextView t1_id =(TextView)convertView.findViewById(R.id.txt_date);
             TextView t1_sub =(TextView)convertView.findViewById(R.id.txt_subject);
             TextView t1_desc=(TextView)convertView.findViewById(R.id.txt_desc);

             DiarydataProvider diarydataProvider = arrayList.get(position);

             t1_id.setText(diarydataProvider.getDate());
             t1_sub.setText(diarydataProvider.getSubject());
             t1_desc.setText(diarydataProvider.getDescription());
         }
        return convertView;
    }


}
