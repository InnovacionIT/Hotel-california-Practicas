package com.example.hotelcalifornia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hotelcaliforniaNegocio.GestorDeClientes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Registro extends AppCompatActivity {

    EditText usuarioRegistro, fechaNacRegistro, emailRegistro, passwordRegistro;
    Button crear;
    TextView textErrorRegistro;
    GestorDeClientes gestorDeClientes;
    private static final String TAG_ERROR_REGISTRO = "Registro no logrado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        gestorDeClientes = new GestorDeClientes(this);

        // Encuentra el botón flotante
        ImageButton fabWhatsApp = findViewById(R.id.fabWhatsApp);

        // Establece un listener para el botón
        fabWhatsApp.setOnClickListener(view -> {
            String phoneNumber = "5493513441382"; // El número de teléfono deseado con el código de país

            // Mensaje predeterminado
            String message = "Hola, quiero hacer una consulta sobre la aplicación de Hotel California";

            // Codifica el mensaje para que sea parte de la URL
            try {
                message = Uri.encode(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Construcción de la URL
            String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + message;
            Log.d("WhatsAppURL", "URL: " + url);

            // Intent para abrir el enlace en el navegador
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });



        // Inicializamos los elementos dinámicos.
        usuarioRegistro = findViewById(R.id.IngresarUsuario);
        fechaNacRegistro = findViewById(R.id.editfechaNacimiento);
        emailRegistro = findViewById(R.id.introducirEmail);
        passwordRegistro = findViewById(R.id.introducirPass);
        textErrorRegistro = findViewById(R.id.detallereservatext);

        fechaNacRegistro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No es necesario implementar esta función.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No es necesario implementar esta función.
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (input.length() == 2 || input.length() == 5) {
                    input += "/";
                    fechaNacRegistro.setText(input);
                    fechaNacRegistro.setSelection(input.length());
                }
            }
        });

        crear = findViewById(R.id.CrearRegistro);
    }

    public void iraMainActivity (View view){
        Pair<Boolean, String> resultadoRegistro
                = registrar(usuarioRegistro, fechaNacRegistro, emailRegistro, passwordRegistro);
        if (resultadoRegistro.first) {
            textErrorRegistro.setText(R.string.ingres_tus_datos_o_logueate);
            textErrorRegistro.setTextColor(ContextCompat.getColorStateList(this, R.color.black));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            String mjeError = resultadoRegistro.second;
            Log.e(TAG_ERROR_REGISTRO, mjeError);
            textErrorRegistro.setText(mjeError);
            textErrorRegistro.setTextColor(ContextCompat.getColorStateList(this, R.color.rojo));
        }
    }

    public void volverAMainActivity (View view){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
    }

    private Pair<Boolean, String> registrar(EditText usuario, EditText fechaNac, EditText email, EditText password) {
        String usu = Utils.getStringFromEditText(usuario);
        String fecha = Utils.getStringFromEditText(fechaNac);
        String mail = Utils.getStringFromEditText(email);
        String pass = Utils.getStringFromEditText(password);

        String mjeError;
        String[] datos = new String[]{usu, fecha, mail, pass};
        // Validamos campos completos.
        if (Utils.existeDatoStringVacio(datos)){
            mjeError = "* Debe completar todos los campos.";
            return Pair.create(false, mjeError);
        }

        // Validamos mail nuevo en Db
        if (gestorDeClientes.esEmailExistente(mail)){
            mjeError = "* El email ingresado ya existe.";
            return Pair.create(false, mjeError);
        }

        // Validamos longitud de password mayor o igual a 6.
        if (pass.length()<Utils.LONG_MIN_PASS){
            mjeError = "* Su contraseña debe contener al menos 6 caracteres.";
            return Pair.create(false, mjeError);
        }

        // Validamos y parseamos fecha a tipo Date.
        SimpleDateFormat formato = new SimpleDateFormat(Utils.FORMATO_FECHA_FORMULARIO, Locale.getDefault());
        Date fechaNacimiento;
        try {
            fechaNacimiento = formato.parse(fecha);

        } catch (ParseException e) {
            mjeError = e.getMessage();
            return Pair.create(false, mjeError);
        }

        if (gestorDeClientes.registrar(usu, fechaNacimiento, mail, pass)){
            return Pair.create(true, "");
        } else {
            return Pair.create(false, "* No fue posible su registro, intente nuevamente.");
        }
    }
}