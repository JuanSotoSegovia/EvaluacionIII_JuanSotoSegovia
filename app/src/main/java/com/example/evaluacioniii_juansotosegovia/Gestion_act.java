package com.example.evaluacioniii_juansotosegovia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import obj_class.pizzas;

public class Gestion_act extends AppCompatActivity {

    //creamos objetos para almacenar los objetos de la vista
    private EditText nombre, precio, locazion;
    private Button añadir;

    //creamos objetos de firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        //almacenamos los objetos de la vista en los objetos creado al comienzo
        nombre = (EditText)findViewById(R.id.edt_nombre);
        precio = (EditText)findViewById(R.id.edt_precio);
        locazion = (EditText)findViewById(R.id.edt_localizacion);

        añadir = (Button)findViewById(R.id.btt_añadir);

        //llamamos al metodo que inicializa y obtiene la base de datos
        obtenerBaseDatos();

        //añadimos datos pasando el metodo setOnClickListener a nuestro boton
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creamos un objeto de la clase pizza el cual alamcenara los datos ingresados por el usuario
                pizzas p = new pizzas();

                //seteamos las variables del objeto
                //pasamos una ID random
                p.setId(UUID.randomUUID().toString());
                //pasamos los datos ingresados por el suario a las variables del objeto
                p.setNombre(nombre.getText().toString());
                p.setPrecio(precio.getText().toString());
                p.setLocazion(locazion.getText().toString());

                //llamamos al objeto de referencia de firebase y seleccionamos la tabla
                //para ingresar lso datos ingresados por el ciente
                databaseReference.child("Pizzas").child(p.getId()).setValue(p);

                //limpiamos los campos con el metodo que hemos creado
                limpiarCampos();

                //mostramos mensaje emergente Toasr
                Toast.makeText(getBaseContext(),"Piazza Agregada.",Toast.LENGTH_SHORT).show();

            }
        });
    }

    //metodo para inicializar y obtener la base de datos
    public void obtenerBaseDatos(){
        FirebaseApp.initializeApp(getBaseContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    //metodo para limpiar los campos
    public void limpiarCampos(){
        nombre.setText("");
        precio.setText("");
        locazion.setText("");
    }


}