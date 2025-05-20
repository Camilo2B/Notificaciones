module co.edu.uniquindio.poo.sistemanotificaciones {
    requires javafx.controls;
    requires javafx.fxml;

    exports co.edu.uniquindio.poo.sistemanotificaciones.model;
    exports co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

    opens co.edu.uniquindio.poo.sistemanotificaciones.ViewController to javafx.fxml;
}