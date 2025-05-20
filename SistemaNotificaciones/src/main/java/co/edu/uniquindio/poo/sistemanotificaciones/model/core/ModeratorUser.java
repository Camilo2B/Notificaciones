package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

public class ModeratorUser extends User {

    public ModeratorUser(String name, String email, String phone) {
        super(name, email, phone);
    }

    @Override
    protected String getHeader() {
        return "[MODERADOR] (ALERTA): ";
    }

    @Override
    protected String getBody(String message) {
        return message;
    }

    @Override
    protected String getFooter() {
        return "Favor realizar una revisión lo más pronto posible.";
    }

    // Only receives notifications via moderation filter.

    public boolean blockUser(NotificationSystem system, String email) {
        return system.blockUser(email);
    }

    public boolean unblockUser(NotificationSystem system, String email) {
        return system.unblockUser(email);
    }
}