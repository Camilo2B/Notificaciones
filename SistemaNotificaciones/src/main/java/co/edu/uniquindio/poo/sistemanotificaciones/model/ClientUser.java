package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class ClientUser extends User {
    public ClientUser(String name, String contact, NotificationStrategy strategy) {
        super(name, contact, strategy);
    }

    protected String formatMessage(String message) {
        return "[Cliente] " + message;
    }

    @Override
    public void actualizar(String tipoEvento, String mensaje) {

    }
}
