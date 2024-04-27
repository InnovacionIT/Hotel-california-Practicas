package com.example.hotelcaliforniaDatos;

import com.example.hotelcaliforniaModelo.ClaseBase;

public interface IWritableDataAccess<T extends ClaseBase> extends IReadableDataAccess<T> {
    void create(T entidad);
    T update(T entidad);
    void delete(T entidad);
    void delete(int id);
}
