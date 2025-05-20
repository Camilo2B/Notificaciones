package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;

public class EmptyMessageFilter extends NotificationFilter {

    @Override
    public boolean apply(Notification notification) {
        String message = notification.getMessage();
        if (message == null || message.trim().isEmpty()) {
            System.out.println("❌ Notificación cancelada: mensaje vacío.");
            return false;
        }
        return super.apply(notification);
    }

}


