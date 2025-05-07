package co.edu.uniquindio.poo.sistemanotificaciones.model;

public abstract class NotificationFilter {
    protected NotificationFilter next;

    public NotificationFilter setNext(NotificationFilter next) {
        this.next = next;
        return next;
    }

    public abstract boolean filter(Notification notification);
}
}
