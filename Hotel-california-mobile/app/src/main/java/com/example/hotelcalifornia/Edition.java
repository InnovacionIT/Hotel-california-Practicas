package com.example.hotelcalifornia;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelcaliforniaModelo.Cliente;
import com.example.hotelcaliforniaNegocio.GestorDeClientes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Edition extends AppCompatActivity {
    private static final String TAG_ERROR_EDICION = "Editar perfil error";
    EditText usuarioEditar, emailEditar, passwordEditar;
    TextView textEditacionDatosError;
    private boolean passwordVisible = false;
    BottomNavigationView bottomNavigationView;
    GestorDeClientes gestorDeClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);


        gestorDeClientes = new GestorDeClientes(this);



        // Seteamos el texto de cada editText con los datos del usuario logueado
        setearEditTextConDatosClienteLogueado();

        textEditacionDatosError = findViewById(R.id.textErrorEditarPerfil);

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
        ImageButton buttonShowPassword = findViewById(R.id.imageButton5);
        EditText editTextPassword = findViewById(R.id.editarPassword);

        buttonShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    // Mostrar contraseña
                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    // Ocultar contraseña
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                // Mover el cursor al final del texto
                editTextPassword.setSelection(editTextPassword.getText().length());
            }
        });

    }

    private void setearEditTextConDatosClienteLogueado() {
        // Inicializamos los editText.
        usuarioEditar = findViewById(R.id.editarUsuario);
        emailEditar = findViewById(R.id.editarEmail);
        passwordEditar = findViewById(R.id.editarPassword);

        // Obtenemos los datos del cliente logueado
        Cliente clienteLogueado = gestorDeClientes.getClienteLogueado();

        usuarioEditar.setText(clienteLogueado.getUsuario());
        emailEditar.setText(clienteLogueado.getEmail());
        passwordEditar.setText(clienteLogueado.getPassword());
    }

    public void editProfile(View view) {
        Pair<Boolean, String> resultadoEdicion
                = editarDatos(usuarioEditar, emailEditar, passwordEditar);
        if (resultadoEdicion.first){
            textEditacionDatosError.setText(resultadoEdicion.second);
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        } else {
            String mjeError = resultadoEdicion.second;
            Log.e(TAG_ERROR_EDICION, mjeError);
            textEditacionDatosError.setText(mjeError);
        }
    }

    private Pair<Boolean, String> editarDatos(EditText usuario, EditText email, EditText password) {
        String usu = Utils.getStringFromEditText(usuario);
        String mail = Utils.getStringFromEditText(email);
        String pass = Utils.getStringFromEditText(password);

        String mjeError;
        String[] datos = new String[]{usu, mail, pass};
        // Validamos campos completos.
        if (Utils.existeDatoStringVacio(datos)){
            mjeError = "* Debe completar todos los campos.";
            return Pair.create(false, mjeError);
        }

        // Validamos mail nuevo en Db
        if (modificoEmail(mail) && gestorDeClientes.esEmailExistente(mail)){
            mjeError = "* El email ingresado ya existe.";
            return Pair.create(false, mjeError);
        }

        // Validamos longitud de password mayor o igual a 6.
        if (pass.length()<Utils.LONG_MIN_PASS) {
            mjeError = "* Su contraseña debe contener al menos 6 caracteres.";
            return Pair.create(false, mjeError);
        }

        if (gestorDeClientes.modificarDatosCliente(usu, mail, pass)){
            return Pair.create(true, "");
        } else {
            return Pair.create(false, "* No fue posible editar su perfil, intente nuevamente.");
        }
    }

    private boolean modificoEmail(String email) {
        String emailOriginal = gestorDeClientes.getClienteLogueado().getEmail();
        return !email.equals(emailOriginal);
    }

}