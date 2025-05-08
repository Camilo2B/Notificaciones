package co.edu.uniquindio.poo.sistemanotificaciones.model;

import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.Observer;

public class Logger implements Observer {

    public void actualizar(String tipoEvento, String mensaje) {
        System.out.println("[LOG] a evento: " + tipoEvento + " mensaje: " + mensaje);
    }
}
