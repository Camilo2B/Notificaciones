package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;

public class AdminUser extends User {
    public AdminUser(String name, String email, NotificationStrategy notificationStrategy) {
        super(name, email, notificationStrategy);

    }

    protected String formatMessage(String message) {
        return "[AdminUser] " + message.toUpperCase() + ": " + message;
    }

    protected boolean isPriority(String eventType) {
        // Los administradores siempre tienen prioridad
        return true;
    }


}
