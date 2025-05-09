package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;

public class SMSNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("\uD83D\uDCF1 Enviando SMS a " + recipient + ": " + message);
    }

}