package com.example.hotelcaliforniaNegocio;

import android.content.Context;

import com.example.hotelcaliforniaDatos.IWritableDataAccess;
import com.example.hotelcaliforniaDatos.ReservaDataAccess;
import com.example.hotelcaliforniaModelo.Reserva;

import java.util.ArrayList;
import java.util.Date;

public class GestorDeReservas {
    IWritableDataAccess<Reserva> reservaDA;
    GestorDeClientes gestorDeClientes;

    public GestorDeReservas(Context context) {
        reservaDA = new ReservaDataAccess(context);
        gestorDeClientes = new GestorDeClientes(context);
    }

    public boolean usuarioTieneReservas() {
        return !obtenerReservasNoAnuladasClienteLogueado().isEmpty();
    }

    public Reserva obtenerReservaParaMostrar(int reservaIndex) {
        ArrayList<Reserva> reservas = obtenerReservasNoAnuladasClienteLogueado();
        int totalReservas = reservas.size();
        int indiceInverso = totalReservas - 1 - reservaIndex;

        return (0 <= indiceInverso && indiceInverso < totalReservas)
                ? reservas.get(indiceInverso)
                : null;
    }

    public ArrayList<Reserva> obtenerReservasNoAnuladasClienteLogueado() {
        int idCliente = gestorDeClientes.getClienteLogueado().getId();
        ArrayList<Reserva> reservas = ((ReservaDataAccess) reservaDA).getAll(idCliente);
        reservas.removeIf(Reserva::isAnulada);
        return reservas;
    }

    public float calculoPrecio(Date fechaIngreso, Date fechaEgreso, float precioHab) {
        long milisegundosIngreso = fechaIngreso.getTime();
        long milisegundosEgreso = fechaEgreso.getTime();
        long diferenciaMilisegundos = milisegundosEgreso - milisegundosIngreso;
        long diferenciaDias = diferenciaMilisegundos / (24 * 60 * 60 * 1000);
        float precioTotal = precioHab * diferenciaDias;
        return precioTotal;
    }

    public Reserva obtenerReserva(int reservaId) {
        return reservaDA.getById(reservaId);
    }

    public void modificarReserva(Reserva re) {
        reservaDA.update(re);
    }
}