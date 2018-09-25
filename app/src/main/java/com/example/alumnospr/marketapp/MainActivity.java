package com.example.alumnospr.marketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 *CLASE PRINCIPAL CORRESPONDIENTE AL ACTIVITY PRINCIPAL
 * @author Bastian Vidal
 * @version 19-09-2018
 */
public class MainActivity extends AppCompatActivity {

    //DECLARACION DE VARIABLES
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INSTANCIAR OBJETOS DE XML A JAVA
        lista = findViewById(R.id.opciones);

        //MÉTODO QUE NOS PERMITE ACCEDER A UN ACTIVITY SEGUN LA SELECCIÓN HECHA
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //SE EVALÚA QUE LA SELECCIÓN NO FUESE NULA
                if(position != -1) {
                    //En caso de ser cualquier opcion, excepto por el carrito, envía al Activity2
                    if (position != 2) {
                        Intent intento = new Intent(MainActivity.this, Activity2.class);
                        intento.putExtra("posicion", position);
                        startActivity(intento);

                    } else {//Caso contratio envía al ActivityStore que muestra el carrito
                        Intent intento = new Intent(MainActivity.this, ActivityStore.class);
                        startActivity(intento);
                    }//Fin If
                }//Fin If
            }//Fin onItemClick()
            }//Fin setOnItem..
        );
    }//Fin onCreate()
}//Fin Class

/**
 * Respuestas de la evaluación
 * 1.- Fortalezas y debilidades durante la evaluación
 * Fortalezas: Dado que cuento con experiencia adquirida anteriormente no fue complicado manejarse en la lógica de programación en JAVA
 * Debilidades: Debido a las carácteristicas del proyecto tuve que investigar cosas no vistas anteriormente para lograr el correcto
 * funcionamiento de la app.
 *
 * 2.- Observaciónes sobre la realización de la evaluación
 * La evaluación fue escencial para conocer las funcionalidades del IDE Android Studio y la versatilidad de las APPs android,
 * fue gran ayuda para una introducción a la programación de aplicaciones moviles.
 *
 * Bastián Ignacio Vidal Torres
 * Aplicaciónes Móviles TIDS08/641
 * Analista Programador
 * Sede Pérez Rosales
 * 24/09/2018
 */
