package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class BlockedUserFilter extends NotificationFilter {

    @Override
    public boolean apply(Notification notification) {
        if (notification.getUser().isBlocked()) {
            System.out.println("❌ Notificación cancelada: El usuario '" + notification.getUser().getName() +
                    "' se encuentra bloqueado.");
            return false;
        }
        return super.apply(notification);
    }

}
