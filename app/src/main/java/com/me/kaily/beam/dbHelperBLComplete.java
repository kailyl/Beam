package com.me.kaily.beam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class dbHelperBLComplete extends SQLiteOpenHelper{

    public dbHelperBLComplete (Context context){super(context,"bucketlist.db", null, 5);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = ("CREATE TABLE blComplete (ID INTEGER PRIMARY KEY AUTOINCREMENT,WRITING Text)");
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS blComplete");
        onCreate(db);
    }

    public boolean addData(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("WRITING", item1);

        long result = db.insert("blComplete", null, contentValues);

        if (result == -1) {
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM blComplete", null);
        return data;
    }

    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT WRITING FROM blComplete WHERE WRITING = '" + name + "'";
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
        db.delete("blComplete","WRITING=?", new String[]{String.valueOf(id)});
    }
}
