package com.example.myapplication;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    private static final String DB_Name= "Test.db";
    private static final int DB_Version=1;

    public DatabaseOpenHelper(Context context) {
        super(context, DB_Name, null,DB_Version);
    }
}
