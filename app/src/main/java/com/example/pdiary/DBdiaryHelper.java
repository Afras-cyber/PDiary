package com.example.pdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBdiaryHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="diary.db";
    public static final int DATABASE_VERSION =1;
    public static final String TABLE_DIARY = "DIARY";
    public static final String  D_COL1="D_ID";
    public static final String  D_COL2="subject";
    public static final String  D_COL3="discripton";


    public DBdiaryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE DIARY (D_ID INTEGER PRIMARY KEY AUTOINCREMENT,subject TEXT,discripton TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);
        onCreate(sqLiteDatabase);
    }
    public long addUser1(String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
      //  contentValues.put("subject", subject);
        contentValues.put("discripton", desc);
        long res = db.insert("DIARY", null, contentValues);
        db.close();
        return res;
    }
        public Cursor getinformation() {
            String[] columns = {D_COL1};
            SQLiteDatabase db = getReadableDatabase();
         //   String selection = D_COL3 + "=?";
            Cursor cursor = db.query(TABLE_DIARY, columns, null, null, null, null, null);
            if (cursor != null){
                cursor.moveToNext();
            }
            return cursor;
        }
}
