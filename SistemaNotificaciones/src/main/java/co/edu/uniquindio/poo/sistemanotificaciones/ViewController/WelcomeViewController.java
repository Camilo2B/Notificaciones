package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomeViewController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), rootPane);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
                    Parent loginRoot = loader.load();

                    Scene scene = new Scene(loginRoot);
                    Stage stage = (Stage) rootPane.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Inicio de Sesión");
                    stage.show();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            fadeOut.play();
        });
        pause.play();
    }
}