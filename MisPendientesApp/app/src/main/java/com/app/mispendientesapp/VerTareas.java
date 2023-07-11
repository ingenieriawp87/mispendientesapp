package com.app.mispendientesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.app.mispendientesapp.db.DbHelper;
public class VerTareas extends AppCompatActivity {
    RecyclerView recyclerView;

    public static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        VerTareas.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tareas);

        recyclerView = findViewById(R.id.reciclerView);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DbHelper dbHelper = new DbHelper(VerTareas.this);

        //listaArrayTareas = new ArrayList<>();
        ListaTareasAdapter adapter = new ListaTareasAdapter(dbHelper.mostrarTareas());
        recyclerView.setAdapter(adapter);



        //ver tareas
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Acción al hacer clic en un elemento del RecyclerView
                // Aquí puedes obtener la tarea seleccionada y realizar las operaciones deseadas
                String tareaSeleccionada = dbHelper.mostrarTareas().get(position).getId();
                id = Integer.parseInt(tareaSeleccionada.replace("ID: ",""));
                //Cuadro de alerta
                AlertDialog.Builder builder = new AlertDialog.Builder(VerTareas.this);
                builder.setMessage(R.string.borrar_tarea)
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Acción al hacer clic en el botón "Iniciar"
                        // Aquí puedes realizar las operaciones necesarias para iniciar el juego
                        dbHelper.eliminarTareas(id, VerTareas.this);
                        finish();
                        // Crea un nuevo intent para iniciar la actividad VerTareas nuevamente
                        Intent intent = new Intent(VerTareas.this, VerTareas.class);
                        startActivity(intent);
                    }
                })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Acción al hacer clic en el botón "Cancelar"
                        // Aquí puedes realizar las operaciones necesarias si el usuario cancela el diálogo
                        Toast.makeText(VerTareas.this,R.string.la_tarea_no_fue_eliminada, Toast.LENGTH_SHORT).show();

                    }
                });
                builder.create().show();
            }
            @Override
            public void onLongClick(View view, int position) {
                // Acción al hacer clic largo en un elemento del RecyclerView
                // Aquí puedes obtener la tarea seleccionada y realizar las operaciones deseadas
                String tareaSeleccionada = dbHelper.mostrarTareas().get(position).getId()+" - "+dbHelper.mostrarTareas().get(position).getNombre();
                //Toast.makeText(VerTareas.this,"*** muestra un elemento click largo "+tareaSeleccionada, Toast.LENGTH_SHORT).show();
                // Realiza las operaciones deseadas con la tarea seleccionada
            }
        }));


    }

}