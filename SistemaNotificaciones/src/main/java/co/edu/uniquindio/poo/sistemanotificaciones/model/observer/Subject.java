package co.edu.uniquindio.poo.sistemanotificaciones.model.observer;

public interface Subject {
    void subscribe(EventType eventType, Observer observer);
    void unsubscribe(EventType eventType, Observer observer);
    void notify(EventType eventType, String message);
}
