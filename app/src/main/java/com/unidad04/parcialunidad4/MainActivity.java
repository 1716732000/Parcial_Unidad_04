package com.unidad04.parcialunidad4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void MostrarClientes(View view){
        Intent MC = new Intent(this, Clientes.class);
        startActivity(MC);
    }

    public void MostrarVehiculos(View view){
        Intent MV = new Intent(this, Vehiculos.class);
        startActivity(MV);
    }

    public void MostrarClienteVehiculo(View view){
        Intent MCV = new Intent(this, ClienteVehiculo.class);
        startActivity(MCV);
    }
}