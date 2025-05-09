package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;

public interface NotificationStrategy {
    void sendNotification(String recipient, String message);
}
