package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by Daniel on 12.06.2018.
 */

public final class InventoryContract {

    private InventoryContract() {
    }

    public static final class InventoryEntry implements BaseColumns {

        public final static String DATABASE_NAME = "inventory.db";
        public static final int DATABASE_VERSION = 1;

        public final static String TABLE_NAME = "inventory";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_INVENTORY_PRODUCTNAME = "product_name";
        public final static String COLUMN_INVENTORY_SUPPLIERNAME = "supplier_name";
        public final static String COLUMN_INVENTORY_SUPPLIER_PHONENUMBER = "supplier_phonenumber";
        public final static String COLUMN_INVENTORY_PRICE = "price";
        public final static String COLUMN_INVENTORY_QUANTITY = "quantity";

    }
}
