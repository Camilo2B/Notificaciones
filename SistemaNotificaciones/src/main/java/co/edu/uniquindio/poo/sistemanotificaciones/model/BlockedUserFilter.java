package co.edu.uniquindio.poo.sistemanotificaciones.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BlockedUserFilter extends NotificationFilter {
    private static final Set<String> blockedUsers = new HashSet<>(Arrays.asList("Blocked@example.com","555-000-222", "blocked_device"));

    @Override
    public boolean filter(Notification notification) {
        if(blockedUsers.contains(notification.getRecipient())) {
            System.out.println("Blocked user: " + notification.getRecipient());
        }
    }
}
