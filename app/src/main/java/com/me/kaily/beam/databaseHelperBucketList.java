package com.me.kaily.beam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class databaseHelperBucketList extends SQLiteOpenHelper {

    public databaseHelperBucketList(Context context) {
        super(context, "bucketlist.db", null, 6);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = ("CREATE TABLE MyData (ID INTEGER PRIMARY KEY AUTOINCREMENT,WRITING Text)");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyData");
        onCreate(db);
    }

    public boolean addData(String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("WRITING", item1);

        long result = db.insert("MyData", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM MyData", null);
        return data;
    }

    public void update(String newName, int id, String oldName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE MyData SET WRITING = '" + newName + "' WHERE WRITING = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT WRITING FROM MyData WHERE WRITING = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteName(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("MyData", "WRITING=?", new String[]{String.valueOf(id)});
    }
}