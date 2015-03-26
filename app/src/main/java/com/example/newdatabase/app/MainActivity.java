package com.example.newdatabase.app;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.sql.SQLException;
import java.util.List;


public class MainActivity extends ListActivity {
private List<Contact>contactlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Contact_DAO myDAO = new Contact_DAO(this);
        Cursor cursor = this.getContentResolver().query(ContactContract.CONTENT_URI, null, null, null, null);

        try {
            myDAO.createContact("Marion",879513851);
          contactlist = myDAO.getAllContacts();
            System.out.println(contactlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        startManagingCursor(cursor);
        ListView mainView = (ListView) findViewById(android.R.id.list);

        mainView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView textView = (TextView)view;
                Toast message = Toast.makeText(getApplicationContext(),textView.getText(),Toast.LENGTH_SHORT);
                return false;
            }
        });
     /*   ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.two_list_items, cursor, new String[]{
                ContactContract.NAME, ContactContract.phone
        }, new int[]{R.id.name_text, R.id.number_text});*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contactlist);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast toast = Toast.makeText(this, "Marion", Toast.LENGTH_LONG);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        
    }
}
