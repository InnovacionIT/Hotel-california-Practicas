package com.example.hotelcaliforniaDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hotelcaliforniaModelo.Habitacion;

import java.util.ArrayList;

public class HabitacionDataAccess implements IReadableDataAccess<Habitacion> {
    SQLiteDatabase db;
    public HabitacionDataAccess(Context context){
        db = HotelSQLiteHelper.getInstance(context).getDatabase();
    }

    @Override
    public Habitacion getById(int id) {
        Habitacion habitacion = new Habitacion();
        if (db != null) {
            String[] campos = new String[] {
                    "habitacionId", "numero", "precio", "descripcion", "tipoHabitacion"
            };
            String[] args = new String[] {String.valueOf(id)};
            Cursor c = db.query("Habitacion", campos, "habitacionId = ?", args, null, null, null);

            while (c.moveToNext()) {
                habitacion.setId(id);

                int numero = c.getInt(1);
                habitacion.setHabNum(numero);

                float precio = c.getFloat(2);
                habitacion.setHabPrecio(precio);

                String descripcion = c.getString(3);
                habitacion.setHabDescripcion(descripcion);

                String tipoHabitacion = c.getString(4);
                habitacion.setHabTipo(tipoHabitacion);

            }
            c.close();
        }
        return habitacion;
    }

    @Override
    public ArrayList<Habitacion> getAll() {
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        if (db != null) {
            String[] campos = new String[] {
                    "habitacionId", "numero", "precio", "descripcion", "tipoHabitacion"
            };
            Cursor c = db.query("Habitacion", campos, null, null, null, null, null);

            while (c.moveToNext()) {
                Habitacion habitacion = new Habitacion();

                int habitacionId = c.getInt(0);
                habitacion.setId(habitacionId);

                int numero = c.getInt(1);
                habitacion.setHabNum(numero);

                float precio = c.getFloat(2);
                habitacion.setHabPrecio(precio);

                String descripcion = c.getString(3);
                habitacion.setHabDescripcion(descripcion);

                String tipoHabitacion = c.getString(4); // Corregido el índice
                habitacion.setHabTipo(tipoHabitacion);

                habitaciones.add(habitacion);
            }
            c.close();
        }
        return habitaciones;
    }

}
