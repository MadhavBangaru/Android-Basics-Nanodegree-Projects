package com.example.madhavbangaru.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.madhavbangaru.inventoryapp.data.ProductContract;

/**
 * Created by Madhav Bangaru on 07-07-2018.
 */

public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    FloatingActionButton fab;
    ListView listView;
    private static final int PRODUCT_LOADER = 0;
    InventoryCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), EditorActivity.class);
                startActivity(in);
            }
        });

        listView = findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);
        adapter = new InventoryCursorAdapter(this, null);
        listView.setAdapter(adapter);
        getLoaderManager().initLoader(PRODUCT_LOADER, null, this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent in = new Intent(getApplicationContext(), EditorActivity.class);
                Uri currentUri = ContentUris.withAppendedId(ProductContract.NewEntry.CONTENT_URI, id);
                in.setData(currentUri);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all :
                getContentResolver().delete(ProductContract.NewEntry.CONTENT_URI, null, null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                ProductContract.NewEntry._ID,
                ProductContract.NewEntry.COLUMN_PRODUCT,
                ProductContract.NewEntry.COLUMN_PRICE,
                ProductContract.NewEntry.COLUMN_QUANTITY};
        return new CursorLoader(this,
                ProductContract.NewEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}

