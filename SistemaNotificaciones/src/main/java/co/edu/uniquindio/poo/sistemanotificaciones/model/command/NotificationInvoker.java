package co.edu.uniquindio.poo.sistemanotificaciones.model.command;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NotificationInvoker {

    private static NotificationInvoker instance;
    private Queue<NotificationCommand> commandQueue = new LinkedList<>();
    private List<NotificationCommand> commandHistory = new ArrayList<>();

    public NotificationInvoker() {}

    public static NotificationInvoker getInstance() {
        if (instance == null) {
            instance = new NotificationInvoker();
        }
        return instance;
    }

    public void queueCommand(NotificationCommand command) {
        commandQueue.add(command);
        System.out.println("Comando agregado a la cola");
    }

    public void executeCommands() {
        for(NotificationCommand command : commandQueue) {
            command.execute();
            commandHistory.add(command);
        }
        commandQueue.clear();

        System.out.println("Comandos ejecutados exitosamente");
    }

}
