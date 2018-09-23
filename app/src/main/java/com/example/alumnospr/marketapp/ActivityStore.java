package com.example.alumnospr.marketapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityStore extends AppCompatActivity {
    Cursor cursor;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        definirLista();
    }

    public void ejecutarQuery(){
        HelperBD helper = new HelperBD(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                EstructuraBD.COLUMNA2,
                EstructuraBD.COLUMNA4,
                EstructuraBD.COLUMNA5
        };

        // Filter results WHERE "title" = 'My Title'
        // String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
        //String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        // String sortOrder =
        //       FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

            cursor = db.query(
                EstructuraBD.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );
    }

    public void definirLista(){
        ejecutarQuery();
        cursor.moveToFirst();
        lista = findViewById(R.id.lista);
        ArrayList<String>producto = new ArrayList<String>();
        producto.add(cursor.getString(0));

        while(cursor.moveToNext()){
            producto.add(cursor.getString(0));
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, producto);
        lista.setAdapter(adaptador);
    }
}
