package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;

public class EmailNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("\uD83D\uDCE7 Enviando Email a" + recipient + ": " + message);
    }

}