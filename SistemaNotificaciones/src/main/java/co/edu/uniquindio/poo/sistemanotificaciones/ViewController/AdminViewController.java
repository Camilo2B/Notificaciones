package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminViewController {

    @FXML
    private ComboBox<?> boxEventType;

    @FXML
    private Button btnPublish;

    @FXML
    private Button btnLogout;

    @FXML
    private ListView<?> ListViewNotifications;

    @FXML
    private Button btnDeleteNotification;

    @FXML
    private TextField txfEventMessage;

    @FXML
    void onPublish(ActionEvent event) {

    }

    @FXML
    void onDeleteNotification(ActionEvent event) {

    }

    @FXML
    void onLogout(ActionEvent event) {

    }

}