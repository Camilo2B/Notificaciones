package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class GuestUser extends User {
    public GuestUser(String name, String contact, NotificationStrategy strategy) {
        super(name, contact, strategy);
    }

    @Override
    protected String formatMessage(String eventType, String message) {
        return "[Invitado] " + message;
    }

    @Override
    public void actualizar(String tipoEvento, String mensaje) {

    }
}
