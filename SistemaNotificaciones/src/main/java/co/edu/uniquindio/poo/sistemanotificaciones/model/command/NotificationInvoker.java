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

    public void executeCommand(NotificationCommand command) {
        command.execute();
        commandHistory.add(command);
        System.out.println("Comando ejecutado inmediatamente");
    }

    public void processQueue() {
        System.out.println("Procesando cola de comandos...");
        NotificationCommand command;
        while ((command = commandQueue.poll()) != null) {
            command.execute();
            commandHistory.add(command);
        }
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            NotificationCommand command = commandHistory.remove(commandHistory.size() - 1);
            command.undo();
            System.out.println("Se deshizo el último comando");
        }
    }
}
