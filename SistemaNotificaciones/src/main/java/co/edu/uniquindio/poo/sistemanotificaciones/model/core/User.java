package co.edu.uniquindio.poo.sistemanotificaciones.model.core;

import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.EventType;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class User implements Observer {
    protected String name;
    protected String email;
    protected String phone;
    protected boolean isBlocked;
    protected List<String> inbox;
    protected NotificationStrategy strategy;

    //Constructor
    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isBlocked = false;
        this.inbox = new ArrayList<>();
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

    public void receiveNotification(String message) {
        inbox.add(message);
    }

    public List<String> getInbox() {
        return inbox;
    }

    public void deleteNotification(int index) {
        if (index >= 0 && index < inbox.size()) {
            inbox.remove(index);
        }
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public NotificationStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void update(EventType eventType, String message) {
        String formatted = formatMessage(message);
        Notification notification = new Notification(this, formatted, strategy);
        notification.send(); // Strategy used here
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setInbox(List<String> inbox) {
        this.inbox = inbox;
    }
}
