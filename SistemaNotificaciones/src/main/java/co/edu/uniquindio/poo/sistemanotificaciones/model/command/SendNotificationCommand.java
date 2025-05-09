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

    @Override
    public void undo() {
        if (sent) {
            System.out.println("↩️ Se ha revertido el envío de notificación a " + notification.getUser().getName());
            // Aquí puedes agregar lógica como: eliminar de bitácora, marcar como cancelada, etc.
            sent = false;
        } else {
            System.out.println("⚠️ No se puede deshacer: la notificación aún no fue enviada.");
        }
    }
}
