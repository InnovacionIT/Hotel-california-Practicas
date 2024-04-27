package com.example.hotelcaliforniaDatos;


import com.example.hotelcaliforniaModelo.ClaseBase;

import java.util.ArrayList;

public interface IReadableDataAccess<T extends ClaseBase> {
    T getById(int id);
    ArrayList<T> getAll();
}
