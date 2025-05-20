package co.edu.uniquindio.poo.sistemanotificaciones.model.observer;

public interface Observer {

    void update(EventType eventType, String message);

}