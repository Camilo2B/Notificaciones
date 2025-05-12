package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public interface NotificationStrategy {
    public void sendNotification(User user, String message);
}
