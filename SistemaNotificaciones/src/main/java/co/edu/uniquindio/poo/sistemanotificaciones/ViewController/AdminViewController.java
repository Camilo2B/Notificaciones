package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.ModeratorUser;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.NotificationSystem;
import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.EventType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminViewController {

    // Elementos de la interfaz definidos en el FXML
    @FXML
    private Button btnDeleteNotification;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPublish;

    @FXML
    private ComboBox<EventType> boxEventType;

    @FXML
    private TextField txfEventMessage;

    @FXML
    private ListView<String> ListViewNotifications;

    @FXML
    private ListView<String> ListViewAdmins;

    // Sistema de notificaciones
    private NotificationSystem notificationsSystem;

    // Administrador actual
    private ModeratorUser currentAdmin;

    // Lista observable para las notificaciones
    private ObservableList<String> notificationsList;

    // Lista observable para los administradores
    private ObservableList<String> adminsList;

    /**
     * Establece el administrador actual
     */
    public void setCurrentAdmin(ModeratorUser admin) {
        this.currentAdmin = admin;
        System.out.println("setCurrentAdmin called with admin: " + (admin != null ? admin.getEmail() : "null"));
    }

    /**
     * Inicializa el controlador
     */
    @FXML
    public void initialize() {
        System.out.println("Inicializando AdminViewController...");

        try {
            // Inicializar el sistema de notificaciones
            System.out.println("Inicializando NotificationSystem...");
            notificationsSystem = NotificationSystem.getInstance(currentAdmin);
            System.out.println("NotificationSystem inicializado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al inicializar NotificationSystem: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Error de inicialización",
                    "No se pudo inicializar el sistema de notificaciones: " + e.getMessage());
            return;
        }

        // Verificar que los elementos del FXML estén inicializados
        if (ListViewAdmins == null || ListViewNotifications == null || boxEventType == null || txfEventMessage == null) {
            System.out.println("Error: Uno o más elementos del FXML no están inicializados.");
            showAlert(Alert.AlertType.ERROR, "Error de interfaz", "No se pudieron cargar los elementos de la interfaz.");
            return;
        }

        // Inicializar la lista observable para notificaciones
        System.out.println("Inicializando notificationsList...");
        notificationsList = FXCollections.observableArrayList();
        ListViewNotifications.setItems(notificationsList);

        // Inicializar la lista observable para administradores
        System.out.println("Inicializando adminsList...");
        adminsList = FXCollections.observableArrayList();
        ListViewAdmins.setItems(adminsList);

        // Inicializar el ComboBox con los tipos de eventos
        try {
            System.out.println("Cargando tipos de eventos en ComboBox...");
            boxEventType.setItems(FXCollections.observableArrayList(EventType.values()));
            System.out.println("Tipos de eventos cargados: " + EventType.values().length);
        } catch (Exception e) {
            System.out.println("Error al cargar tipos de eventos: " + e.getMessage());
            showAlert(Alert.AlertType.WARNING, "Error en ComboBox",
                    "No se pudieron cargar los tipos de eventos: " + e.getMessage());
        }

        // Cargar administradores de ejemplo
        System.out.println("Cargando administradores...");
        loadAdmins();
        System.out.println("Administradores cargados: " + adminsList.size());

        // Cargar notificaciones iniciales
        System.out.println("Cargando notificaciones...");
        loadNotifications();
        System.out.println("Notificaciones cargadas: " + notificationsList.size());
    }

    /**
     * Carga una lista de administradores de ejemplo en el ListViewAdmins
     */
    private void loadAdmins() {
        // Ejemplos de administradores
        List<ModeratorUser> adminExamples = new ArrayList<>();
        adminExamples.add(new ModeratorUser("Oscar", "cliente@gmail.com", "1234567890"));
        adminExamples.add(new ModeratorUser("Mauro", "admin@gmail.com", "0987654321"));
        adminExamples.add(new ModeratorUser("Julian", "moderador@gmail.com", "1122334455"));

        // Convertir a una lista de cadenas para mostrar en el ListView
        adminsList.clear();
        for (ModeratorUser admin : adminExamples) {
            adminsList.add(admin.getName() + " (" + admin.getEmail() + ")");
        }
    }

    private void loadNotifications() {
        // Ejemplos de notificaciones
        List<String> notificationExamples = new ArrayList<>();
        notificationExamples.add("Ofertas especiales los martes");
        notificationExamples.add("Mantenimiento del sistema hoy a las 2:00 a.m.");

        notificationsList.clear();
        for (String notification : notificationExamples) {
            notificationsList.add(notification);
        }
    }

    /**
     * Manejador para eliminar notificación seleccionada
     */
    @FXML
    public void onDeleteNotification() {
        if (currentAdmin.isBlocked()) {
            showAlert(Alert.AlertType.WARNING, "Usuario bloqueado",
                    "No puede eliminar notificaciones porque su cuenta está bloqueada.");
            return;
        }

        int selectedIndex = ListViewNotifications.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            boolean confirmed = showConfirmDialog("Eliminar notificación",
                    "¿Está seguro de que desea eliminar esta notificación?");

            if (confirmed) {
                try {
                    currentAdmin.deleteNotification(selectedIndex);
                    notificationsList.remove(selectedIndex);
                    showAlert(Alert.AlertType.INFORMATION, "Notificación eliminada",
                            "La notificación ha sido eliminada correctamente.");
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error al eliminar",
                            "No se pudo eliminar la notificación: " + e.getMessage());
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Selección requerida",
                    "Por favor, seleccione una notificación para eliminar.");
        }
    }

    /**
     * Manejador para cerrar sesión
     */
    @FXML
    public void onLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al cerrar sesión",
                    "Error al cargar la vista de inicio de sesión: " + e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error inesperado",
                    "Ocurrió un error al cerrar sesión: " + e.getMessage());
        }
    }

    /**
     * Manejador para publicar un evento
     */
    @FXML
    public void onPublish() {
        EventType selectedEventType = boxEventType.getSelectionModel().getSelectedItem();
        String message = txfEventMessage.getText();

        if (selectedEventType == null) {
            showAlert(Alert.AlertType.WARNING, "Selección requerida",
                    "Por favor, seleccione un tipo de evento.");
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Mensaje requerido",
                    "Por favor, ingrese un mensaje para el evento.");
            return;
        }

        try {
            notificationsSystem.notifyEvent(selectedEventType, message);
            showAlert(Alert.AlertType.INFORMATION, "Evento publicado",
                    "El evento ha sido publicado correctamente.");
            txfEventMessage.clear();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error al publicar",
                    "No se pudo publicar el evento: " + e.getMessage());
        }
    }

    /**
     * Muestra un diálogo de alerta
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra un diálogo de confirmación
     */
    private boolean showConfirmDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}