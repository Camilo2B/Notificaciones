package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.AdminUser;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.ClientUser;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.ModeratorUser;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginViewController {

    @FXML
    private TextField correoTxtField;

    @FXML
    private Label lblCorreo;

    // Mapa para almacenar emails de ejemplo (simulando una base de datos)
    private Map<String, String> usuariosRegistrados;

    // Inicializa el controlador
    public void initialize() {
        // Inicializa el mapa con algunos usuarios de ejemplo
        usuariosRegistrados = new HashMap<>();
        usuariosRegistrados.put("admin@gmail.com", "admin");
        usuariosRegistrados.put("moderador@gmail.com", "moderator");
        usuariosRegistrados.put("cliente@gmail.com", "client");
    }

    /**
     * Método para manejar el inicio de sesión y redirigir según el tipo de usuario
     * @param event El evento que desencadena la acción (por ejemplo, un botón)
     */
    @FXML
    public void iniciarSesion(ActionEvent event) {
        String email = correoTxtField.getText().trim();

        if (email.isEmpty()) {
            mostrarError("Por favor ingrese un correo electrónico");
            return;
        }

        // Verifica el tipo de usuario según el email
        String tipoUsuario = verificarTipoUsuario(email);

        if (tipoUsuario != null) {
            abrirVistaPorTipoUsuario(tipoUsuario, event);
        } else {
            mostrarError("Email no reconocido en el sistema");
        }
    }

    /**
     * Verifica el tipo de usuario basado en el email
     * @param email El email ingresado por el usuario
     * @return El tipo de usuario o null si no se reconoce
     */
    private String verificarTipoUsuario(String email) {
        // Usando el mapa de usuarios registrados
        return usuariosRegistrados.get(email);

        // Alternativa: verificar por patrón en el email
        /*
        if (email.contains("admin")) {
            return "admin";
        } else if (email.contains("moderator") || email.contains("moderador")) {
            return "moderator";
        } else if (email.contains("client") || email.contains("cliente")) {
            return "client";
        }
        return null;
        */
    }

    /**
     * Abre la vista correspondiente según el tipo de usuario
     * @param tipoUsuario El tipo de usuario identificado
     * @param event El evento para obtener la ventana actual
     */
    private void abrirVistaPorTipoUsuario(String tipoUsuario, ActionEvent event) {
        try {
            String vistaNombre = "";

            // Determina qué vista cargar según el tipo de usuario
            switch (tipoUsuario) {
                case "admin":
                    vistaNombre = "/views/Admin.fxml";
                    break;
                case "moderator":
                    vistaNombre = "/views/Moderator.fxml";
                    break;
                case "client":
                    vistaNombre = "/views/Client.fxml";
                    break;
                default:
                    vistaNombre = "/views/Welcome.fxml";
                    break;
            }

            // Carga la vista FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaNombre));
            Parent root = loader.load();

            // Configurar el controlador correspondiente si es necesario
            // Por ejemplo, pasar datos del usuario logueado

            // Crea la escena y configura la ventana
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al cargar la vista: " + e.getMessage());
        }
    }

    /**
     * Muestra un mensaje de error
     * @param mensaje El mensaje de error a mostrar
     */
    private void mostrarError(String mensaje) {
        // Puedes mostrar el error en un label de la interfaz
        lblCorreo.setText(mensaje);
        lblCorreo.setStyle("-fx-text-fill: red;");

        // O usar un Alert
        /*
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
        */
    }


}