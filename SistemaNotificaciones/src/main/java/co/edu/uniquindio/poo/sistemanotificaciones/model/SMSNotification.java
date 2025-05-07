package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class SMSNotification implements NotificationStrategy {

    public void enviarNotification(String message, String recipient) {
        System.out.println("Enviando SMS a " + recipient + ": " + message);
    }
}


