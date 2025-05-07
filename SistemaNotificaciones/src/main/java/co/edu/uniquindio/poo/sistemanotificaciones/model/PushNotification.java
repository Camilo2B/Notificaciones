package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class PushNotification implements NotificationStrategy {

    public void enviarNotification(String mensaje, String receptor) {
        System.out.println("Enviando Push a" + receptor + ": " + mensaje);

    }
}
