package com.example.hotelcalifornia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.hotelcaliforniaModelo.Habitacion;
import com.example.hotelcaliforniaModelo.Reserva;
import com.example.hotelcaliforniaNegocio.GestorDeClientes;
import com.example.hotelcaliforniaNegocio.GestorDeReservas;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Detalle extends AppCompatActivity {

    TextView textPrecioTotal, textDetalleHabitacion;
    int reservaActualId;

    GestorDeReservas gestorDeReservas;
    Reserva reserva;

    public static final String PRECIOTOTAL = "pTotal";
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        TextView textHolaUsuario = findViewById(R.id.holaUsuario);


        // Inicializamos elementos visuales:
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu);
        textPrecioTotal = findViewById(R.id.precioText);
        textDetalleHabitacion = findViewById(R.id.PromocionText);

        // Obtenemos el id de la Reserva que selecciono el cliente
        Intent intenetReserva = getIntent();
        reservaActualId = intenetReserva.getIntExtra(Reservas.RESERVA, 0);

        gestorDeReservas = new GestorDeReservas(this);
        reserva = gestorDeReservas.obtenerReserva(reservaActualId);

        mostrarDatosReserva();

        GestorDeClientes gestorDeClientes = new GestorDeClientes(this);

        //Saludo al usuario logueado
        String nombreCompleto = gestorDeClientes.getClienteLogueado().getUsuario();
        String[] partes = nombreCompleto.split(" "); // Divide el nombre completo en palabras usando un espacio en blanco como separador
        String nombre = partes[0]; // Obtiene la primera palabra, que es el nombre
        textHolaUsuario.setText("Hola " + nombre + "!");

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

    }


    public void realizarPago(View view) {
        Intent intent = new Intent(this, Tarjeta.class);
        intent.putExtra(Reservas.RESERVA, reservaActualId);
        float precioTotal = obtenerPrecioTotal(reserva);
        intent.putExtra( PRECIOTOTAL , precioTotal);
        startActivity(intent);
    }

    private float obtenerPrecioTotal(Reserva res ) {
        float precioTotal = gestorDeReservas.calculoPrecio(res.getCheckIn(), res.getCheckOut(), res.getHabitacion().getHabPrecio());
        return precioTotal;
    }

    public void volverAReservas(View view) {
        Intent intent = new Intent(this, Reservas.class);
        finish();
    }

    private void mostrarDatosReserva() {
        Habitacion habitacion = reserva.getHabitacion();
        String textoReserva = "Habitación " + habitacion.getHabTipo() + "\r\n" + habitacion.getHabDescripcion();
        textDetalleHabitacion.setText(textoReserva);

         float precioTotal = obtenerPrecioTotal(reserva);

         textPrecioTotal.setText("$" + String.valueOf((int)precioTotal));
    }

}