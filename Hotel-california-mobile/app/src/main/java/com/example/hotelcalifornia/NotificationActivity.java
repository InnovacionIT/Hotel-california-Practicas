package com.example.hotelcalifornia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.hotelcaliforniaNegocio.GestorDeClientes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NotificationActivity extends AppCompatActivity {



    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        //Saludo al usuario logueado
        TextView textHolaUsuario = findViewById(R.id.holaUsuario);
        GestorDeClientes gestorDeClientes = new GestorDeClientes(this);
        String nombreCompleto = gestorDeClientes.getClienteLogueado().getUsuario();
        String[] partes = nombreCompleto.split(" "); // Divide el nombre completo en palabras usando un espacio en blanco como separador
        String nombre = partes[0]; // Obtiene la primera palabra, que es el nombre
        textHolaUsuario.setText("Hola " + nombre + "!");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.compra) {
                    Intent intent = new Intent(getApplicationContext(), Reservas.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.notificaciones) {
                    Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.perfil) {
                    Intent intent = new Intent(getApplicationContext(), Profile.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("mensaje")) {
            String mensaje = intent.getStringExtra("mensaje");
            mostrarMensaje(mensaje);
        }
    }

    public void goToHome(View view) {

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}