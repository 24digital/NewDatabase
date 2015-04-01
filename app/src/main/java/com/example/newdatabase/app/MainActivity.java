package com.example.newdatabase.app;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {
    private ArrayList<Contact> contactlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Contact_DAO myDAO = new Contact_DAO(this);

    if(contactlist ==null) {
        try {
            myDAO.createContact("Marion", 879513851);
            contactlist = myDAO.getAllContacts();
            System.out.println(contactlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
     //   startManagingCursor(cursor);
        ListView mainView = (ListView) findViewById(android.R.id.list);

      mainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              TextView tempMessage = (TextView)view;
              Toast message = Toast.makeText(MainActivity.this,tempMessage.getText(),Toast.LENGTH_LONG);
              message.show();
          }
      });

        mainView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView viewTemp = (TextView)v;
            Toast message = Toast.makeText(MainActivity.this,viewTemp.getText(),Toast.LENGTH_LONG);
                message.show();
                return true;
            }
        });
     /*   ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.two_list_items, cursor, new String[]{
                ContactContract.NAME, ContactContract.phone
        }, new int[]{R.id.name_text, R.id.number_text});*/
      //  SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.activity_main,cursor,);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contactlist);
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
        outState.putStringArrayList("conacts",contactlist);
    }
}
