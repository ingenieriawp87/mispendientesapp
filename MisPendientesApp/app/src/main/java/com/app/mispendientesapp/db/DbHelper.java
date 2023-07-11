package com.app.mispendientesapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.app.mispendientesapp.AgregarTareas;
import com.app.mispendientesapp.R;
import com.app.mispendientesapp.Tarea;
import com.app.mispendientesapp.VerTareas;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    AgregarTareas agregarTareas = new AgregarTareas();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db.db";
    public static final String DATABASE_TABLE = "t_data";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+DATABASE_TABLE+" (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "detalle TEXT NOT NULL," +
                "estado INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void insertDb(SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre",agregarTareas.getNombreTarea());
        contentValues.put("detalle",agregarTareas.getDetalleTarea());
        contentValues.put("estado",agregarTareas.getEstadoTarea());
        sqLiteDatabase.insert("t_data",null,contentValues);
    }

    public ArrayList<Tarea> mostrarTareas(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+DATABASE_TABLE+" ORDER BY estado", null);
        ArrayList<Tarea> listaTareas=new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Tarea tarea1 = new Tarea();
                tarea1.setId("ID: "+cursor.getString(0));
                tarea1.setNombre("NOMBRE: "+cursor.getString(1));
                tarea1.setDetalle("DETALLE: "+cursor.getString(2));
                if(cursor.getString(3).equals("1")){
                    tarea1.setEstado("ESTADO: Activo");
                }else {
                    tarea1.setEstado("ESTADO: Inactivo");
                }
                listaTareas.add(tarea1);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listaTareas;
    }

    public void eliminarTareas(int idFilaEliminar, Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(idFilaEliminar) };
        int filasEliminadas = db.delete(DATABASE_TABLE, whereClause, whereArgs);

        if (filasEliminadas > 0) {
            // La fila se eliminó exitosamente
            Toast.makeText(context,R.string.la_tarea_si_fue_eliminada, Toast.LENGTH_SHORT).show();
        } else {
            // No se encontró la fila o no se pudo eliminar
            Toast.makeText(context,R.string.la_tarea_no_fue_eliminada, Toast.LENGTH_SHORT).show();

        }

        db.close();
    }

}
