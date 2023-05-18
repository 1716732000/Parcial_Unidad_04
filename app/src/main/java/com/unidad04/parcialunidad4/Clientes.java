package com.unidad04.parcialunidad4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Clientes extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtDireccion, txtCiudad;
    String Nombre, Apellidos, Direccion, Ciudad, NombreA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        txtNombre = findViewById(R.id.edtNombre);
        txtApellidos = findViewById(R.id.edtApellidos);
        txtDireccion = findViewById(R.id.edtDireccion);
        txtCiudad = findViewById(R.id.edtCiudad);
    }

    public void Agregar(View view){
        AdminSQLiteOpenHelper Admin = new AdminSQLiteOpenHelper(this, "Cerrito", null, 1);
        SQLiteDatabase BaseDatos = Admin.getReadableDatabase();

        Nombre = txtNombre.getText().toString();
        Apellidos = txtApellidos.getText().toString();
        Direccion = txtDireccion.getText().toString();
        Ciudad = txtCiudad.getText().toString();

        try{
            if(!Nombre.isEmpty() && !Apellidos.isEmpty() && !Direccion.isEmpty() && !Ciudad.isEmpty()){
                ContentValues registro = new ContentValues();
                registro.put("sNombreCliente", Nombre);
                registro.put("sApellidosCliente", Apellidos);
                registro.put("sDireccionCliente", Direccion);
                registro.put("sCiudadCliente", Ciudad);

                BaseDatos.insert("MD_Clientes", null, registro);
                BaseDatos.close();

                //LimpiarCajas();
                txtNombre.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                txtCiudad.setText("");

            }else{
                Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Toast.makeText(this, "ERROR AL ADICIONAR", Toast.LENGTH_LONG).show();
        }
    }

    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Cerrito", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Nombre = txtNombre.getText().toString();

        if(!Nombre.isEmpty()){
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT sApellidosCliente, sDireccionCliente, sCiudadCliente FROM MD_Clientes WHERE sNombreCliente =" + Nombre, null);
            if(fila.moveToFirst()){
                txtApellidos.setText(fila.getString(0));
                txtDireccion.setText(fila.getString(1));
                txtCiudad.setText(fila.getString(2));
                BaseDatos.close();
            }else{
                Toast.makeText(this, "NO EXISTE", Toast.LENGTH_LONG).show();
                BaseDatos.close();

            }

        }else{
            Toast.makeText(this, "REGISTRO NO EXISTE O CAMPO VACIO", Toast.LENGTH_LONG).show();
        }
    }

    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Cerrito", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Nombre = txtNombre.getText().toString();
        NombreA = Nombre;
        Apellidos = txtApellidos.getText().toString();
        Direccion = txtDireccion.getText().toString();
        Ciudad = txtCiudad.getText().toString();

        if(!Nombre.isEmpty() && !Apellidos.isEmpty() && !Direccion.isEmpty() && !Ciudad.isEmpty()){
            ContentValues registro = new ContentValues();
                registro.put("sNombreCliente", Nombre);
                registro.put("sApellidosCliente", Apellidos);
                registro.put("sDireccionCliente", Direccion);
                registro.put("sCiudadCliente", Ciudad);

            int cantidad = BaseDatos.update("MD_Clientes", registro, "sNombreCliente=" + NombreA, null);
            BaseDatos.close();

            if(cantidad == 1)
            {
                Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
               //LimpiarCajas();
                txtNombre.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                txtCiudad.setText("");
            }
            else
            {
                Toast.makeText(this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
            }

            //LimpiarCajas();
            txtNombre.setText("");
            txtApellidos.setText("");
            txtDireccion.setText("");
            txtCiudad.setText("");
            Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "UN CAMPO VACIO", Toast.LENGTH_LONG).show();
        }

    }

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Cerrito", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Nombre = txtNombre.getText().toString();

        try{
            if(!Nombre.isEmpty()){
                int Cantidad = BaseDatos.delete("MD_Clientes", "sNombreCliente=" + Nombre, null);
                BaseDatos.close();

                txtNombre.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                txtCiudad.setText("");

                if(Cantidad == 1){
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(this, "NOMBRE ESTA VACIO", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Toast.makeText(this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
        }
    }

    public void Retornar(View view){
        finish();
    }

    public void LimpiarCajas(View view){
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDireccion.setText("");
        txtCiudad.setText("");
    }
}