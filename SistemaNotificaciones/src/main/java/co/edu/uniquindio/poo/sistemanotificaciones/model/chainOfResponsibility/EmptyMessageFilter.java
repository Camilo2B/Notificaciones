package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;

public class EmptyMessageFilter extends NotificationFilter {

    @Override
    protected boolean check(Notification notification) {
        if (notification.getMessage() == null || notification.getMessage().isBlank()) {
            System.out.println("❌ Filtro fallido: El mensaje está vacío.");
            return false;
        }
        return true;
    }

}


