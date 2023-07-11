package com.app.mispendientesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.mispendientesapp.db.DbHelper;

public class AgregarTareas extends AppCompatActivity {

    Tarea tarea = new Tarea();

    public static int estadoTarea;

    public static String nombreTarea,detalleTarea;

    public static int getEstadoTarea() {
        return estadoTarea;
    }

    public static void setEstadoTarea(int estadoTarea) {
        AgregarTareas.estadoTarea = estadoTarea;
    }

    public static String getNombreTarea() {
        return nombreTarea;
    }

    public static void setNombreTarea(String nombreTarea) {
        AgregarTareas.nombreTarea = nombreTarea;
    }

    public static String getDetalleTarea() {
        return detalleTarea;
    }

    public static void setDetalleTarea(String detalleTarea) {
        AgregarTareas.detalleTarea = detalleTarea;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tareas);

        EditText editTextTextNombretarea=(EditText) findViewById(R.id.editTextTextNombretarea);
        EditText editTextTextDetalleTarea=(EditText) findViewById(R.id.editTextTextDetalleTarea);
        Button button_agregar_tareas = (Button) findViewById(R.id.button_agregar_tarea);
        button_agregar_tareas.setOnClickListener(new View.OnClickListener() {
                DbHelper dbHelper = new DbHelper(AgregarTareas.this);
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            @Override
            public void onClick(View view) {
                if(editTextTextNombretarea.getText().toString().equals("") || editTextTextDetalleTarea.getText().toString().equals("")){
                    Toast.makeText(AgregarTareas.this,getString(R.string.verifique_los_datos_para_ingresar_la_tarea),Toast.LENGTH_SHORT).show();
                }else{

                    setNombreTarea(editTextTextNombretarea.getText().toString().toUpperCase());
                    setDetalleTarea(editTextTextDetalleTarea.getText().toString());
                    setEstadoTarea(1);

                    dbHelper.insertDb(sqLiteDatabase);
                    Toast.makeText(AgregarTareas.this,getString(R.string.la_tarea_se_agrego_satisfactoriamente), Toast.LENGTH_SHORT).show();
                    editTextTextNombretarea.setText(null);
                    editTextTextDetalleTarea.setText(null);

                }

            }
        });


    }
}