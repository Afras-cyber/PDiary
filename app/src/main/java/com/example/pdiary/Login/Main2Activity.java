package com.example.pdiary.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pdiary.R;

public class Main2Activity extends AppCompatActivity {
    EditText e1, e2,e3;
    Button b1;
    TextView t1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registation);

        db= new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.edt_username);
        e2=(EditText)findViewById(R.id.edt_password);
        e3=(EditText)findViewById(R.id.edt_confPassword);
        b1=(Button) findViewById(R.id.btn_registation);
        t1=(TextView) findViewById(R.id.txt_login);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent=new Intent(Main2Activity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });
      b1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String user =e1.getText().toString().trim();
              String pwd =e2.getText().toString().trim();
              String cnf_pwd=e3.getText().toString().trim();
              if(pwd.equals(cnf_pwd)){
                  long val =db.addUser(user,pwd);
                  if(val>0){
                      Toast.makeText(Main2Activity.this,"Registation Sucessful",Toast.LENGTH_LONG).show();
                      Intent intent =new Intent(Main2Activity.this,MainActivity.class);
                      startActivity(intent);
                  }else{
                      Toast.makeText(Main2Activity.this,"Registation error",Toast.LENGTH_LONG).show();

                  }

              }
              else{
              Toast.makeText(Main2Activity.this,"Password not matching",Toast.LENGTH_LONG).show();
              }
          }
      });
    }
}
