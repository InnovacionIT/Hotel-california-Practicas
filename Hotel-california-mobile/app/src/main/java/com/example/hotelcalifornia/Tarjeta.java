package com.example.hotelcalifornia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelcaliforniaModelo.Reserva;
import com.example.hotelcaliforniaNegocio.GestorDeClientes;
import com.example.hotelcaliforniaNegocio.GestorDeReservas;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Tarjeta extends AppCompatActivity {

    GestorDeReservas gestorReservas;
    TextView textoPrecio;
    Reserva re;
    Button botonReservar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta);

        //Saludo al usuario logueado
        TextView textHolaUsuario = findViewById(R.id.holaUsuario);
        GestorDeClientes gestorDeClientes = new GestorDeClientes(this);
        String nombreCompleto = gestorDeClientes.getClienteLogueado().getUsuario();
        String[] partes = nombreCompleto.split(" "); // Divide el nombre completo en palabras usando un espacio en blanco como separador
        String nombre = partes[0]; // Obtiene la primera palabra, que es el nombre
        textHolaUsuario.setText("Hola " + nombre + "!");

        // Abrimos el intenet con el dato de la reserva id
        Intent intenet = getIntent();
        float precio = getIntent().getFloatExtra(Detalle.PRECIOTOTAL, 0);
        int reservaActualId = intenet.getIntExtra(Reservas.RESERVA, 0);

        gestorReservas = new GestorDeReservas(this);
        re = gestorReservas.obtenerReserva(reservaActualId);
        textoPrecio = findViewById(R.id.totalAPagar);
        textoPrecio.setText("total a pagar: $" + String.valueOf((int) precio));

        botonReservar = findViewById(R.id.confirmarPagoButton);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu);

        EditText nTarjetaInput = findViewById(R.id.nTarjetaInput);

        // Aplicamos un InputFilter para limitar a 19 dígitos (16 + 3 caracteres vacíos)
        nTarjetaInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(19)});
        nTarjetaInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Obtenemos el texto actual del EditText y eliminamos espacios en blanco
                String input = s.toString().replaceAll("\\s", "");

                // Formateamos el número de tarjeta con guiones bajos
                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < input.length(); i++) {
                    formatted.append(input.charAt(i));
                    if ((i + 1) % 4 == 0 && i < input.length() - 1) {
                        formatted.append(" "); // Agregamos un espacio después de cada grupo de 4 dígitos
                    }
                }

                // Establece el nuevo texto formateado en el EditText
                nTarjetaInput.removeTextChangedListener(this);
                nTarjetaInput.setText(formatted.toString());
                nTarjetaInput.setSelection(formatted.length());
                nTarjetaInput.addTextChangedListener(this);
            }
        });


        EditText codSeguridadInput = findViewById(R.id.codSeguridadInput);

        // Establecemos el tipo de entrada como número
        codSeguridadInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        // Aplicamos un InputFilter para limitar a 3 dígitos numéricos
        codSeguridadInput.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(3), // Límite de longitud
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        // Solo permitimos dígitos numéricos
                        for (int i = start; i < end; i++) {
                            if (!Character.isDigit(source.charAt(i))) {
                                return "";
                            }
                        }
                        return null;
                    }
                }
        });


        EditText fechaVencInput = findViewById(R.id.fechaVencInput);

        fechaVencInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();

                // Elimina caracteres no permitidos
                input = input.replaceAll("[^0-9/]", "");

                // Limita la entrada a "MM/YY"
                if (input.length() > 5) {
                    input = input.substring(0, 5);
                }

                // Verifica que los dos primeros dígitos estén en el rango 01-12
                if (input.length() >= 2) {
                    int mes = Integer.parseInt(input.substring(0, 2));
                    if (mes < 1) {
                        input = "01" + input.substring(2);
                    } else if (mes > 12) {
                        input = "12" + input.substring(2);
                    }
                }

                // Asegura que haya una barra ("/") entre MM y YY
                if (input.length() >= 3 && input.charAt(2) != '/') {
                    input = input.substring(0, 2) + "/" + input.substring(2);
                }

                fechaVencInput.removeTextChangedListener(this);
                fechaVencInput.setText(input);
                fechaVencInput.setSelection(input.length());
                fechaVencInput.addTextChangedListener(this);
            }
        });


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

    public void volverADetalle(View view) {
        Intent intent = new Intent(this, Detalle.class);
        finish();
    }

    public void notificaciones(View view) {
        // Obtén los valores de los campos
        EditText nTarjetaInput = findViewById(R.id.nTarjetaInput);
        EditText codSeguridadInput = findViewById(R.id.codSeguridadInput);
        EditText fechaVencInput = findViewById(R.id.fechaVencInput);
        EditText nombreApellidoInput = findViewById(R.id.nombreyapellidoInput);

        String numeroTarjeta = nTarjetaInput.getText().toString().replaceAll("\\s", ""); // Elimina espacios en blanco
        String codSeguridad = codSeguridadInput.getText().toString();
        String fechaVencimiento = fechaVencInput.getText().toString();
        String nombreApellido = nombreApellidoInput.getText().toString().trim(); // Elimina espacios en blanco al inicio y al final

        // Verifica si alguno de los campos está vacío o incompleto
        if (numeroTarjeta.length() != 16 || codSeguridad.length() != 3 || !fechaVencimiento.matches("\\d{2}/\\d{2}") || nombreApellido.isEmpty()) {
            // Muestra un mensaje Toast indicando que los datos son incorrectos
            Toast.makeText(this, "Verifica todos los campos y complételos correctamente.", Toast.LENGTH_SHORT).show();
        } else {
            // Todos los campos tienen datos válidos, puedes continuar con la transacción
            re.setPagada(true);
            gestorReservas.modificarReserva(re);
            String mensaje = "¡Reserva confirmada con éxito!";
            Intent intent = new Intent(this, NotificationActivity.class);
            intent.putExtra("mensaje", mensaje);
            startActivity(intent);
        }

    }
}