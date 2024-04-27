package com.example.hotelcaliforniaModelo;

public class Habitacion extends ClaseBase {
    int habNum;
    String habTipo;
    float habPrecio;
    String habDescripcion;

    public int getHabNum() {
        return habNum;
    }

    public void setHabNum(int habNum) {
        this.habNum = habNum;
    }

    public String getHabTipo() {
        return habTipo;
    }

    public void setHabTipo(String habTipo) {
        this.habTipo = habTipo;
    }

    public float getHabPrecio() {
        return habPrecio;
    }

    public void setHabPrecio(float habPrecio) {
        this.habPrecio = habPrecio;
    }

    public String getHabDescripcion() {
        return habDescripcion;
    }

    public void setHabDescripcion(String habDescripcion) {
        this.habDescripcion = habDescripcion;
    }
}
