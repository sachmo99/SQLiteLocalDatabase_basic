package com.example.android.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by COOL on 07-07-2018.
 */

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,"Hello.db", factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqlitedatabase){
        sqlitedatabase.execSQL("CREATE TABLE STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT NOT NULL,LASTNAME TEXT);");
    }
    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int i1){
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS STUDENT;");
        onCreate(sqlitedatabase);
    }
    public void insert(String firstname, String lastname){
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("FIRSTNAME",firstname);
        contentvalues.put("LASTNAME",lastname);
        this.getWritableDatabase().insertOrThrow("STUDENT","",contentvalues);
    }
    public void delete(String firstname){
        this.getWritableDatabase().delete("STUDENT","FIRSTNAME='"+firstname+"'",null);
    }
    public void update(String old_firstname,String new_firstname){
        this.getWritableDatabase().execSQL("UPDATE STUDENT SET FIRSTNAME='"+new_firstname+"' WHERE FIRSTNAME+'"+old_firstname+"'");
    }
    public void delete_all(){
        this.getWritableDatabase().delete("STUDENT",null,null);
    }
    public void list(TextView textview){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENT",null);
        textview.setText("FIRST NAME  --  LAST NAME \n");
        while(cursor.moveToNext()){
            textview.append(cursor.getString(1) + " "+cursor.getString(2) + "\n");
        }
    }
}
