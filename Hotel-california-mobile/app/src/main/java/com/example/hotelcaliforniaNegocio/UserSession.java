package com.example.hotelcaliforniaNegocio;

import com.example.hotelcaliforniaModelo.Cliente;

class UserSession {
    private static UserSession instance;

    // Almacena la información del usuario logueado
    private Cliente cliente;

    private UserSession() {
        // Constructor privado para asegurar que solo haya una instancia
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    protected void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected boolean isClienteLoggedIn() {
        return cliente != null;
    }

    protected void logout() {
        cliente = null; // Cierra la sesión
    }
}