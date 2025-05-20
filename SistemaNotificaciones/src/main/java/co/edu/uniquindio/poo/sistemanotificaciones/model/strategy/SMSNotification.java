package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class SMSNotification implements NotificationStrategy {

    @Override
    public void send(User user, String message) {
        user.receiveNotification("[\uD83D\uDCF1 SMS] " + message);
    }

}