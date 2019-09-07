package com.example.pdiary.DarkPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pdiary.R;

public class writeDiaryActivity extends AppCompatActivity {
    EditText e1, e2,e3;
    Button b1;

    DBdiaryHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);
        db= new DBdiaryHelper(this);

        e2=(EditText)findViewById(R.id.editxt_subDiary);

        e3=(EditText)findViewById(R.id.editxt_diary);
        b1=(Button) findViewById(R.id.btn_write_save);
/*
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String user=e1.getText().toString().trim();
                String pwd=e2.getText().toString().trim();
                Boolean res=db.checkUser1(pwd);
                if(res== true) {
                    Toast.makeText(writeDiaryActivity.this,"Successful Login",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(writeDiaryActivity.this,"Unsuccessful Login",Toast.LENGTH_LONG).show();

                }

            }
        });*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
///             String user =e1.getText().toString().trim();
                String sub =e2.getText().toString().trim();
                String desc=e3.getText().toString().trim();

                   db.addUser1(sub,desc);
                    //if(val>0){
                        Toast.makeText(writeDiaryActivity.this,"Saving..",Toast.LENGTH_LONG).show();
                       Intent intent =new Intent(writeDiaryActivity.this, DiaryActivity.class);
                        startActivity(intent);
                    //}else{
                      //  Toast.makeText(Main2Activity.this,"Registation error",Toast.LENGTH_LONG).show();

                    }

                //}
                //else{
                 //   Toast.makeText(Main2Activity.this,"Password not matching",Toast.LENGTH_LONG).show();
            ///   / }

        });
}

}

