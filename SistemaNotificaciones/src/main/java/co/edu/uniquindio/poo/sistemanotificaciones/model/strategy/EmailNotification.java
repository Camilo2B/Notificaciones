package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class EmailNotification implements NotificationStrategy {

    @Override
    public void sendNotification(User user, String message) {
        System.out.println("\uD83D\uDCE7 Enviando Email a" + user.getEmail() + ": " + message);
    }

}