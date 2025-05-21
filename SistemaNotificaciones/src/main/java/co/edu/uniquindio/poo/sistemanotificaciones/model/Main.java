package co.edu.uniquindio.poo.sistemanotificaciones.model;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.command.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility.*;

public class Main {
    public static void main(String[] args) {

        ModeratorUser moderador = new ModeratorUser("Henrique", "henrique@soporte.com", "3003333345");

        // Crear el sistema de notificaciones (gestiona eventos)
        NotificationSystem sistema = NotificationSystem.getInstance(moderador);

        // Crear usuarios
        User admin = new AdminUser("Ana", "ana@empresa.com", "3001112233");
        sistema.registerUser(admin);
        User cliente = new ClientUser("Luis", "luis@email.com", "3002223344");
        sistema.registerUser(cliente);

        // Asignar estrategias de envío
        admin.setStrategy(new EmailNotification());
        cliente.setStrategy(new SMSNotification());

        // Suscribir usuarios al evento "perfilActualizado"
        sistema.subscribeUserToEvent("ana@empresa.com", EventType.PROMOTION);
        sistema.subscribeUserToEvent("luis@email.com", EventType.SECURITY_ALERT);

        sistema.getEventManager().subscribe(EventType.PROMOTION, cliente);
        sistema.getEventManager().subscribe(EventType.SECURITY_ALERT, cliente);

        new Notification(cliente, "20% de descuento en electrodomésticos");

        // INVOCADOR de comandos
        NotificationInvoker invoker = new NotificationInvoker();

    }
}