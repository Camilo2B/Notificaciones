package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class Notification {
    private String recipient;
    private String message;
    private NotificationStrategy strategy;

    public Notification(String recipient, String message, NotificationStrategy strategy) {
        this.recipient = recipient;
        this.message = message;
        this.strategy = strategy;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStrategy getStrategy() {
        return strategy;
    }

    public void send() {
        strategy.enviarNotification(message, recipient);
    }
}
