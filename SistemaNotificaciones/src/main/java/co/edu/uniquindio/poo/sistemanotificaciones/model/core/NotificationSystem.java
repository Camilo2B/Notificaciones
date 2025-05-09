package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.*;

public class NotificationSystem {

    private EventManager eventManager;

    /**
     * Registra un observador (usuario, logger, auditor...) a un tipo de evento.
     */
    public void registrarUsuario(String evento, EventListener listener) {
        eventManager.subscribe(evento, listener);
    }

    /**
     * Elimina un observador de un tipo de evento.
     */
    public void eliminarUsuario(String evento, EventListener listener) {
        eventManager.unsubscribe(evento, listener);
    }

    /**
     * Dispara un evento con un mensaje para todos los suscriptores.
     */
    public void dispararEvento(String evento, String mensaje) {
        System.out.println("📢 Disparando evento '" + evento + "'...");
        eventManager.notifySubscribers(evento, mensaje);
    }
}