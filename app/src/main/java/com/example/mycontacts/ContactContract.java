package com.example.mycontacts;

import android.provider.BaseColumns;

/**
 * Created by James Ooi on 26/7/2017.
 */

public class ContactContract {

    private ContactContract() {}

    public static class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contact";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE = "phone";

        //no need to define ID (ady got _ID in BaeColumns
    }
}
