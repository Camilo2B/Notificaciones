package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;

public class GuestUser extends User {

    public GuestUser(String name, String email, String phone) {
        super(name, email, phone);
    }

    @Override
    protected String getHeader() {
        return "Hola visitante " + name;
    }

    @Override
    protected String getBody(String message) {
        return "Te informamos: " + message;
    }

    @Override
    protected String getFooter() {
        return "Regístrate para más beneficios.";
    }

    public Notification createProfileUpdateNotification(String customMessage) {
        return new Notification(this, customMessage, strategy);
    }

}