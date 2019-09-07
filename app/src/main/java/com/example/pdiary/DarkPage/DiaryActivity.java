package com.example.pdiary.DarkPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pdiary.R;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity  {

    Button writeDiary;
    ListView listView;
    DBdiaryHelper dBdiaryHelper;
    ArrayList<DiarydataProvider> arrayList;
    diaryAdapter dAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        //casting elment
        dBdiaryHelper=new DBdiaryHelper(this);

        writeDiary=(Button)findViewById(R.id.btn_diary_write);
        listView=(ListView)findViewById(R.id.listV_diary);

        arrayList=new ArrayList<>();
        loadDatainListView();

        ////////////////////////////////////////////////////////
        writeDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DiaryActivity.this, writeDiaryActivity.class);
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
