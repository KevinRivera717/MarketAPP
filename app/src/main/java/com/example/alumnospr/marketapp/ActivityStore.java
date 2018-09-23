package com.example.alumnospr.marketapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityStore extends AppCompatActivity {
    Cursor cursor;
    ListView lista;
    TextView total;
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
        try {
            ejecutarQuery();
            cursor.moveToFirst();
            lista = findViewById(R.id.lista);
            total = findViewById(R.id.txt_total);
            int acum = 0;
            ArrayList<String> producto = new ArrayList<String>();
            //producto.add(cursor.getString(0));

            do {
                int cant = Integer.parseInt(cursor.getString(1));
                int precio = Integer.parseInt(cursor.getString(2));
                producto.add(cursor.getString(0) + " Cant:" + cant + " P.Unidad:$" + precio + " Subtotal:$" + (precio * cant));
                acum = acum + (precio * cant);
            } while (cursor.moveToNext());

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, producto);
            lista.setAdapter(adaptador);
            total.setText(total.getText() + "$" + acum);

        }catch(Exception e ){
            Toast.makeText(this,"NO HAY ARTICULOS EN EL CARRITO",Toast.LENGTH_LONG).show();
        }

    }

    public void borrarPedido(View view){
        try {
            HelperBD helper = new HelperBD(this);
            SQLiteDatabase db = helper.getReadableDatabase();

            db.execSQL(EstructuraBD.SQL_DELETE_REGISTERS);
            onCreate(null);
        }catch (Exception e){
            Toast.makeText(this,"SE HA VACIADO EL CARRITO",Toast.LENGTH_LONG).show();
            Intent intento = new Intent(this,MainActivity.class);
            startActivity(intento);
        }
    }
}
