package co.edu.uniquindio.poo.sistemanotificaciones.model.strategy;

public class PushNotification implements NotificationStrategy {

    public void sendNotification(String message, String receptor) {
        System.out.println("Enviando Push a" + receptor + ": " + message);

    }
}
