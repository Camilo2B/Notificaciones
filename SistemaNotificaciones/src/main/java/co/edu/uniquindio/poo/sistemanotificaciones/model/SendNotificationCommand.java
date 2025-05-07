package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class SendNotificationCommand implements NotificationCommand {
    private Notification notification;
    private boolean sent = false;

    public SendNotificationCommand(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void execute() {
        notification.send();
        sent = true;
    }

    @Override
    public void undo() {
        if (sent) {
            System.out.println("Cancelando notificacion enviada a " + notification.getRecipient());

        }
    }
}
