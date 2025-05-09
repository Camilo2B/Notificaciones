package co.edu.uniquindio.poo.sistemanotificaciones.model.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... events) {
        for (String event : events) {
            listeners.put(event, new ArrayList<>());
        }
    }

    public void subscribe(String evento, EventListener listener) {
        List<EventListener> users = listeners.get(evento);
        if (!users.contains(listener)) {
            users.add(listener);
        }
        System.out.println("Suscrito a evento: " + evento);
    }

    public void unsubscribe(String event, EventListener listener) {
        listeners.get(event).remove(listener);
        System.out.println("Desuscrito de evento: " + event);
    }

    public void notifySubscribers(String event, String message) {
        List<EventListener> users = listeners.get(event);
        System.out.println("🔔 Notificando evento '" + event + "': " + message);
        for (EventListener listener : users) {
            listener.sendNotification(message);
        }
    }

}
