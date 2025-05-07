package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class Auditor implements Observer{

    public void actualizar(String tipoEvento, String mensaje) {
        System.out.println("[AUDITOR] a evento: " + tipoEvento + " mensaje: " + mensaje);
    }
}
