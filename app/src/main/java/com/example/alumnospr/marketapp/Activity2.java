package com.example.alumnospr.marketapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//CLASE ENCARGADA DE MOSTRAR LOS PRODUCTOS DE CADA TIPO, VISUALIZADOS EN ACTIVITY2
public class Activity2 extends AppCompatActivity {
    //DECLARACIÓN DE VARIABLES
     ListView listaCervezas;
     ListView listaPizzas;

    public static int pos;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        //INSTANCIACIÓN DE XML EN JAV;
        pos = getIntent().getIntExtra("posicion",-1);
        definirLista(pos);
    }



    public void definirLista(int pos) {
        //SE EVALÚA EL ITEM SELECCIONADO EN EL ACTIVITY ANTERIOR PARA LISTAR LOS DATOS CORRESPONDIENTES
            if (pos == 0) {//EN CASO DE CERVEZAS
                //SE VISUALIZAN TODOS LOS PRODUCTOS CORRESPONDIENTES AL TIPO SELECCIONADO MEDIANTE UNA LISTA,
                //DE DECLARA EL ARREGLO MEDIANTE UN ADAPTADOR
                listaCervezas = findViewById(R.id.lista);

                ArrayAdapter<Producto> adaptador = new ArrayAdapter<Producto>(this, android.R.layout.simple_list_item_1, Producto.cervezas);
                listaCervezas.setAdapter(adaptador);
                listaCervezas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position != -1) {//SE REALIZA UN INTENT CON EL ITEM SELECCIONADO DE LA LISTA
                            Intent intento = new Intent(Activity2.this, Activity3.class);
                            //SE ENVIAN LOS PARAMETROS NECESARIOS PARA VISUALIZAR EL ITEM EN EL SGTE ACTIVITY
                            intento.putExtra("posicion", String.valueOf(position));
                            intento.putExtra("producto", "cerveza");
                            onPause();
                            startActivity(intento);
                        }
                    }
                });
            }

            if (pos == 1) {//EN CASO DE PIZZAS
                //SE VISUALIZAN TODOS LOS PRODUCTOS CORRESPONDIENTES AL TIPO SELECCIONADO MEDIANTE UNA LISTA,
                //DE DECLARA EL ARREGLO MEDIANTE UN ADAPTADOR
                listaPizzas = findViewById(R.id.lista);

                ArrayAdapter<Producto> adaptador = new ArrayAdapter<Producto>(this, android.R.layout.simple_list_item_1, Producto.pizzas);
                listaPizzas.setAdapter(adaptador);
                listaPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i != -1) {//SE REALIZA UN INTENT CON EL ITEM SELECCIONADO DE LA LISTA
                            Intent intento = new Intent(Activity2.this, Activity3.class);
                            //SE ENVIAN LOS PARAMETROS NECESARIOS PARA VISUALIZAR EL ITEM EN EL SGTE ACTIVITY
                            intento.putExtra("posicion", String.valueOf(i));
                            intento.putExtra("producto", "pizza");
                            onPause();
                            startActivity(intento);
                        }
                    }
                });
            }
    }

   /* public void onSaveInstanceState(Bundle estado){
        estado.putInt("posicion",pos);
        super.onSaveInstanceState(estado);
    }

    public void onRestoreInstanceState(Bundle estado){
        super.onRestoreInstanceState(estado);
        int pos = estado.getInt("posicion");
        definirLista(pos);
    }*/
       public void onPause(){
            super.onPause();
            SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = datos.edit();
            editor.putInt("posicion", pos);
            editor.apply();
        }

        public void onResume () {
            super.onResume();
            //OBTENEMOS LOS DATOS ENVIADOS A TRAVÉS DEL INTENT DE LA ACTIVITY ANTERIOR
           // Bundle bundle = getIntent().getExtras();
           // String item = bundle.get("posicion").toString();
            //pos = Integer.parseInt(item);

            if(pos == -1){
                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
                pos = datos.getInt("posicion", 0);
                definirLista(pos);
            }else{
                definirLista(pos);
            }

        }
    }

