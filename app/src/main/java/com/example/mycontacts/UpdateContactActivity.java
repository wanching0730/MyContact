package com.example.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class UpdateContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final Contact contact = (Contact)intent.getSerializableExtra(ViewContactActivity.EXTRA_CONTACT);

        final EditText etName = (EditText)findViewById(R.id.input_name);
        final EditText etEmail = (EditText)findViewById(R.id.input_email);
        final EditText etPhone = (EditText)findViewById(R.id.input_phone);

        etName.setText(contact.getName());
        etEmail.setText(contact.getEmail());
        etPhone.setText(contact.getPhone());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.setName(etName.getText().toString());
                contact.setEmail(etEmail.getText().toString());
                contact.setPhone(etPhone.getText().toString());

                ContactDbQueries dbq = new ContactDbQueries(new ContactDbHelper(getApplicationContext()));
                dbq.update(contact);

                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}
