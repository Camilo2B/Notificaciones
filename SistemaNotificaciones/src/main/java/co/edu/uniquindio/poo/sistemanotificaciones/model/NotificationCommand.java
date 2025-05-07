package co.edu.uniquindio.poo.sistemanotificaciones.model;

public interface NotificationCommand {
    public void execute();
    public void undo();
}
