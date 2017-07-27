package com.example.mycontacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.mycontacts.ID";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });

        listView = (ListView)findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor)adapterView.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, ViewContactActivity.class);
                intent.putExtra(EXTRA_ID, cursor.getLong(cursor.getColumnIndex(ContactContract.ContactEntry._ID)));
                MainActivity.this.startActivity(intent);
            }
        });
    }

    //mainactivity is resumed after onFinish(0 called
    //to get latest contact list
    @Override
    public void onResume() {
        super.onResume();

        ContactDbQueries dbq = new ContactDbQueries(new ContactDbHelper(getApplicationContext()));

        String[] columns = {
                ContactContract.ContactEntry._ID,
                ContactContract.ContactEntry.COLUMN_NAME_NAME,
                ContactContract.ContactEntry.COLUMN_NAME_PHONE
        };
        Cursor cursor = dbq.query(columns, null, null, null, null, ContactContract.ContactEntry.COLUMN_NAME_NAME + " ASC");

        ContactCursorAdapter adapter = new ContactCursorAdapter(this, cursor, 0);

        listView.setAdapter(adapter);
    }


}
