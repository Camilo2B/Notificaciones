package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class AdminUser extends User {
    public AdminUser(String name, String email, NotificationStrategy notificationStrategy) {
        super(name, email, notificationStrategy);

    }

    @Override
    protected String formatMessage(String eventType, String message) {
        return "[AdminUser] " + eventType.toUpperCase() + ": " + message;
    }


    @Override
    public void actualizar(String tipoEvento, String mensaje) {

    }
}
