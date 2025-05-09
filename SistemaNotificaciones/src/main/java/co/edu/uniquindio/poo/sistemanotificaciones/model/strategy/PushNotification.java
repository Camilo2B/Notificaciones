package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;

public class PushNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("\uD83D\uDD14 Enviando Push a" + recipient + ": " + message);
    }

}