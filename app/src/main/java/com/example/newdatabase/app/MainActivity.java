package com.example.newdatabase.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.sql.SQLException;
import java.util.ArrayList;


public class MainActivity extends ListActivity {
    private ArrayList<Contact> contactlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.land_orient);
        Contact_DAO myDAO = new Contact_DAO(this);

    if(contactlist ==null) {
        try {
            //insert contacts
    myDAO.createContact("Marion", "843589452");
            myDAO.createContact("Marion", "843589452");
            myDAO.createContact("Corey Mark", "8435894532");
            myDAO.createContact("Michael Lewis", "8435894552");
            myDAO.createContact("Jimmy Hoove", "8435889452");
            myDAO.createContact("Same Richard", "843589452");
            myDAO.createContact("Vick Sam", "8435891452");

           //Get all stored contacts
            contactlist = myDAO.getAllContacts();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //Get listview to manage
        ListView mainView = (ListView) findViewById(android.R.id.list);

        //Set click listener
      mainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              TextView tempMessage = (TextView)view;
              Toast message = Toast.makeText(MainActivity.this,tempMessage.getText(),Toast.LENGTH_LONG);
              message.show();
          }
      });

//Setup long click listener
        mainView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView viewTemp = (TextView)v;
            Toast message = Toast.makeText(MainActivity.this,viewTemp.getText(),Toast.LENGTH_LONG);
                message.show();
                return true;
            }
        });
//Commit binding
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
        //Commit binding of already stored data again after orientation change
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,state.getStringArrayList("contacts"));
        setListAdapter(adapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Commit array list to bundle which must be of strings
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i< contactlist.size(); i++)
        {

            temp.add(contactlist.get(i).toString());
        }
        outState.putStringArrayList("contacts",temp);
    }
}
