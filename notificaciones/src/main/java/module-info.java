module co.edu.uniquindio.poo.notificaciones {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.notificaciones to javafx.fxml;
    exports co.edu.uniquindio.poo.notificaciones;
}