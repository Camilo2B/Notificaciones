package co.edu.uniquindio.poo.sistemanotificaciones.model.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private Map<String, List<Observer>> listeners = new HashMap<>();

    public EventManager(String... tiposEventos) {
        for (String tipoEvento : tiposEventos) {
            this.listeners.put(tipoEvento, new ArrayList<>());
        }
    }

    public void subscribe(String tipoEvento, Observer observer) {
        List<Observer> users = listeners.get(tipoEvento);
        users.add(observer);
        System.out.println("Suscrito a evento: " + tipoEvento);
    }

    public void unsubscribe(String tipoEvento, Observer observer) {
        List<Observer> users = listeners.get(tipoEvento);
        users.remove(observer);
        System.out.println("Desuscrito de evento: " + tipoEvento);
    }

    public void notify(String tipoEvento, String mensaje) {
        List<Observer> users = listeners.get(tipoEvento);
        for (Observer observer : users) {
            observer.actualizar(tipoEvento, mensaje);
        }
    }
}
