package com.example.newdatabase.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marion on 03/20/15.
 */
public class ContactHelper extends SQLiteOpenHelper {



    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ContactContract.Table_Name + " (" +
          ContactContract.Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ContactContract.NAME
            + " TEXT NOT NULL,"
            + ContactContract.phone+ " INTEGER NOT NULL )";


    public ContactHelper(Context context, String databaseName, int version) {
        super(context, databaseName, null, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
