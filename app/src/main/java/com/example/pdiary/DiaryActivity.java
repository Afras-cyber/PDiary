package com.example.pdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {
    Button writeDiary;
    ListView listView;
    DBdiaryHelper dBdiaryHelper;
    /////////////////////////////////////////
    ArrayList<DiarydataProvider> arrayList;
    diaryAdapter dAdapter;
    /////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        //casting elment
        dBdiaryHelper=new DBdiaryHelper(this);

        writeDiary=(Button)findViewById(R.id.btn_diary_write);
        listView=(ListView)findViewById(R.id.listV_diary);
        /////////////////////////////////////////////////
        arrayList=new ArrayList<>();
        loadDatainListView();
        /////////////////////////////////////////////////
/*
        ArrayList<String>thelist=new ArrayList<>();
        Cursor cursor=dBdiaryHelper.getinformation();
        if(cursor.getCount()==0){
            Toast.makeText(DiaryActivity.this,"Data base empty",Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()){
                thelist.add(cursor.getString(2));
            }

        }

        ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, android.R.id.text1,thelist);
        listView.setAdapter(listAdapter);
*/
        ////////////////////////////////////////////////////////
        writeDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DiaryActivity.this,writeDiaryActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadDatainListView() {
        arrayList=dBdiaryHelper.getAlldata();
        dAdapter=new diaryAdapter(this,arrayList);
        listView.setAdapter(dAdapter);
        dAdapter.notifyDataSetChanged();
    }

}
