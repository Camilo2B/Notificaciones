package co.edu.uniquindio.poo.sistemanotificaciones.model;

public interface NotificationStrategy {
    public void enviarNotification(String mensaje, String receptor);
}
