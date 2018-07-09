package com.example.madhavbangaru.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Madhav Bangaru on 07-07-2018.
 */

public class ProductDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "inventory.db";

    public static final int DATABASE_VERSION = 1;

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + ProductContract.NewEntry.TABLE_NAME + " ("
                + ProductContract.NewEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductContract.NewEntry.COLUMN_PRODUCT + " TEXT, "
                + ProductContract.NewEntry.COLUMN_PRICE + " INTEGER, "
                + ProductContract.NewEntry.COLUMN_QUANTITY + " INTEGER, "
                + ProductContract.NewEntry.COLUMN_SUPPLIER + " TEXT, "
                + ProductContract.NewEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT);";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

