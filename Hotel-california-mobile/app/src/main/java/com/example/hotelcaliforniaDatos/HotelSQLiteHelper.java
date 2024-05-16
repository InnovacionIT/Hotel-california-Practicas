package com.example.hotelcaliforniaDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hotelcalifornia.R;

public class HotelSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DbHotelCaliforniaFinal";
    private static final int DATABASE_VERSION = 8;
    private static HotelSQLiteHelper instance;
    private Context context;
    private SQLiteDatabase database;

    private HotelSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static synchronized HotelSQLiteHelper getInstance(Context context) {
        if (instance == null) {
            instance = new HotelSQLiteHelper(context.getApplicationContext());
        }
        return instance;
    }

    protected SQLiteDatabase getDatabase() {
        if (database == null || !database.isOpen()) {
            database = getWritableDatabase();
        }
        return database;
    }

    // Se llama si la DB no existe.
    // Crea la DB con las tablas y los datos que indiquemos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        createDataHotel(db);
        createDataHabitacion(db);
        createDataCliente(db);
        createDataReserva(db);
    }

    // Se llama si la DB existe previamente y si cuando creamos el objeto
    // HotelSQLiteHelper le pasamos una versión posterior a la existente.
    // Actualiza la DB a su estado original.
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {
        // NOTA: Por simplicidad utilizamos la opción de eliminar todas las tablas
        // y crearlas de nuevo en el estado iniical.
        db.execSQL(context.getString(R.string.drop_reserva));
        db.execSQL(context.getString(R.string.drop_cliente));
        db.execSQL(context.getString(R.string.drop_habitacion));
        db.execSQL(context.getString(R.string.drop_hotel));

        createDataHotel(db);
        createDataHabitacion(db);
        createDataCliente(db);
        createDataReserva(db);
    }

    private void createDataReserva(SQLiteDatabase db) {
        db.execSQL(getSqlFromResource(R.string.create_reserva));
        db.execSQL(getSqlFromResource(R.string.insert_reserva));
    }

    private void createDataCliente(SQLiteDatabase db) {
        db.execSQL(getSqlFromResource(R.string.create_cliente));
        db.execSQL(getSqlFromResource(R.string.insert_cliente));
    }

    private void createDataHabitacion(SQLiteDatabase db) {
        db.execSQL(getSqlFromResource(R.string.create_habitacion));
        db.execSQL(getSqlFromResource(R.string.insert_habitacion));
    }

    private void createDataHotel(SQLiteDatabase db) {
        db.execSQL(getSqlFromResource(R.string.create_hotel));
        db.execSQL(getSqlFromResource(R.string.insert_hotel));
    }

    private String getSqlFromResource(int resId) {
        return context.getString(resId);
    }
}