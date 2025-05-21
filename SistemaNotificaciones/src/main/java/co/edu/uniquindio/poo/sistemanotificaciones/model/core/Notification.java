package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;

public class Notification {
    private final User user;
    private final String message;

    public Notification(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }


    public void send() {
        String formatted = user.formatMessage(message);
        user.getStrategy().send(user, formatted);
    }
}
