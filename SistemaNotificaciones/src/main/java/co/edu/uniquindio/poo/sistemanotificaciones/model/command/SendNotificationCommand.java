package co.edu.uniquindio.poo.sistemanotificaciones.model.command;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class SendNotificationCommand implements NotificationCommand {

    private Notification notification;
    private boolean sent = false;

    public SendNotificationCommand(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void execute() {
        User user = notification.getUser();
        user.setNotificationStrategy(notification.getStrategy());
        user.sendNotification(notification.getMessage());
        sent = true;
    }

}
