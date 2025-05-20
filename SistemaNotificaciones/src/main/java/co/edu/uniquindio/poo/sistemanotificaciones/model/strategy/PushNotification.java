package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class PushNotification implements NotificationStrategy {

    @Override
    public void send(User user, String message) {
        user.receiveNotification("[\uD83D\uDD14 PUSH] " + message);
    }

}