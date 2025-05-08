package co.edu.uniquindio.poo.sistemanotificaciones.model;

import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.Observer;

public class Auditor implements Observer {

    public void actualizar(String tipoEvento, String mensaje) {
        System.out.println("[AUDITOR] a evento: " + tipoEvento + " mensaje: " + mensaje);
    }
}
