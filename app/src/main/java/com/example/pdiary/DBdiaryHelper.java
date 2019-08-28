package com.example.pdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DBdiaryHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "diary.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_DIARY = "DIARY";
    public static final String D_COL1 = "D_ID";
    public static final String D_COL2 = "subject";
    public static final String D_COL3 = "discripton";
    public static final String D_COL4 = "currentDate";


    public DBdiaryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE DIARY (D_ID INTEGER PRIMARY KEY AUTOINCREMENT,subject TEXT,discripton TEXT,currentDate TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);
        onCreate(sqLiteDatabase);
    }

    public long addUser1(String desc,String subject) {
        ///////////////////////////////////////////////////
        Calendar calendar = Calendar.getInstance();
        String currentdate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
       // TextView textView2 = findViewById(R.id.id_time);
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        String time=format.format(calendar.getTime())+"\n"+currentdate;
        //textView2.setText(time);
        ////////////////////////////////////////////////////
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", subject);
        contentValues.put("discripton", desc);
        contentValues.put("currentDate",time);
        long res = db.insert("DIARY", null, contentValues);
        db.close();
        return res;
    }/*
        public Cursor getinformation() {
            String[] columns = {D_COL1,D_COL2,D_COL3};
            SQLiteDatabase db = getReadableDatabase();
         //   String selection = D_COL3 + "=?";
            Cursor cursor = db.query(TABLE_DIARY, columns, null, null, null, null, null);
            if (cursor != null){
                cursor.moveToNext();
            }
            return cursor;
        }*/

    public ArrayList<DiarydataProvider> getAlldata() {
        ArrayList<DiarydataProvider> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from DIARY", null);
        while (cursor.moveToNext()) {
           // String id1 = cursor.getString(0);
            String id = cursor.getString(3);
            String sub = cursor.getString(1);
            String desc = cursor.getString(2);

            DiarydataProvider diarydataProvider = new DiarydataProvider(id, sub, desc);

            arrayList.add(diarydataProvider);

        }
        return arrayList;
    }

}
