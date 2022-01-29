package com.example.corona_safe.database;

import android.provider.BaseColumns;

public final class UsersMaster {
    private UsersMaster(){}

    public static class Users implements BaseColumns{
        public static final String TABLE_NAME = "users1";
        public static final String COLUMN_NAME_PROVINCE = "province";
        public static final String COLUMN_NAME_CONFIRMED = "confirmed";
        public static final String COLUMN_NAME_DEATHS = "deaths";
        public static final String COLUMN_NAME_RECOVERED = "recovered";
        public static final String COLUMN_NAME_DATE = "date";
    }
}
