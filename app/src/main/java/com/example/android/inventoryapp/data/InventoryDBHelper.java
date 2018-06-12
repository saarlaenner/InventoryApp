package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Created by Daniel on 12.06.2018.
 */

public class InventoryDBHelper extends SQLiteOpenHelper {

    //obligatory instantiation
    public InventoryDBHelper(Context context) {
        super(context, InventoryEntry.DATABASE_NAME, null, InventoryEntry.DATABASE_VERSION);
    }

    //create database initially
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + InventoryEntry.TABLE_NAME +
                " (" + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                InventoryEntry.COLUMN_INVENTORY_PRODUCTNAME + " TEXT NOT NULL," +
                InventoryEntry.COLUMN_INVENTORY_PRICE + " INTEGER NOT NULL," +
                InventoryEntry.COLUMN_INVENTORY_QUANTITY + " INTEGER NOT NULL," +
                InventoryEntry.COLUMN_INVENTORY_SUPPLIERNAME + " TEXT NOT NULL," +
                InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONENUMBER + " TEXT NOT NULL)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + InventoryEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}