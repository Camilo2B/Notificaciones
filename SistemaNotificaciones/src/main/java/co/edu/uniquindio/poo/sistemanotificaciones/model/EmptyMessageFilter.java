package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class EmptyMessageFilter extends NotificationFilter {

    public boolean filter(Notification notification) {
        if (notification.getMessage() == null || notification.getMessage().trim().isEmpty()) {
            System.out.println("Filtro: El mensaje está vacío");
            return false;
        }

        return next == null || next.filter(notification);
    }
}


