package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class EmailNotification implements NotificationStrategy {

    public void enviarNotification(String mensaje, String receptor) {
        System.out.println("Enviando Email a" + receptor + ": " + mensaje);


    }
}
