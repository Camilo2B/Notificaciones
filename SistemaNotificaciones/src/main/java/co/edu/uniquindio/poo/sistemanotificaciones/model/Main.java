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
        sistema.registrarUsuario("perfilActualizado", admin);
        sistema.registrarUsuario("perfilActualizado", cliente);

        // Agregar otros observadores (Observer)
        sistema.registrarUsuario("perfilActualizado", new Logger());
        sistema.registrarUsuario("perfilActualizado", new Auditor());

        // NOTIFICACIÓN válida
        Notification notifValida = new Notification(cliente, "Tu perfil fue actualizado.", new SMSNotification());

        // NOTIFICACIÓN con mensaje vacío (fallará)
        Notification notifVacia = new Notification(admin, "", new EmailNotification());

        // Filtros (Chain of Responsibility)
        NotificationFilter filtro1 = new EmptyMessageFilter();
        NotificationFilter filtro2 = new BlockedUserFilter();
        filtro1.setNext(filtro2);

        // INVOCADOR de comandos
        NotificationInvoker invoker = new NotificationInvoker();

        // Probar notificación válida
        System.out.println("\n📨 Enviando notificación válida:");
        if (filtro1.apply(notifValida)) {
            NotificationCommand cmd1 = new SendNotificationCommand(notifValida);
            invoker.queueCommand(cmd1);
            invoker.executeCommands();
        } else {
            System.out.println("❌ No se puede enviar la notificación válida.");
        }

        // Probar notificación inválida (mensaje vacío)
        System.out.println("\n📨 Enviando notificación vacía:");
        if (filtro1.apply(notifVacia)) {
            NotificationCommand cmd2 = new SendNotificationCommand(notifVacia);
            invoker.queueCommand(cmd2);
            invoker.executeCommands();
        } else {
            System.out.println("❌ No se puede enviar la notificación vacía.");
        }

        // Ejecutar todos los comandos válidos
        System.out.println("\n▶️ Ejecutando comandos:");
        invoker.executeCommands();

        // Simular evento general usando Observer puro
        sistema.dispararEvento("perfilActualizado", "¡Se actualizó tu información correctamente!");
    }
}