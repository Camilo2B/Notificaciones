package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class Main {
    public static void main(String[] args) {
        // Crear el gestor de eventos con tipos de eventos predefinidos
        EventManager eventManager = new EventManager("login", "logout", "purchase", "news");

        // Crear estrategias de notificación
        NotificationStrategy emailStrategy = new EmailNotification();
        NotificationStrategy smsStrategy = new SMSNotification();
        NotificationStrategy pushStrategy = new PushNotification();

        // Crear usuarios
        AdminUser admin = new AdminUser("Admin1", "admin@example.com", emailStrategy);
        ClientUser client1 = new ClientUser("Cliente1", "555-123-456", smsStrategy);
        ClientUser client2 = new ClientUser("Cliente2", "client2_device_id", pushStrategy);
        GuestUser guest = new GuestUser("Guest1", "guest@example.com", emailStrategy);

        // Suscribir usuarios a diferentes eventos
        eventManager.subscribe("login", admin);
        eventManager.subscribe("logout", admin);
        eventManager.subscribe("purchase", admin);

        eventManager.subscribe("purchase", client1);
        eventManager.subscribe("news", client1);

        eventManager.subscribe("news", client2);

        eventManager.subscribe("news", guest);

        System.out.println("\n--- Simulación de eventos ---");

        // Simular eventos
        eventManager.notify("login", "Nuevo inicio de sesión desde Madrid");
        System.out.println();

        eventManager.notify("purchase", "Se ha realizado una compra de $500");
        System.out.println();

        eventManager.notify("news", "Nuevas funcionalidades disponibles en la plataforma");
        System.out.println();

        // Cambiar estrategia de notificación para un usuario
        System.out.println("Cambiando estrategia de notificación para Cliente1...");
        client1.setNotificationStrategy(pushStrategy);

        eventManager.notify("news", "Descuentos especiales este fin de semana");
        System.out.println();

        // Desuscribir a un usuario
        eventManager.unsubscribe("news", guest);

        eventManager.notify("news", "Mantenimiento programado");
    }
}