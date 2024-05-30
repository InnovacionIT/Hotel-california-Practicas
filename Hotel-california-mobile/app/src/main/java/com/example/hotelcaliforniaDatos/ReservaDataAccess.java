package com.example.hotelcaliforniaDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hotelcaliforniaModelo.Cliente;
import com.example.hotelcaliforniaModelo.Habitacion;
import com.example.hotelcaliforniaModelo.Reserva;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ReservaDataAccess implements IWritableDataAccess<Reserva> {

    SQLiteDatabase db;
    IReadableDataAccess<Habitacion> habitacionDA;
    IWritableDataAccess<Cliente> clienteDA;

    public ReservaDataAccess(Context context) {
        this.db = HotelSQLiteHelper.getInstance(context).getDatabase();
        habitacionDA = new HabitacionDataAccess(context);
        clienteDA = new ClienteDataAccess(context);
    }

    @Override
    public Reserva getById(int id) {
        Reserva reserva = new Reserva();
        if (db != null) {
            String[] campos = new String[]{
                    "habitacionId", "clienteId", "chechIn", "checkOut", "notificadoAlCliente", "anulada", "pagada"};
            String[] args = new String[]{String.valueOf(id)};
            Cursor c = db.query("Reserva", campos, "reservaId = ?", args, null, null, null);

            if (c.moveToFirst()) { // Verifica que exista al menos un registro.
                reserva.setId(id);

                int habitacionId = c.getInt(0);
                reserva.setHabitacion(habitacionDA.getById(habitacionId));

                int clienteId = c.getInt(1);
                reserva.setCliente(clienteDA.getById(clienteId));

                String fechaCheckIn = c.getString(2);
                String fechaCheckOut = c.getString(3);
                Date checkin, chechout;
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                try {
                    checkin = formatoFecha.parse(fechaCheckIn);
                    chechout = formatoFecha.parse(fechaCheckOut);
                } catch (ParseException ex) {
                    Log.e("FECHA", ex.getMessage());
                    checkin = new Date(1900, 1, 1);
                    chechout = new Date(1900, 2, 2);
                }
                reserva.setCheckIn(checkin);
                reserva.setCheckOut(chechout);

                int notificadoAlCliente = c.getInt(4);
                boolean notificado = notificadoAlCliente == 1;
                reserva.setNotificadoAlCliente(notificado);

                int anulada = c.getInt(5);
                boolean anu = anulada == 1;
                reserva.setAnulada(anu);

                int pagada = c.getInt(6);
                boolean pago = pagada == 1;
                reserva.setPagada(pago);
            }
            c.close();
        }
        return reserva;
    }

    @Override
    public ArrayList<Reserva> getAll() {
        return null;
    }


    public ArrayList<Reserva> getAll(int id) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        if (db != null) {
            String[] campos = new String[]{
                    "reservaId", "habitacionId", "clienteId", "chechIn", "checkOut", "notificadoAlCliente", "anulada", "pagada"};
            String[] args = new String[]{String.valueOf(id)};
            Cursor c = db.query("Reserva", campos, "clienteId = ?", args, null, null, null);

            if (c.moveToFirst()) { // Verifica que exista al menos un registro.
                //Recorremos el cursor por los todos los registros que trae la query
                do {
                    // Casteamos todos los datos del registro y lo guardamos en un objeto Cliente
                    // para almacenarlo en la lista clientes.
                    Reserva reserva = new Reserva();

                    int reservaId = c.getInt(0);
                    reserva.setId(reservaId);

                    int habitacionId = c.getInt(1);
                    reserva.setHabitacion(habitacionDA.getById(habitacionId));

                    int clienteId = c.getInt(2);
                    reserva.setCliente(clienteDA.getById(clienteId));

                    String fechaCheckIn = c.getString(3);
                    String fechaCheckOut = c.getString(4);
                    Date checkin, chechout;
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    try {
                        checkin = formatoFecha.parse(fechaCheckIn);
                        chechout = formatoFecha.parse(fechaCheckOut);
                    } catch (ParseException ex) {
                        Log.e("FECHA", ex.getMessage());
                        checkin = new Date(1900, 1, 1);
                        chechout = new Date(1900, 2, 2);
                    }
                    reserva.setCheckIn(checkin);
                    reserva.setCheckOut(chechout);

                    int notificadoAlCliente = c.getInt(5);
                    boolean notificado = notificadoAlCliente == 1;
                    reserva.setNotificadoAlCliente(notificado);

                    int anulada = c.getInt(6);
                    boolean anu = anulada == 1;
                    reserva.setAnulada(anu);

                    int pagada = c.getInt(7);
                    boolean pago = pagada == 1;
                    reserva.setPagada(pago);

                    reservas.add(reserva);
                } while (c.moveToNext());
            }
            c.close();
        }
        return reservas;
    }

    @Override
    public void create(Reserva entidad) {
        //"habitacionId", "clienteId", "chechIn", "checkOut", "notificadoAlCliente", "anulada","pagada"
        //Creamos el registro a insertar como objeto ContentValues
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("habitacionId", entidad.getHabitacion().getId());
        nuevoRegistro.put("clienteId", entidad.getCliente().getId());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        nuevoRegistro.put("chechIn", formatoFecha.format(entidad.getCheckIn()));
        nuevoRegistro.put("checkOut", formatoFecha.format(entidad.getCheckOut()));
        nuevoRegistro.put("notificadoAlCliente", entidad.isNotificadoAlCliente());
        nuevoRegistro.put("anulada", entidad.isAnulada());
        nuevoRegistro.put("pagada", entidad.isPagada());

        if (db != null) {
            // Verificar si ya existe una reserva para esa habitación en las fechas dadas
            String query = "SELECT COUNT(*) FROM Reserva WHERE habitacionId = ? AND " +
                    "(? BETWEEN chechIn AND checkOut OR ? BETWEEN chechIn AND checkOut OR " +
                    "chechIn BETWEEN ? AND ? OR checkOut BETWEEN ? AND ?)";
            String[] selectionArgs = new String[] {
                    String.valueOf(entidad.getHabitacion().getId()),
                    formatoFecha.format(entidad.getCheckIn()),
                    formatoFecha.format(entidad.getCheckOut()),
                    formatoFecha.format(entidad.getCheckIn()),
                    formatoFecha.format(entidad.getCheckOut()),
                    formatoFecha.format(entidad.getCheckIn()),
                    formatoFecha.format(entidad.getCheckOut())
            };

            Cursor cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null) {
                cursor.moveToFirst();
                int count = cursor.getInt(0);
                cursor.close();

                if (count > 0) {
                    // Ya existe una reserva para esa habitación en las fechas
                    return;
                }
            }

            //Insertamos el registro en la base de datos
            db.insert("Reserva", null, nuevoRegistro);
        }
    }


    @Override
    public Reserva update(Reserva entidad) {
        //Creamos un registro que será modificado
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("habitacionId", entidad.getHabitacion().getId());
        nuevoRegistro.put("clienteId", entidad.getCliente().getId());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        nuevoRegistro.put("chechIn", formatoFecha.format(entidad.getCheckIn()));
        nuevoRegistro.put("checkOut", formatoFecha.format(entidad.getCheckOut()));
        nuevoRegistro.put("notificadoAlCliente", entidad.isNotificadoAlCliente());
        nuevoRegistro.put("anulada", entidad.isAnulada());
        nuevoRegistro.put("pagada", entidad.isPagada());

        int reservaId = entidad.getId();
        //Modificamos el registro en la base de datos
        String[] args = new String[]{String.valueOf(reservaId)};
        db.update("Reserva", nuevoRegistro, "reservaId = ?", args);

        return getById(reservaId);
    }

    @Override
    public void delete(Reserva entidad) {

    }

    @Override
    public void delete(int id) {

    }
}