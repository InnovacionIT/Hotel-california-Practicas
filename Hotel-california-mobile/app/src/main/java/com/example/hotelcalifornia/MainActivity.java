package com.example.hotelcalifornia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelcaliforniaNegocio.GestorDeClientes;

public class MainActivity extends AppCompatActivity {

    EditText emailLogin, passwordLogin;
    Button inicio;
    TextView textErroLogin;
    GestorDeClientes gestorDeClientes;
    private static final String TAG_ERROR_LOGIN = "Login incorrecto";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        inicio = findViewById(R.id.inicio);
        textErroLogin = findViewById(R.id.textErrorLogin);

        // Obtenemos un gestor y le pasamos el Contexto para que haga la conexión a la DB
        gestorDeClientes = new GestorDeClientes(this);

        // Encuentra el botón flotante
        ImageButton fabWhatsApp = findViewById(R.id.fabWhatsApp);

        // Establece un listener para el botón
        fabWhatsApp.setOnClickListener(view -> {

            String phoneNumber = "+5493513441382"; // El número de teléfono deseado con el código de país

            // Mensaje predeterminado
            String message = "Hola, quiero hacer una consulta sobre la aplicación de Hotel California";

            // Codifica el mensaje para que sea parte de la URL
            try {
                message = Uri.encode(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Intent para abrir WhatsApp con el número de teléfono y el mensaje predeterminado
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + message;
            intent.setData(Uri.parse(url));

            // Verifica si WhatsApp está instalado y ejecuta el Intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // Muestra un mensaje si WhatsApp no está instalado
                Toast.makeText(MainActivity.this, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void iraregistro(View view){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    public void home(View view){
        Pair<Boolean, String> resultadoLogin = login(emailLogin, passwordLogin);
        if (resultadoLogin.first) {
            textErroLogin.setText(resultadoLogin.second);
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        } else {
            String mjeError = resultadoLogin.second;
            Log.e(TAG_ERROR_LOGIN, mjeError);
            textErroLogin.setText(mjeError);
        }
    }

    private Pair<Boolean, String> login(EditText email, EditText password){
        String mail = Utils.getStringFromEditText(email);
        String pass = Utils.getStringFromEditText(password);

        String mjeError;
        String[] datos = new String[]{mail, pass};
        // Validamos campos completos.
        if (Utils.existeDatoStringVacio(datos)){
            mjeError = "* Debe completar todos los campos.";
            return Pair.create(false, mjeError);
        }
        if (gestorDeClientes.login(mail, pass)){
            return Pair.create(true, "");
        } else {
            return Pair.create(false, "* El usuario y/o password son incorrectos.");
        }
    }
}
