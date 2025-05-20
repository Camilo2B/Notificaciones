package co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility;

import java.util.Arrays;
import java.util.List;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.*;

public class ModerationFilter extends NotificationFilter {
    private static final List<String> BAD_WORDS = Arrays.asList("idiota", "estupido", "maldicion");

    private ModeratorUser moderator;

    public ModerationFilter(ModeratorUser moderator) {
        this.moderator = moderator;
    }

    @Override
    public boolean apply(Notification notification) {
        String content = notification.getMessage().toLowerCase();
        for (String word : BAD_WORDS) {
            if (content.contains(word)) {
                String alert = "⚠️ Offensive content detected in notification to " +
                        notification.getUser().getEmail() + ": \"" + notification.getMessage() + "\"";
                moderator.receiveNotification(alert);
                break; // still continue chain
            }
        }
        return super.apply(notification);
    }
}
