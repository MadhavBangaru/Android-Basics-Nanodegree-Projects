package com.example.madhavbangaru.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Madhav Bangaru on 07-07-2018.
 */

public class ProductProvider extends ContentProvider {
    public static final int PRODUCTS = 200;
    public static final int PRODUCT_ID = 201;

    ProductDbHelper mProductDbHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(ProductContract.CONTENT_AUTHORITY, ProductContract.PATH_PRODUCTS, PRODUCTS);
        sUriMatcher.addURI(ProductContract.CONTENT_AUTHORITY, ProductContract.PATH_PRODUCTS + "/#", PRODUCT_ID);
    }

    @Override
    public boolean onCreate() {
        mProductDbHelper = new ProductDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mProductDbHelper.getReadableDatabase();
        Cursor cursor;
        switch (sUriMatcher.match(uri)){
            case PRODUCTS :
                cursor = db.query(ProductContract.NewEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PRODUCT_ID :
                selection = ProductContract.NewEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(ProductContract.NewEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default :
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)){
            case PRODUCTS :
                return ProductContract.NewEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID :
                return ProductContract.NewEntry.CONTENT_ITEM_TYPE;
            default :
                throw new IllegalStateException("Unknown URI");
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mProductDbHelper.getWritableDatabase();
        // Number of rows which will be deleted
        int numRowsDeleted;
        switch (sUriMatcher.match(uri)){
            case PRODUCTS :
                numRowsDeleted = db.delete(ProductContract.NewEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PRODUCT_ID :
                selection = ProductContract.NewEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                numRowsDeleted = db.delete(ProductContract.NewEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default :
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if(numRowsDeleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (sUriMatcher.match(uri)){
            case PRODUCTS :
                return updateProduct(uri, values, selection, selectionArgs);
            case PRODUCT_ID :
                selection = ProductContract.NewEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateProduct(uri, values, selection, selectionArgs);
            default :
                throw new IllegalArgumentException("Update not supported");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (sUriMatcher.match(uri)){
            case PRODUCTS :
                return insertProduct(uri, values);
            default :
                throw new IllegalStateException("Insertion is not supported");
        }
    }

    private Uri insertProduct(Uri uri, ContentValues values){

        if(values.getAsString(ProductContract.NewEntry.COLUMN_PRODUCT) == null)
            throw new IllegalArgumentException("Product Requires a name");
        if(values.getAsString(ProductContract.NewEntry.COLUMN_SUPPLIER) == null)
            throw new IllegalArgumentException("Product Supplier Requires a name");
        String phoneNumber = values.getAsString(ProductContract.NewEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
        if(phoneNumber == null || phoneNumber.length() != 10)
            throw new IllegalArgumentException("Invalid Supplier Phone Number");
        Integer quantity = values.getAsInteger(ProductContract.NewEntry.COLUMN_QUANTITY);
        if(quantity != null && quantity <= 0)
            throw new IllegalArgumentException("Invalid number of products");
        Integer cost = values.getAsInteger(ProductContract.NewEntry.COLUMN_PRICE);
        if(cost != null && cost <= 0)
            throw new IllegalArgumentException("Invalid cost of product");

        SQLiteDatabase db = mProductDbHelper.getWritableDatabase();

        long id = db.insert(ProductContract.NewEntry.TABLE_NAME, null, values);
        if(id == -1){
            Log.v("insertProduct() method", "Insertion error");
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    private int updateProduct(Uri uri, ContentValues values, String selection, String[] selectionArgs){

        if(values.containsKey(ProductContract.NewEntry.COLUMN_PRODUCT)){
            if(values.getAsString(ProductContract.NewEntry.COLUMN_PRODUCT) == null)
                throw new IllegalArgumentException("Product Requires a name");
        }
        if(values.containsKey(ProductContract.NewEntry.COLUMN_SUPPLIER)){
            if(values.getAsString(ProductContract.NewEntry.COLUMN_SUPPLIER) == null)
                throw new IllegalArgumentException("Product Supplier Requires a name");
        }
        if(values.containsKey(ProductContract.NewEntry.COLUMN_SUPPLIER_PHONE_NUMBER)){
            String phoneNumber = values.getAsString(ProductContract.NewEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
            if(phoneNumber == null || phoneNumber.length() != 10)
                throw new IllegalArgumentException("Invalid Supplier Phone Number");
        }
        if(values.containsKey(ProductContract.NewEntry.COLUMN_QUANTITY)){
            Integer quantity = values.getAsInteger(ProductContract.NewEntry.COLUMN_QUANTITY);
            if(quantity != null && quantity <= 0)
                throw new IllegalArgumentException("Invalid number of products");
        }
        if(values.containsKey(ProductContract.NewEntry.COLUMN_PRICE)){
            Integer cost = values.getAsInteger(ProductContract.NewEntry.COLUMN_PRICE);
            if(cost != null && cost <= 0)
                throw new IllegalArgumentException("Invalid cost of product");
        }
        if(values.size() == 0){
            return 0;
        }

        SQLiteDatabase db = mProductDbHelper.getWritableDatabase();
        int rowsChanged = db.update(ProductContract.NewEntry.TABLE_NAME, values, selection, selectionArgs);
        if(rowsChanged != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsChanged;
    }
}



