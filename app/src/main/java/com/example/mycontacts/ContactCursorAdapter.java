package com.example.mycontacts;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by James Ooi on 26/7/2017.
 */

public class ContactCursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public ContactCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName = (TextView)view.findViewById(R.id.item_name);
        TextView tvPhone = (TextView)view.findViewById(R.id.item_phone);
        String name = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_NAME));
        String phone = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_PHONE));
        tvName.setText(name);
        tvPhone.setText(phone);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item, parent, false);
    }
}
