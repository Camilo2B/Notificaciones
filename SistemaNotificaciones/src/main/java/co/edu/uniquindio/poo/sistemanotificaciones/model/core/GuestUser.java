package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;

public class GuestUser extends User {
    public GuestUser(String name, String contact, NotificationStrategy strategy) {
        super(name, contact, strategy);
    }

    protected String formatMessage(String message) {
        return "[Invitado] " + message;
    }

    @Override
    public void actualizar(String tipoEvento, String mensaje) {

    }
}
