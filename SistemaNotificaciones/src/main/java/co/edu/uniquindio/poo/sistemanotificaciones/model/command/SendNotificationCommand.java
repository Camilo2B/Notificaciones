package co.edu.uniquindio.poo.sistemanotificaciones.model.command;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.Notification;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;

public class SendNotificationCommand implements NotificationCommand {

    private Notification notification;

    public SendNotificationCommand(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void execute() {
        notification.send();
    }

}
