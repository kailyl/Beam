package com.me.kaily.beam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;
import static android.os.Build.ID;

public class databaseHelper extends SQLiteOpenHelper {
//    public static final String DATABASE_NAME = "mylist.db";
//    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
//    public static final String COL2 = "ITEM1";

    public databaseHelper(Context context){super(context,"mylist.db", null, 4);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = ("CREATE TABLE MyData (ID INTEGER PRIMARY KEY AUTOINCREMENT,WRITING Text)");
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyData");
        onCreate(db);
    }

    public boolean addData(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("WRITING", item1);

        long result = db.insert("MyData", null, contentValues);

        if (result == -1) {
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM MyData", null);
        return data;
    }

    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT WRITING FROM MyData WHERE WRITING = '" + name + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

//    public void updateName (String newText, int id, String oldText) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "UPDATE MyData SET WRITING = '" + newText + "' WHERE ID = '"
//                + id + "' AND WRITING = '" + oldText + "'";
//        Log.d(TAG, "updateText: query: " + query);
//        Log.d(TAG, "updateText: Setting text to " + newText);
//        db.execSQL(query);
//    }
//
    public void deleteName(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("MyData","WRITING=?", new String[]{String.valueOf(id)});
    }
}



