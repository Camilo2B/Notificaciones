package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;

public class AdminUser extends User {

    public AdminUser(String name, String id, String phone, String email) {
        super(name, id, phone, email);
    }

    @Override
    protected String getHeader() {
        return "[ADMIN] Notificación importante para " + name;
    }

    @Override
    protected String getBody(String message) {
        return "Contenido del mensaje: " + message;
    }

    @Override
    protected String getFooter() {
        return "Atentamente, el equipo de soporte.";
    }

}