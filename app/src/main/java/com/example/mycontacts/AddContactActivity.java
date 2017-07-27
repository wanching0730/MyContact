package com.example.mycontacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etEmail;
    private EditText etPhone;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private boolean saved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = getSharedPreferences(getResources().getString(R.string.sp_save_state), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        etName = (EditText)findViewById(R.id.input_name);
        etEmail = (EditText)findViewById(R.id.input_email);
        etPhone = (EditText)findViewById(R.id.input_phone);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();

                //can use db.writable also

                ContactDbQueries dbq = new ContactDbQueries(new ContactDbHelper(getApplicationContext()));
                Contact contact = new Contact(name, email, phone);
                if(dbq.insert(contact) != 0) {
                    saved = true;
                }
                finish(); //current activity will pause, stop, destroy and go back to previous activity
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //check the data is saved or not, if save, clear all component
        if(saved) {
            editor.clear();
        }
        else {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();

            editor.putString("SAVE_STATE_NAME", name);
            editor.putString("SAVE_STATE_EMAIL", email);
            editor.putString("SAVE_STATE_PHONE", phone);
        }

        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String name = sharedPref.getString("SAVE_STATE_NAME", "");
        String email = sharedPref.getString("SAVE_STATE_EMAIL", "");
        String phone = sharedPref.getString("SAVE_STATE_PHONE", "");

        etName.setText(name);
        etEmail.setText(email);
        etPhone.setText(phone);
    }
}
