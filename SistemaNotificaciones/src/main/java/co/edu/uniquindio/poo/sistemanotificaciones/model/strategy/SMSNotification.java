package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class SMSNotification implements NotificationStrategy {

    @Override
    public void sendNotification(User user, String message) {
        System.out.println("\uD83D\uDCF1 Enviando SMS a " + user.getName() + ": " + message);
    }

}