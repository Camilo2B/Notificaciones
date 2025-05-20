package co.edu.uniquindio.poo.sistemanotificaciones.model.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager implements Subject{

    private Map<EventType, List<Observer>> observers = new HashMap<>();

    public EventManager() {
        for (EventType event : EventType.values()) {
            observers.put(event, new ArrayList<>());
        }
    }

    @Override
    public void subscribe(EventType eventType, Observer observer) {
        observers.get(eventType).add(observer);
    }

    @Override
    public void unsubscribe(EventType eventType, Observer observer) {
        observers.get(eventType).remove(observer);
    }

    @Override
    public void notify(EventType eventType, String message) {
        for (Observer observer : observers.get(eventType)) {
            observer.update(eventType, message);
        }
    }

}
