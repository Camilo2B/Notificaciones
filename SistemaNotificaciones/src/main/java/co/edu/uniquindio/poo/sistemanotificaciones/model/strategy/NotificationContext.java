package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;

public class NotificationContext {

    private NotificationStrategy strategy;

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void sendNotification(String recipient, String message) {
        if (strategy == null) {
            throw new IllegalStateException("No se ha configurado ninguna estrategia de notificación.");
        }
        strategy.sendNotification(recipient, message);
    }

}
