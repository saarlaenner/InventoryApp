package com.example.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.inventoryapp.data.InventoryContract;
import com.example.android.inventoryapp.data.InventoryDBHelper;
import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

public class MainActivity extends AppCompatActivity {

    private InventoryDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button to start EditorActivity
        Button btn = (Button) findViewById(R.id.add_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new InventoryDBHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayInventoryInfo();
    }

    private void displayInventoryInfo() {
        // instantiate database...
        InventoryDBHelper mDbHelper = new InventoryDBHelper(this);

        // ...and get read-access
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {InventoryContract.InventoryEntry._ID,
                InventoryEntry.COLUMN_INVENTORY_PRODUCTNAME,
                InventoryEntry.COLUMN_INVENTORY_PRICE,
                InventoryEntry.COLUMN_INVENTORY_QUANTITY,
                InventoryEntry.COLUMN_INVENTORY_SUPPLIERNAME,
                InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONENUMBER};

        Cursor cursor = db.query(
                InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayView = (TextView) findViewById(R.id.text_view_inventory);

        try {
            // Display the whole database-content
            displayView.append(InventoryEntry._ID + " - "
                    + InventoryEntry.COLUMN_INVENTORY_PRODUCTNAME + " - "
                    + InventoryEntry.COLUMN_INVENTORY_PRICE + " - "
                    + InventoryEntry.COLUMN_INVENTORY_QUANTITY + " - "
                    + InventoryEntry.COLUMN_INVENTORY_SUPPLIERNAME + " - "
                    + InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONENUMBER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCTNAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_SUPPLIERNAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONENUMBER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " + currentProductName + " - " + currentPrice + " - " + currentQuantity + " - " + currentSupplierName + " - " + currentSupplierPhoneNumber));
            }
        } finally {
            // Mandatory close the cursor
            cursor.close();
        }
    }
}