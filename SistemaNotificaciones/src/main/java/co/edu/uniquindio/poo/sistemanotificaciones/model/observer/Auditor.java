package co.edu.uniquindio.poo.sistemanotificaciones.model.observer;

public class Auditor implements EventListener {

    @Override
    public void notify(String message) {
        System.out.println("🔐 [AUDITORÍA] Registro de actividad: " + message);
    }

}
