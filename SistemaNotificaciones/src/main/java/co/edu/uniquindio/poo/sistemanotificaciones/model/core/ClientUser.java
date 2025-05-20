package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;

public class ClientUser extends User {

    public ClientUser(String name, String email, String phone) {
        super(name, email, phone);
    }

    @Override
    protected String getHeader() {
        return "Estimado cliente " + name;
    }

    @Override
    protected String getBody(String message) {
        return message;
    }

    @Override
    protected String getFooter() {
        return "Gracias por confiar en nosotros.";
    }

    public Notification createProfileUpdateNotification(String customMessage) {
        return new Notification(this, customMessage, strategy);
    }

}