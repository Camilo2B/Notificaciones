package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.command.*;

import java.util.*;

public class NotificationSystem {

    private static NotificationSystem instance; // Instancia única
    private EventManager eventManager;
    private Map<String, User> users;
    private NotificationInvoker invoker;
    private NotificationFilter filterChain;

    // Constructor privado para evitar instanciación directa
    private NotificationSystem(ModeratorUser moderator) {
        this.eventManager = new EventManager();
        this.users = new HashMap<>();
        this.invoker = new NotificationInvoker();
        this.filterChain = createFilterChain(moderator);
    }

    // Método Singleton para obtener la instancia
    public static NotificationSystem getInstance(ModeratorUser moderator) {
        if (instance == null) {
            instance = new NotificationSystem(moderator);
        }
        return instance;
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

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public boolean blockUser(String email) {
        User user = users.get(email);
        if (user != null && !(user instanceof ModeratorUser)) {
            user.setBlocked(true);
            return true;
        }
        return false;
    }

    public boolean unblockUser(String email) {
        User user = users.get(email);
        if (user != null) {
            user.setBlocked(false);
            return true;
        }
        return false;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}