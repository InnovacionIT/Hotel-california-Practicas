package com.example.hotelcaliforniaModelo;

import java.util.Date;

public class Reserva extends ClaseBase {
    Date checkIn;
    Date checkOut;
    boolean notificadoAlCliente;
    boolean anulada;
    boolean pagada;

    Cliente cliente;

    Habitacion habitacion;

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isNotificadoAlCliente() {
        return notificadoAlCliente;
    }

    public void setNotificadoAlCliente(boolean notificadoAlCliente) {
        this.notificadoAlCliente = notificadoAlCliente;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
}
