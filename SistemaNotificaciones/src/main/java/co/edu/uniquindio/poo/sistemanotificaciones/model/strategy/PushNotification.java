package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class PushNotification implements NotificationStrategy {

    @Override
    public void sendNotification(User user, String message) {
        System.out.println("\uD83D\uDD14 Enviando Push a" + user.getName() + ": " + message);
    }

}