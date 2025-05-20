package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.*;

public class AdminUser extends User {

    public AdminUser(String name, String email, String phone) {
        super(name, email, phone);
    }

    @Override
    protected String getHeader() {
        return "[ADMIN] Notificación importante para " + name ;
    }

    @Override
    protected String getBody(String message) {
        return "Contenido del mensaje: " + message;
    }

    @Override
    protected String getFooter() {
        return "Atentamente, el equipo de soporte.";
    }

    public void publishEvent(NotificationSystem system, EventType eventType, String message) {
        system.notifyEvent(eventType, message);
    }

}