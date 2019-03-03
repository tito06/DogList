package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseAcces {

    private SQLiteAssetHelper openhelper;
    private SQLiteDatabase db;
    private static DatabaseAcces instance;
    Cursor c1 = null;

    private DatabaseAcces(Context context) {
        this.openhelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAcces getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseAcces(context);
        return instance;
    }

    public void open() {
        this.db = openhelper.getWritableDatabase();
    }

    public void close() {
        if (db != null)
            this.db.close();
    }

    public String[] displayRecycler(String dq){
        c1 = db.rawQuery(dq, null);
        String[] s = new String[c1.getCount()];
        int count = 0;
        while (c1.moveToNext()) {
            s[count] = c1.getString(0);
            count = count + 1;
        }
        System.out.println(s.length);
        return s;

    }

    public long addtotable(String s){
        ContentValues c = new ContentValues();
        c.put("Image_Link",s);
        long x= db.insert("my_table", null, c);
        return x;
    }
}
