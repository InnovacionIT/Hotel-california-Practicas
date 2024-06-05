package com.example.hotelcalifornia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;


import com.example.hotelcaliforniaModelo.Reserva;
import com.example.hotelcaliforniaNegocio.GestorDeClientes;
import com.example.hotelcaliforniaNegocio.GestorDeReservas;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Reservas extends AppCompatActivity {
    Button buttonEliminar, buttonPagar;
    ImageButton buttonPrevReserva, buttonNextReserva;
    TextView textViewCheckin, textViewCheckout, textViewTipoHabitacion, textViewMontoTotal;
    TextView textNoHayReservas, textReservaVencida;
    BottomNavigationView bottomNavigationView;
    GestorDeReservas gestorDeReservas;
    Reserva reservaMostrandose;
    ImageView imageHabitacion;
    private int reservaActualIndex = 0;
    public static final String RESERVA = "RESERVA_ID";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        // Saludo al usuario logueado
        TextView textHolaUsuario = findViewById(R.id.holaUsuario);

        GestorDeClientes gestorDeClientes = new GestorDeClientes(this);

        String nombreCompleto = gestorDeClientes.getClienteLogueado().getUsuario();
        String[] partes = nombreCompleto.split(" "); // Divide el nombre completo en palabras usando un espacio en blanco como separador
        String nombre = partes[0]; // Obtiene la primera palabra, que es el nombre

        textHolaUsuario.setText("Hola " + nombre + "!");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu);

        // Inicializamos los elementos visuales
        inicializarEventosVisuales();

        gestorDeReservas = new GestorDeReservas(this);
        if (gestorDeReservas.usuarioTieneReservas()) {
            // Mostramos la reserva actual o la última hecha por el cliente que no esté anulada.
            mostrarElementosVisuales(true);
            mostrarReserva(reservaActualIndex);
            activarBotonesPrevAndNext();
        } else {
            mostrarElementosVisuales(false);
            textNoHayReservas.setText(R.string.usuario_sin_reservas);
        }

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

    private void inicializarEventosVisuales() {
        // TextView
        textViewTipoHabitacion = findViewById(R.id.textTipoHab);
        textViewCheckin = findViewById(R.id.textCheckIn);
        textViewCheckout = findViewById(R.id.textCheckout);
        textViewMontoTotal = findViewById(R.id.textMontoTotal);
        textNoHayReservas = findViewById(R.id.textNoHayReservas);
        textReservaVencida = findViewById(R.id.textReservaVencida);

        // Botones
        buttonEliminar = findViewById(R.id.buttonEliminar);
        buttonPagar = findViewById(R.id.buttonPagar);
        buttonPrevReserva = findViewById(R.id.buttonPrevReserva);
        buttonNextReserva = findViewById(R.id.buttonNextReserva);

        //ImageView
        imageHabitacion = findViewById(R.id.imageHabitacion);
    }

    private void mostrarElementosVisuales(boolean mostrar) {
        int visibility = mostrar ? View.VISIBLE : View.INVISIBLE;
        int visibilityNoHayReservas = mostrar ? View.INVISIBLE : View.VISIBLE;

        List<View> elementosVisuales = Arrays.asList(
                textViewTipoHabitacion,
                textViewCheckin,
                textViewCheckout,
                textViewMontoTotal,
                buttonEliminar,
                buttonPagar,
                buttonPrevReserva,
                buttonNextReserva
        );
        elementosVisuales.forEach(view -> view.setVisibility(visibility));
        textNoHayReservas.setVisibility(visibilityNoHayReservas);
    }

    private void mostrarReserva(int reservaActualIndex) {
        reservaMostrandose = gestorDeReservas.obtenerReservaParaMostrar(reservaActualIndex);
        // Seteamos los textos de la info
        SimpleDateFormat formato
                = new SimpleDateFormat(Utils.FORMATO_FECHA_FORMULARIO, Locale.getDefault());
        String fechaCheckin = formato.format(reservaMostrandose.getCheckIn());
        String fechaCheckout = formato.format(reservaMostrandose.getCheckOut());
        String tipoHabitacion = reservaMostrandose.getHabitacion().getHabTipo();
        textViewCheckin.setText(fechaCheckin);
        textViewCheckout.setText(fechaCheckout);
        textViewTipoHabitacion.setText("Habitacion " + tipoHabitacion);
        float precioTotal = gestorDeReservas.calculoPrecio(reservaMostrandose.getCheckIn(), reservaMostrandose.getCheckOut(), reservaMostrandose.getHabitacion().getHabPrecio());
        textViewMontoTotal.setText("$" + String.valueOf((int) precioTotal ));


        imageHabitacion.setImageResource(obtenerImagenHabitacion(tipoHabitacion));

        actualizarVisualizacionSegunElEstadoDe(reservaMostrandose);
    }
    private int obtenerImagenHabitacion(String tipoHabitacion) {
        Map<String, Integer> imagenesHabitaciones = new HashMap<>();
        imagenesHabitaciones.put("Single", R.drawable.singledoble); // Asocia "Single" con la imagen correspondiente
        imagenesHabitaciones.put("Doble", R.drawable.suite);
        imagenesHabitaciones.put("Suite", R.drawable.suiteii);
        imagenesHabitaciones.put("Triple", R.drawable.triple);
        imagenesHabitaciones.put("Cuadruple", R.drawable.cuadruple);

        Integer imagenResId = imagenesHabitaciones.get(tipoHabitacion);
        return (imagenResId != null) ? imagenResId : R.drawable.room; // Imagen por defecto si no se encuentra el tipo de habitación
    }


    private void actualizarVisualizacionSegunElEstadoDe(Reserva reserva) {
        // Seteamos si los botones están habilitados o no.
        if (reserva.isPagada()) {
            actualizarBotonPagar(R.string.pagada, false, R.color.gris);
        } else {
            actualizarBotonPagar(R.string.pagar, true, R.color.naranja);
        }

        Calendar fechaActual = Calendar.getInstance();
        fechaActual.set(Calendar.HOUR_OF_DAY, 0);
        fechaActual.set(Calendar.MINUTE, 0);
        fechaActual.set(Calendar.SECOND, 0);
        fechaActual.set(Calendar.MILLISECOND, 0);

        Calendar fechaCheckIn = Calendar.getInstance();
        fechaCheckIn.setTime(reserva.getCheckIn());
        fechaCheckIn.set(Calendar.HOUR_OF_DAY, 0);
        fechaCheckIn.set(Calendar.MINUTE, 0);
        fechaCheckIn.set(Calendar.SECOND, 0);
        fechaCheckIn.set(Calendar.MILLISECOND, 0);

        boolean reservaVencida = fechaCheckIn.before(fechaActual);
        if (reservaVencida && reserva.isPagada()) {
            ajustarVistaSegunReserva(R.string.gracias, View.INVISIBLE, View.VISIBLE);
        } else if (reservaVencida && !reserva.isPagada()) {
            ajustarVistaSegunReserva(R.string.reserva_anulada, View.INVISIBLE, View.INVISIBLE);
        } else {
            ajustarVistaSegunReserva(R.string.empty, View.VISIBLE, View.VISIBLE);
        }
    }

    private void actualizarBotonPagar(int idText, boolean isClickeable, int idColor) {
        buttonPagar.setText(idText);
        buttonPagar.setClickable(isClickeable);
        buttonPagar.setBackgroundTintList(ContextCompat.getColorStateList(this, idColor));
    }

    private void ajustarVistaSegunReserva(int idText, int visibilidadEliminar, int visibilidadPagar){
        textReservaVencida.setText(idText);
        buttonEliminar.setVisibility(visibilidadEliminar);
        buttonPagar.setVisibility(visibilidadPagar);
    }

    private void activarBotonesPrevAndNext() {
        // Obtenemos todas las reservas del usuario logueado
        ArrayList<Reserva> listaDeReservas = gestorDeReservas.obtenerReservasNoAnuladasClienteLogueado();

        // Ocultamos los botones si es la primer reserva o la ultima
        if (reservaActualIndex == 0) {
            buttonPrevReserva.setVisibility(View.INVISIBLE);
        } else {
            buttonPrevReserva.setVisibility(View.VISIBLE);
        }
        if (reservaActualIndex == listaDeReservas.size() - 1) {
            buttonNextReserva.setVisibility(View.INVISIBLE);
        } else {
            buttonNextReserva.setVisibility(View.VISIBLE);
        }
        // Para que los cambios se reflejen
        invalidateOptionsMenu();

        // Manejar el clic en el botón "Prev"
        buttonPrevReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reservaActualIndex > 0) {
                    reservaActualIndex--;
                    mostrarReserva(reservaActualIndex);
                    activarBotonesPrevAndNext();
                }
            }
        });

        // Manejar el clic en el botón "Next"
        buttonNextReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reservaActualIndex < listaDeReservas.size() - 1) {
                    reservaActualIndex++;
                    mostrarReserva(reservaActualIndex);
                    activarBotonesPrevAndNext();
                }
            }
        });
    }

    public void pago(View view) {
        Intent pagar = new Intent(this, Detalle.class);
        int reservaId = reservaMostrandose.getId();
        pagar.putExtra(RESERVA, reservaId);
        startActivity(pagar);
    }

    public void IraHome(View view) {
        Intent intent = new Intent(this, Home.class);
        finish();
    } //flechita para volver

    public void eliminar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Esta seguro que desea eliminar la reserva?");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reservaMostrandose.setAnulada(true);
                gestorDeReservas.modificarReserva(reservaMostrandose);
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }

}