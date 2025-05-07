package co.edu.uniquindio.poo.sistemanotificaciones.model;

public abstract class User implements Observer {
    protected String name;
    protected String email;
    protected NotificationStrategy notificationStrategy;

    public User(String name, String email, NotificationStrategy notificationStrategy) {
        this.name = name;
        this.email = email;
        this.notificationStrategy = notificationStrategy;
    }

    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    // Método plantilla que define el esqueleto del algoritmo

    public void update(String eventType, String message) {
        String formattedMessage = formatMessage(eventType, message);
        notificationStrategy.enviarNotification(formattedMessage, email);
    }

    // Método que deben implementar las subclases (parte del patrón Template Method)
    protected abstract String formatMessage(String eventType, String message);
}
