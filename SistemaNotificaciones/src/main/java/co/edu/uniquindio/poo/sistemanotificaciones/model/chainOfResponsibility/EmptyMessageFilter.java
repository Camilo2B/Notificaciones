package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;

public class EmptyMessageFilter extends NotificationFilter {

    @Override
    public void check(Notification notification) {
        if (notification.getMessage() == null || notification.getMessage().isEmpty()) {
            System.out.println("❌ Notificación cancelada: mensaje vacío.");
        } else if (next != null) {
            next.check(notification);
        }
    }

}


