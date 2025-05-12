package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;


public class NotificationFilterChain {

    private NotificationFilter first;

    public void addFilter(NotificationFilter filter) {
        if (first == null) {
            first = filter;
        } else {
            NotificationFilter current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.setNext(filter);
        }
    }

    public void process(Notification notification) {
        if (first != null) {
            first.check(notification);
        }
    }

}
