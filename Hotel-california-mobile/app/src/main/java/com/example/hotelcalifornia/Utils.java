package com.example.hotelcalifornia;

import android.widget.EditText;

import java.util.Arrays;

public class Utils {
    public static final String FORMATO_FECHA_FORMULARIO = "dd/MM/yyyy";

    public static final int LONG_MIN_PASS = 6;

    public static String getStringFromEditText(EditText editText){
        return editText.getText().toString();
    }

    public static boolean existeDatoStringVacio(String[] datos) {
        return Arrays.stream(datos).anyMatch(String::isEmpty);
    }
}
