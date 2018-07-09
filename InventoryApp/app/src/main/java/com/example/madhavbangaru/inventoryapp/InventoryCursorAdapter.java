package com.example.madhavbangaru.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhavbangaru.inventoryapp.data.ProductContract;

/**
 * Created by Madhav Bangaru on 07-07-2018.
 */

public class InventoryCursorAdapter extends CursorAdapter {

    InventoryCursorAdapter(Context context, Cursor c){
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false);
        }

        @Override
        public void bindView(final View view, final Context context, final Cursor cursor) {

            TextView productView = view.findViewById(R.id.product_name);

            TextView productPriceView = view.findViewById(R.id.price);

            TextView productQuantityView = view.findViewById(R.id.quantity);

            String product = cursor.getString(cursor.getColumnIndexOrThrow(ProductContract.NewEntry.COLUMN_PRODUCT));

            int productPrice = cursor.getInt(cursor.getColumnIndexOrThrow(ProductContract.NewEntry.COLUMN_PRICE));

            int productQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(ProductContract.NewEntry.COLUMN_QUANTITY));

            ImageView button = view.findViewById(R.id.button_sell);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(ProductContract.NewEntry._ID));
                    int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(ProductContract.NewEntry.COLUMN_QUANTITY));
                    Uri currentUri = ContentUris.withAppendedId(ProductContract.NewEntry.CONTENT_URI, id);

                    if(quantity > 1){
                        ContentValues values = new ContentValues();
                        values.put(ProductContract.NewEntry.COLUMN_QUANTITY, quantity - 1);
                        context.getContentResolver().update(currentUri, values, null, null);
                    }
                    else{
                        context.getContentResolver().delete(currentUri, null, null);
                        Toast.makeText(context, R.string.last_product_message, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            productView.setText(product);
            productPriceView.setText(String.format("Price : Rs. %s", String.valueOf(productPrice)));
            productQuantityView.setText(String.format("Quanitiy : %s", String.valueOf(productQuantity)));
        }
    }

