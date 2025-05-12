package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class BlockedUserFilter extends NotificationFilter {

    @Override
    public void check(Notification notification) {
        if (notification.getUser().isBlocked()) {
            System.out.println("❌ Notificación cancelada: El usuario '" + notification.getUser().getName() +
                                "' se encuentra bloqueado.");
        } else if (next != null) {
           next.check(notification);
        }
    }

}
