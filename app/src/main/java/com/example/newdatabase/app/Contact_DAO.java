package com.example.newdatabase.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marion on 03/20/15.
 */
public class Contact_DAO {
    private SQLiteDatabase database;
    private ContactHelper helper;
    private String[] allColumns = {ContactContract.Column_ID, ContactContract.NAME, ContactContract.phone};
    private Cursor cursor;

    public Contact_DAO(Context context) {
        helper = new ContactHelper(context, ContactContract.DATABASE_NAME, ContactContract.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public Contact createContact(String name, long number) {
        ContentValues values = new ContentValues();
        values.put(ContactContract.NAME, name);
        values.put(ContactContract.phone, number);

        if (database == null) {

            database = helper.getWritableDatabase();
        }
         database.insert(ContactContract.Table_Name, null, values);

        cursor = database.query(ContactContract.Table_Name,
                allColumns, null, null,
                null, null, null);
        cursor.moveToLast();
        Contact contact = new Contact();
        contact.setName(cursor.getString(0));
        contact.setNumber(cursor.getLong(1));
        return contact;
    }


    public ArrayList<Contact> getAllContacts() throws SQLException {
        this.open();
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        if (database == null) {
            database = helper.getReadableDatabase();
        }
        Cursor cursor = database.rawQuery("Select * from " + ContactContract.Table_Name, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Contact contact = new Contact();
            contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getLong(2));

            contacts.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return contacts;
    }

    public void deleteComment(Contact contact) {
        long id = contact.getId();
        System.out.println("Contact deleted with id: " + id);
        database.delete(ContactContract.Table_Name, ContactContract.Column_ID
                + " = " + id, null);
    }

    public Cursor getCursor() throws SQLException {
        if (isCursor()) {
            this.getAllContacts();

        }
        return cursor;
    }

    private boolean isCursor() {
        if (this.cursor == null)
            return false;
        return true;
    }
}
