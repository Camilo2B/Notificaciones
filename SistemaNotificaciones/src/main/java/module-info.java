module co.edu.uniquindio.poo.sistemanotificaciones {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.sistemanotificaciones.model to javafx.fxml;
    exports co.edu.uniquindio.poo.sistemanotificaciones.model;
}