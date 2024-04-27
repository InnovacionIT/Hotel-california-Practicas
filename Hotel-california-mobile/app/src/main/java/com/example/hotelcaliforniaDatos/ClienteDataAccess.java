package com.example.hotelcaliforniaDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hotelcaliforniaModelo.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ClienteDataAccess implements IWritableDataAccess<Cliente> {
    SQLiteDatabase db;
    SimpleDateFormat formatoFecha;
    private static final String FORMATO_FECHA_DB = "yyyy-MM-dd";


    public ClienteDataAccess(Context context) {
        this.db = HotelSQLiteHelper.getInstance(context).getDatabase();
        formatoFecha = new SimpleDateFormat(FORMATO_FECHA_DB, Locale.getDefault());
    }

    @Override
    public void create(Cliente entidad) {
        //Creamos el registro a insertar como objeto ContentValues
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("usuario", entidad.getUsuario());
        nuevoRegistro.put("email", entidad.getEmail());
        nuevoRegistro.put("password", entidad.getPassword());
        String fechaNacimiento = formatoFecha.format(entidad.getFechaNac());
        nuevoRegistro.put("fechaDeNacimiento", fechaNacimiento);
        nuevoRegistro.put("activo", entidad.getActivo());
        nuevoRegistro.put("recibeNotificaciones", entidad.getRecibeNotificaciones());

        if (db != null){
            //Insertamos el registro en la base de datos
            db.insert("Cliente", null, nuevoRegistro);
        }
    }

    @Override
    public Cliente update(Cliente entidad) {
        //Creamos un registro que será modificado
        ContentValues valores = new ContentValues();
        valores.put("usuario", entidad.getUsuario());
        valores.put("email", entidad.getEmail());
        valores.put("password", entidad.getPassword());
        String fechaNacimiento = formatoFecha.format(entidad.getFechaNac());
        valores.put("fechaDeNacimiento", fechaNacimiento);
        valores.put("recibeNotificaciones", entidad.getRecibeNotificaciones());

        int clienteId = entidad.getId();
        //Modificamos el registro en la base de datos
        String[] args = new String[]{ String.valueOf(clienteId)};
        db.update("Cliente", valores, "clienteId = ?", args);

        return getById(clienteId);
    }

    @Override
    public void delete(Cliente entidad) {
        int id = entidad.getId();
        delete(id);
    }

    @Override
    public void delete(int id) {
        // Seteamos el campo activo en false
        ContentValues valores = new ContentValues();
        valores.put("activo",0);
        //Actualizamos el registro en la base de datos
        String[] args = new String[]{ String.valueOf(id) };
        db.update("Cliente", valores, "clienteId = ?", args);
    }

    @Override
    public Cliente getById(int id) {
        Cliente cliente = new Cliente();
        if(db != null)
        {
            String[] campos = new String[] {
                    "clienteId", "usuario", "email", "password", "fechaDeNacimiento", "activo", "recibeNotificaciones" };
            String[] args = new String[] {String.valueOf(id)};
            Cursor c = db.query("Cliente", campos, "clienteId = ?", args, null, null, null);

            if (c.moveToFirst()) { // Verifica que exista al menos un registro.
                cliente.setId(id);

                String usuario = c.getString(1);
                cliente.setUsuario(usuario);

                String email = c.getString(2);
                cliente.setEmail(email);

                String password = c.getString(3);
                cliente.setPassword(password);

                String fechaDeNacimiento = c.getString(4);
                Date fechaNac;
                try {
                    fechaNac = formatoFecha.parse(fechaDeNacimiento);
                } catch (ParseException e) {
                    fechaNac = new Date(1900,1,1);
                }
                cliente.setFechaNac(fechaNac);

                int activoData = c.getInt(5);
                boolean activo = activoData == 1;
                cliente.setActivo(activo);

                int recibeNotificacionesData = c.getInt(6);
                boolean recibeNotificaciones = recibeNotificacionesData == 1;
                cliente.setRecibeNotificaciones(recibeNotificaciones);
            }
            c.close();
        }
        return cliente;
    }

    @Override
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        if(db != null)
        {
            String[] campos = new String[] {
                    "clienteId", "usuario", "email", "password", "fechaDeNacimiento", "activo", "recibeNotificaciones" };
            Cursor c = db.query("Cliente", campos, null, null, null, null, null);

            if (c.moveToFirst()) { // Verifica que exista al menos un registro.
                //Recorremos el cursor por los todos los registros que trae la query
                do {
                    // Casteamos todos los datos del registro y lo guardamos en un objeto Cliente
                    // para almacenarlo en la lista clientes.
                    Cliente cliente = new Cliente();

                    int clienteId = c.getInt(0);
                    cliente.setId(clienteId);

                    String usuario = c.getString(1);
                    cliente.setUsuario(usuario);

                    String email = c.getString(2);
                    cliente.setEmail(email);

                    String password = c.getString(3);
                    cliente.setPassword(password);

                    String fechaDeNacimiento = c.getString(4);
                    Date fechaNac;
                    try {
                        fechaNac = formatoFecha.parse(fechaDeNacimiento);
                    } catch (ParseException e) {
                        fechaNac = new Date(1900,1,1);
                    }
                    cliente.setFechaNac(fechaNac);

                    int activoData = c.getInt(5);
                    boolean activo = activoData == 1;
                    cliente.setActivo(activo);

                    int recibeNotificacionesData = c.getInt(6);
                    boolean recibeNotificaciones = recibeNotificacionesData == 1;
                    cliente.setRecibeNotificaciones(recibeNotificaciones);

                    clientes.add(cliente);
                } while(c.moveToNext());
            }
            c.close();
        }
        return clientes;
    }
}