package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.command.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationSystem {

    private EventManager eventManager;
    private Map<String, User> users;
    private NotificationInvoker invoker;
    private NotificationFilter filterChain;

    public NotificationSystem(ModeratorUser moderator) {
        this.eventManager = new EventManager();
        this.users = new HashMap<>();
        this.invoker = new NotificationInvoker();
        this.filterChain = createFilterChain(moderator);
    }

    public void registerUser(User user) {
        users.put(user.getEmail(), user);
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public void subscribeUserToEvent(String email, EventType eventType) {
        User user = users.get(email);
        if (user != null) {
            eventManager.subscribe(eventType, user);
        }
    }

    public void notifyEvent(EventType eventType, String message) {
        eventManager.notify(eventType, message);
    }

    public void sendNotification(Notification notification) {
        if (!filterChain.apply(notification)) {
            return;
        }

        NotificationCommand command = new SendNotificationCommand(notification);
        invoker.addCommand(command);
        invoker.executeCommands();
    }

    private NotificationFilter createFilterChain(ModeratorUser moderator) {
        NotificationFilter empty = new EmptyMessageFilter();
        NotificationFilter blocked = new BlockedUserFilter();
        NotificationFilter moderation = new ModerationFilter(moderator);

        empty.setNext(blocked);
        blocked.setNext(moderation);

        return empty;
    }

}