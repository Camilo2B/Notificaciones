package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;

public class SMSNotification implements NotificationStrategy {

    public void sendNotification(String message, String recipient) {
        System.out.println("Enviando SMS a " + recipient + ": " + message);
    }
}


