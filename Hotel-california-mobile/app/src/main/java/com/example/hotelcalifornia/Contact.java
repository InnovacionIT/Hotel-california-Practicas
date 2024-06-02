package com.example.hotelcalifornia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;

import com.example.hotelcaliforniaModelo.Cliente;
import com.example.hotelcaliforniaNegocio.GestorDeClientes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contact extends AppCompatActivity {

    Cliente client;
    public void goToProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        finish();

    }

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        TextView textHolaUsuario = findViewById(R.id.holaUsuario);

        GestorDeClientes gestorDeClientes = new GestorDeClientes(this);
        client = gestorDeClientes.getClienteLogueado();
        String fullName = client.getUsuario();
        String name = fullName.split(" ")[0]; // Divide el nombre completo en palabras usando un espacio en blanco como separador

        textHolaUsuario.setText("Hola " + name + "!");

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

    }


    public void buttonSendEmail(View view) {

        try {
            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            String password = BuildConfig.EMAIL_PASSWORD;
            String email = BuildConfig.EMAIL_USERNAME;

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });


            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(client.getEmail()));

            String senderEmail = client.getEmail();
            String username = client.getUsuario();

            EditText subject = findViewById(R.id.subject);
            EditText message = findViewById(R.id.message);

            if(message.getText() == null || message.getText().toString().isEmpty()) {
                Toast.makeText(this, "El mensaje no debe estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }
            if(subject.getText() == null) {
                mimeMessage.setSubject("Hotel California Mobile");
            } else {
                mimeMessage.setSubject(subject.getText().toString());
            }

            String messageStr = message.getText().toString();
            String emailTemplate = "<h1>Hotel California</h1>" +
                    "<p>Enviado por: "+ senderEmail + " ("+username +")" + "</p>" +
                    "<p>Mensaje: " + messageStr + "</p>";

            mimeMessage.setContent(emailTemplate, "text/html; charset=utf-8");



            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {
                    Transport.send(mimeMessage);
                } catch (MessagingException e) {
                    Toast.makeText(this, "¡Algo ha ocurrido! Intente nuevamente", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            });
            executor.shutdown();
            Toast.makeText(this, "¡El mensaje se envió correctamente!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        } catch (AddressException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (MessagingException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

}