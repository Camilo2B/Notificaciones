package co.edu.uniquindio.poo.sistemanotificaciones.model;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.command.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.*;
import co.edu.uniquindio.poo.sistemanotificaciones.model.chainOfResponsibility.*;

public class Main {
    public static void main(String[] args) {
        // Crear el sistema de notificaciones (gestiona eventos)
        NotificationSystem sistema = new NotificationSystem();

        // Crear usuarios
        User admin = new AdminUser("Ana", "001", "3001112233", "ana@empresa.com");
        User cliente = new ClientUser("Luis", "002", "3002223344", "luis@email.com");

        // Asignar estrategias de envío
        admin.setNotificationStrategy(new EmailNotification());
        cliente.setNotificationStrategy(new SMSNotification());

        // Suscribir usuarios al evento "perfilActualizado"
        sistema.subscribe("Actualización de perfil", admin);
        sistema.subscribe("Actualización de perfil", cliente);

        // Agregar otros observadores (Observer)
        sistema.subscribe("Actualización de perfil", new Logger());
        sistema.subscribe("Actualización de perfil", new Auditor());

        // NOTIFICACIÓN válida
        Notification notifValida = new Notification(cliente, "Tu perfil fue actualizado.", new SMSNotification());

        // NOTIFICACIÓN con mensaje vacío (fallará)
        Notification notifVacia = new Notification(admin, "", new EmailNotification());

        // Filtros (Chain of Responsibility)
        NotificationFilterChain chain = new NotificationFilterChain();
        chain.addFilter(new EmptyMessageFilter());
        chain.addFilter(new BlockedUserFilter());

        // INVOCADOR de comandos
        NotificationInvoker invoker = new NotificationInvoker();

        // Probar notificación válida
        System.out.println("\n📨 Enviando notificación válida:");
        NotificationCommand cmd1 = new SendNotificationCommand(notifValida);
        invoker.addCommand(cmd1);
        invoker.executeCommands();


        // Probar notificación inválida (mensaje vacío)
        System.out.println("\n📨 Enviando notificación vacía:");
        NotificationCommand cmd2 = new SendNotificationCommand(notifVacia);
        invoker.addCommand(cmd2);
        invoker.executeCommands();

        // Ejecutar todos los comandos válidos
        System.out.println("\n▶️ Ejecutando comandos:");
        invoker.executeCommands();

        // Simular evento general usando Observer puro
        sistema.notifySubscribers("perfilActualizado", "¡Se actualizó tu información correctamente!");
    }
}