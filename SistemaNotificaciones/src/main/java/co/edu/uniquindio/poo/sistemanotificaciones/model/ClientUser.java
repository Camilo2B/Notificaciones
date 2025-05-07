package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class ClientUser extends User {
    public ClientUser(String name, String contact, NotificationStrategy strategy) {
        super(name, contact, strategy);
    }

    @Override
    protected String formatMessage(String eventType, String message) {
        return "[Notificación para Cliente] " + message + " (" + eventType + ")";
    }

    @Override
    public void actualizar(String tipoEvento, String mensaje) {

    }
}
