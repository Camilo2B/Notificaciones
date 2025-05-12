package co.edu.uniquindio.poo.sistemanotificaciones.model.command;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NotificationInvoker {

    private static NotificationInvoker instance;
    private Queue<NotificationCommand> queue = new LinkedList<>();

    public NotificationInvoker() {}

    public static NotificationInvoker getInstance() {
        if (instance == null) {
            instance = new NotificationInvoker();
        }
        return instance;
    }

    public void addCommand(NotificationCommand command) {
        queue.add(command);
        System.out.println("Comando agregado a la cola");
    }

    public void executeCommands() {
        for(NotificationCommand command : queue) {
            command.execute();
        }
        queue.clear();

        System.out.println("Comandos ejecutados exitosamente");
    }

}
