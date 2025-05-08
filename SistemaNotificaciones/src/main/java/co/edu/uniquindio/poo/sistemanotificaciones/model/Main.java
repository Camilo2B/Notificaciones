package co.edu.uniquindio.poo.sistemanotificaciones.model;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.AdminUser;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.ClientUser;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.GuestUser;
import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.EventManager;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.EmailNotification;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.NotificationStrategy;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.PushNotification;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.SMSNotification;

public class Main {
    public static void main(String[] args) {
        // Crear el gestor de eventos
        EventManager eventManager = new EventManager(
                "login", "logout", "purchase", "news", "security", "promotion"
        );

        // Crear estrategias de notificación
        NotificationStrategy emailStrategy = new EmailNotification();
        NotificationStrategy smsStrategy = new SMSNotification();
        NotificationStrategy pushStrategy = new PushNotification();

        // Crear usuarios
        AdminUser admin = new AdminUser("Admin1", "admin@example.com", emailStrategy);
        ClientUser client1 = new ClientUser("Cliente1", "555-123-456", smsStrategy);
        GuestUser guest = new GuestUser("Guest1", "guest@example.com", emailStrategy);

        // Crear observadores adicionales
        Logger logger = new Logger();
        Auditor auditor = new Auditor();

        // Suscribir observadores a eventos
        eventManager.subscribe("login", admin);
        eventManager.subscribe("security", admin);

        eventManager.subscribe("purchase", client1);
        eventManager.subscribe("promotion", client1);

        eventManager.subscribe("news", guest);

        // Suscribir logger y auditor a múltiples eventos
        eventManager.subscribe("login", logger);
        eventManager.subscribe("purchase", logger);
        eventManager.subscribe("security", logger);

        eventManager.subscribe("login", auditor);
        eventManager.subscribe("security", auditor);

        System.out.println("\n--- Simulación del Sistema de Notificaciones ---");

        // Simular eventos
        eventManager.notify("login", "Nuevo inicio de sesión desde dispositivo desconocido");
        System.out.println();

        eventManager.notify("purchase", "Nueva compra por $200");
        System.out.println();

        eventManager.notify("security", "Intento de acceso no autorizado");
        System.out.println();

        // Procesar la cola de notificaciones
        NotificationInvoker.getInstance().processQueue();
        System.out.println();

        // Cambiar estrategia de notificación
        System.out.println("Cambiando estrategia de notificación para Cliente1 a Push...");
        client1.setNotificationStrategy(pushStrategy);

        // Enviar otra notificación
        eventManager.notify("promotion", "Oferta especial de fin de semana");

        // Procesar cola nuevamente
        NotificationInvoker.getInstance().processQueue();

        // Deshacer última notificación
        System.out.println("\nCancelando última notificación...");
        NotificationInvoker.getInstance().undoLastCommand();
    }
}