package com.example.newdatabase.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

        long insertID = database.insert(ContactContract.Table_Name, null, values);

        cursor = database.query(ContactContract.Table_Name,
                allColumns, null, null,
                null, null, null);
        cursor.moveToFirst();
        Contact contact = new Contact();
        contact.setName(cursor.getString(0));
        contact.setNumber(cursor.getLong(1));
        return contact;
    }


    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<Contact>();

        Cursor cursor = database.rawQuery("Select * from "+ContactContract.Table_Name,)

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Contact contact = new Contact();
            contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getLong(2));
            contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getLong(2));
            contacts.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }

    public void deleteComment(Contact contact) {
        long id = contact.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(ContactContract.Table_Name, ContactContract.Column_ID
                + " = " + id, null);
    }

    public Cursor getCursor() {
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
