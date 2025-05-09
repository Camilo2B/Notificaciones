package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class BlockedUserFilter extends NotificationFilter {

    @Override
    protected boolean check(Notification notification) {
        User user = notification.getUser();
        if (user.isBlocked()) {
            System.out.println("❌ Filtro fallido: El usuario '" + user.getName() + "' está bloqueado.");
            return false;
        }
        return true;
    }

}
