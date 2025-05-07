package co.edu.uniquindio.poo.sistemanotificaciones.model;

public class Logger implements Observer{

    public void actualizar(String tipoEvento, String mensaje) {
        System.out.println("[LOG] a evento: " + tipoEvento + " mensaje: " + mensaje);
    }
}
