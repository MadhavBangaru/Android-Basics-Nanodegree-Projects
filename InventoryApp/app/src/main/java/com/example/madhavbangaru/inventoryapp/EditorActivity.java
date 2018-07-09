package com.example.madhavbangaru.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.madhavbangaru.inventoryapp.data.ProductContract;

/**
 * Created by Madhav Bangaru on 07-07-2018.
 */

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener  {

    EditText product, productPrice, supplierName, supplierNumber, productQuantity;
    ImageView increaseQuantity, decreaseQuantity;
    private static final int EXISTING_PRODUCT_LOADER = 0;
    private boolean ProductChanged = false;

    Uri currentUri;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ProductChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent in = getIntent();
        currentUri = in.getData();

        if(currentUri == null){
            setTitle("Add a Product");
            invalidateOptionsMenu();
        }
        else{
            setTitle("Edit Product");
            getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
        }

        product = findViewById(R.id.in_product_name);
        productPrice = findViewById(R.id.in_product_cost);
        supplierName = findViewById(R.id.in_supplier_name);
        supplierNumber = findViewById(R.id.in_supplier_number);
        productQuantity = findViewById(R.id.in_quantity);
        increaseQuantity = findViewById(R.id.quantity_plus);
        decreaseQuantity = findViewById(R.id.quantity_minus);
        increaseQuantity.setOnClickListener(this);
        decreaseQuantity.setOnClickListener(this);

        product.setOnTouchListener(mTouchListener);
        productPrice.setOnTouchListener(mTouchListener);
        supplierNumber.setOnTouchListener(mTouchListener);
        supplierNumber.setOnTouchListener(mTouchListener);
        productQuantity.setOnTouchListener(mTouchListener);
        productQuantity.setText("0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if(currentUri == null){
            MenuItem menuItem = menu.findItem(R.id.delelte_product);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertProduct();
                return true;
            case android.R.id.home:
                if(!ProductChanged){
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
                DialogInterface.OnClickListener discardButton = new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };
                showMyDialog(discardButton);
                return true;
            case R.id.contact_supplier :
                String supplier_number = supplierNumber.getText().toString().trim();
                Intent in = new Intent(Intent.ACTION_DIAL);
                in.setData(Uri.parse("tel:" + supplier_number));
                if(in.resolveActivity(getPackageManager()) != null)
                    startActivity(in);
                return true;
            case R.id.delelte_product :
                showConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showConfirmationDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirm_dialog_message);
        builder.setPositiveButton(R.string.confirm_dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.confirm_dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null)
                    dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {
        if(currentUri != null){
            int rowsDeleted = getContentResolver().delete(currentUri, null, null);
            if(rowsDeleted == 0){
                Toast.makeText(this, R.string.delete_failed_message, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, R.string.delete_success_message, Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        if(!ProductChanged){
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButton =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                };
        showMyDialog(discardButton);
    }

    private void showMyDialog(DialogInterface.OnClickListener discardButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_dialog_message);
        builder.setPositiveButton(R.string.unsaved_positive_response, discardButton);
        builder.setNegativeButton(R.string.unsaved_negative_response, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null)
                    dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void insertProduct() {
        ContentValues values = new ContentValues();
        String mproduct, mSupplierName, mSupplierNumber;
        int mproductPrice, mProductQuantity;
        try {
            mproduct = product.getText().toString().trim();
            mSupplierName = supplierName.getText().toString().trim();
            mSupplierNumber = supplierNumber.getText().toString().trim();
            mproductPrice = Integer.parseInt(productPrice.getText().toString().trim());
            mProductQuantity = Integer.parseInt(productQuantity.getText().toString().trim());
        }
        catch(Exception e){
            Toast.makeText(this, R.string.invalid_values_message, Toast.LENGTH_SHORT).show();
            return;
        }

        if(mSupplierNumber.length() != 10){
            Toast.makeText(this, R.string.invalid_number_message, Toast.LENGTH_SHORT).show();
            return;
        }

        if(mproductPrice < 0 || mProductQuantity == 0){
            Toast.makeText(this, R.string.invalid_message, Toast.LENGTH_SHORT).show();
            return;
        }

        values.put(ProductContract.NewEntry.COLUMN_PRODUCT, mproduct);
        values.put(ProductContract.NewEntry.COLUMN_SUPPLIER, mSupplierName);
        values.put(ProductContract.NewEntry.COLUMN_SUPPLIER_PHONE_NUMBER, mSupplierNumber);
        values.put(ProductContract.NewEntry.COLUMN_PRICE, mproductPrice);
        values.put(ProductContract.NewEntry.COLUMN_QUANTITY, mProductQuantity);
        if(currentUri == null){
            Uri newUri = getContentResolver().insert(ProductContract.NewEntry.CONTENT_URI, values);
            if(newUri == null){
                Toast.makeText(this, R.string.insert_product_error, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, R.string.insert_product_success, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else{
            int rowsAffected = getContentResolver().update(currentUri, values, null, null);
            if(rowsAffected == 0) {
                Toast.makeText(this, R.string.update_product_error, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, R.string.update_product_success, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ProductContract.NewEntry._ID,
                ProductContract.NewEntry.COLUMN_PRODUCT,
                ProductContract.NewEntry.COLUMN_PRICE,
                ProductContract.NewEntry.COLUMN_SUPPLIER,
                ProductContract.NewEntry.COLUMN_SUPPLIER,
                ProductContract.NewEntry.COLUMN_SUPPLIER_PHONE_NUMBER,
                ProductContract.NewEntry.COLUMN_QUANTITY};
        return new CursorLoader(this,
                currentUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor == null || cursor.getCount() < 1)
            return;
        if(cursor.moveToFirst()){
            product.setText(cursor.getString(cursor.getColumnIndex(ProductContract.NewEntry.COLUMN_PRODUCT)));
            supplierName.setText(cursor.getString(cursor.getColumnIndex(ProductContract.NewEntry.COLUMN_SUPPLIER)));
            supplierNumber.setText(cursor.getString(cursor.getColumnIndex(ProductContract.NewEntry.COLUMN_SUPPLIER_PHONE_NUMBER)));
            productPrice.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(ProductContract.NewEntry.COLUMN_PRICE))));
            productQuantity.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(ProductContract.NewEntry.COLUMN_QUANTITY))));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        product.setText("");
        supplierName.setText("");
        supplierNumber.setText("");
        productPrice.setText("");
        productQuantity.setText("");
    }

    @Override
    public void onClick(View v) {
        int quantity = Integer.valueOf(productQuantity.getText().toString().trim());
        switch (v.getId()){
            case R.id.quantity_plus :
                productQuantity.setText(String.valueOf(quantity + 1));
                break;
            case R.id.quantity_minus :
                if(quantity > 0)
                    productQuantity.setText(String.valueOf(quantity - 1));
                else
                    productQuantity.setText("0");
        }
    }
}
