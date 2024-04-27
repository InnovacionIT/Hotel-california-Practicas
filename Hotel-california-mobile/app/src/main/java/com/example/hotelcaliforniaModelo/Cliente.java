package com.example.hotelcaliforniaModelo;

import java.util.Date;

public class Cliente extends ClaseBase {
    String usuario;
    String email;
    String password;
    Date fechaNac;
    Boolean activo;
    Boolean recibeNotificaciones;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getRecibeNotificaciones() {
        return recibeNotificaciones;
    }

    public void setRecibeNotificaciones(Boolean recibeNotificaciones) {
        this.recibeNotificaciones = recibeNotificaciones;
    }
}
