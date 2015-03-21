package com.example.newdatabase.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marion on 03/20/15.
 */
public class ContactHelper extends SQLiteOpenHelper {

    private Cursor cursor;

    public ContactHelper(Context context, String databaseName, int version) {
        super(context, databaseName, null, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
