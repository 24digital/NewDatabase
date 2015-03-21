package com.example.newdatabase.app;

import android.net.Uri;

/**
 * Created by Marion on 03/20/15.
 */
public class ContactContract {
    public static final String[] COLUMNS = {"_id", "name", "phone"};
    public static final String Table_Name = "EMERGENCY_CONTACT";
    public static final String Column_ID = "_id";
    public static final String NAME = "name";
    public static final String phone = "phone";
    public static final String AUTHORITY
            = "com.example.databaseapplication.app";
    public final static String DATABASE_NAME = "Contacts";

    public final static int DATABASE_VERSION = 1;

    public static final String COMPLETE_URI = "content://" + AUTHORITY + "/" + Table_Name;
    public static final Uri CONTENT_URI = Uri.parse(COMPLETE_URI);

}
