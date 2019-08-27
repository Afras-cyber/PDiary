package com.example.pdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1;
    TextView t1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.edt_username);
        e2=(EditText)findViewById(R.id.edt_password);
        b1=(Button) findViewById(R.id.btn_login);
        t1=(TextView) findViewById(R.id.txt_Registation);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String user=e1.getText().toString().trim();
               String pwd=e2.getText().toString().trim();
               Boolean res=db.checkUser(user,pwd);
               if(res== true) {
                   Toast.makeText(MainActivity.this,"Successful Login",Toast.LENGTH_LONG).show();
                   Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                   startActivity(intent);
               }
               else {
                   Toast.makeText(MainActivity.this,"Unsuccessful Login",Toast.LENGTH_LONG).show();

               }

           }
       });
    }
}