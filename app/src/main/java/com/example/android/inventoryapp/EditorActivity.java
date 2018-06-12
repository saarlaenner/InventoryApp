package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;
import com.example.android.inventoryapp.data.InventoryDBHelper;
import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Created by Daniel on 12.06.2018.
 */

public class EditorActivity extends AppCompatActivity {

    private InventoryDBHelper mDbHelper;

    private EditText mProductNameEditText;
    private EditText mPriceEditText;
    private EditText mQuantitiyText;
    private EditText mSupplierNameEditText;
    private EditText mSupplierPhoneNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mProductNameEditText = (EditText) findViewById(R.id.edit_product_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_product_price);
        mSupplierNameEditText = (EditText) findViewById(R.id.edit_supplier_name);
        mSupplierPhoneNumberText = (EditText) findViewById(R.id.edit_supplier_phone_number);
        mQuantitiyText = (EditText) findViewById(R.id.edit_quantity);

        mDbHelper = new InventoryDBHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menu for Options (save and delete)
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                insertInventory();
                NavUtils.navigateUpFromSameTask(this);
                return true;
            // Respond to a click on the "Up" button
            case android.R.id.home:
                // Navigate back to parent activity
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertInventory() {

        Integer inventoryPrice = 0;
        Integer inventoryQuantity = 0;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //get the Strings from input fields
        String inventoryProductName = mProductNameEditText.getText().toString().trim();
        String inventorySupplierName = mSupplierNameEditText.getText().toString().trim();
        String inventorySupplierPhoneNumber = mSupplierPhoneNumberText.getText().toString().trim();

        mPriceEditText.getText().toString();
        try {
            inventoryPrice = Integer.parseInt(String.valueOf(mPriceEditText));
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }

        mQuantitiyText.getText().toString();
        try {
            inventoryQuantity = Integer.parseInt(String.valueOf(mQuantitiyText));
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }

        //add all input to values
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_INVENTORY_PRODUCTNAME, inventoryProductName);
        values.put(InventoryEntry.COLUMN_INVENTORY_PRICE, inventoryPrice);
        values.put(InventoryEntry.COLUMN_INVENTORY_QUANTITY, inventoryQuantity);
        values.put(InventoryEntry.COLUMN_INVENTORY_SUPPLIERNAME, inventorySupplierName);
        values.put(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONENUMBER, inventorySupplierPhoneNumber);

        //try to insert into database
        try {
            long newRowId = db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);
            Toast.makeText(this, "Inventory saved with id " + newRowId, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("", e.getMessage());
            Toast.makeText(this, "Error with saving inventory", Toast.LENGTH_SHORT).show();
        }
    }
}