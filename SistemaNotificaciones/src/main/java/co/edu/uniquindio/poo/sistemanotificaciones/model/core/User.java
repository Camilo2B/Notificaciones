package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.Observer;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;

public abstract class User implements Observer {
    protected String name;
    protected String email;
    protected NotificationStrategy notificationStrategy;

    public User(String name, String email, NotificationStrategy notificationStrategy) {
        this.name = name;
        this.email = email;
        this.notificationStrategy = notificationStrategy;
    }

    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    // Método plantilla que define el esqueleto del algoritmo
    @Override
    public void actualizar(String eventType, String message) {
        String formattedMessage = formatMessage(message);
        // Crear una notificación
        Notification notification = new Notification("contact", formattedMessage, notificationStrategy);

        // Crear la cadena de filtros
        NotificationFilter filter = createFilterChain();

        // Validar la notificación
        if (filter.filter(notification)) {
            // Crear comando
            NotificationCommand command = new SendNotificationCommand(notification);

            // Enviar al invocador
            NotificationInvoker invoker = NotificationInvoker.getInstance();

            // Decidir si es prioritario o no
            if (isPriority(eventType)) {
                invoker.executeCommand(command);
            } else {
                invoker.queueCommand(command);
            }
        }
    }

    // Método para determinar si un evento es prioritario
    protected boolean isPriority(String eventType) {
        return eventType.contains("alert") || eventType.equals("security");
    }

    // Método para crear la cadena de filtros
    protected NotificationFilter createFilterChain() {
        NotificationFilter emptyFilter = new EmptyMessageFilter();
        NotificationFilter blockedFilter = new BlockedUserFilter();

        emptyFilter.setNext(blockedFilter);

        return emptyFilter;
    }

    // Método abstracto que deben implementar las subclases
    protected abstract String formatMessage(String message);
}