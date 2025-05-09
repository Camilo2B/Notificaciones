package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility.BlockedUserFilter;
import co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility.EmptyMessageFilter;
import co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility.NotificationFilter;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.SMSNotification;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.EventListener;

public abstract class User implements EventListener {
    protected String name;
    protected String id;
    protected String phone;
    protected String email;
    protected NotificationStrategy notificationStrategy;
    protected boolean blocked;

    /**
     * Método Constructor de la clase Usuario
     *
     * @param name
     * @param id
     * @param phone
     * @param email
     */
    public User(String name, String id, String phone, String email) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        blocked = false;
    }

    //=====================================================//

    // Template Method
    public final String formatMessage(String message) {
        String header = getHeader();
        String body = getBody(message);
        String footer = getFooter();
        return header + "\n" + body + "\n" + footer;
    }

    protected abstract String getHeader();

    protected abstract String getBody(String message);

    protected abstract String getFooter();

    //=====================================================//

    // Inyectar la estrategia desde fuera
    public void setNotificationStrategy(NotificationStrategy strategy) {
        this.notificationStrategy = strategy;
    }

    //=====================================================//

    @Override
    public void sendNotification(String message) {
        String formatted = formatMessage(message);
        if (notificationStrategy != null) {
            // Decide qué dato usar como destino según el tipo de canal
            if (notificationStrategy instanceof SMSNotification) {
                notificationStrategy.sendNotification(phone, formatted);
            } else {
                notificationStrategy.sendNotification(email, formatted);
            }
        } else {
            System.out.println("⚠ Estrategia de notificación no configurada para el usuario " + name);
        }
    }

    //=====================================================//

    //=================GETTERS Y SETTERS===================//


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() { return blocked; }

}
