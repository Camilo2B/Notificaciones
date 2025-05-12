package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;

public abstract class NotificationFilter {

    protected NotificationFilter next;

    public void setNext(NotificationFilter next) {
        this.next = next;
    }

    public abstract void check(Notification notification);

}

