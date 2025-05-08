package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;

public class EmailNotification implements NotificationStrategy {

    public void sendNotification(String message, String receptor) {
        System.out.println("Enviando Email a" + receptor + ": " + message);


    }
}
