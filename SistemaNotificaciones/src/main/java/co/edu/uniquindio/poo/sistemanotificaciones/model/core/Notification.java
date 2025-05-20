package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;

public class Notification {
    private final User user;
    private final String message;
    private final NotificationStrategy strategy;

    public Notification(User user, String message, NotificationStrategy strategy) {
        this.user = user;
        this.message = message;
        this.strategy = strategy;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStrategy getStrategy() {
        return strategy;
    }

    public void send() {
        String formatted = user.formatMessage(message);
        strategy.send(user, formatted);
    }
}
