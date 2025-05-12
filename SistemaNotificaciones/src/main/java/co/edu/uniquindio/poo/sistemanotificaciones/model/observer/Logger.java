package co.edu.uniquindio.poo.sistemanotificaciones.model.observer;

public class Logger implements EventListener {

    @Override
    public void notify(String message) {
        System.out.println("📋 [LOG] Evento registrado: " + message);
    }

}
