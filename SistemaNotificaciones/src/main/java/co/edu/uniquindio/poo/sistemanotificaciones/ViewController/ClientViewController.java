package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.NotificationSystem;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;
import co.edu.uniquindio.poo.sistemanotificaciones.model.observer.EventType;
import co.edu.uniquindio.poo.sistemanotificaciones.model.strategy.*;
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
import java.util.Optional;

public class ClientViewController {

    // Elementos de la interfaz definidos en el FXML
    @FXML
    private Button btnDeleteNotification;

    @FXML
    private Button btnLogout;

    @FXML
    private ListView<String> ListViewNotifications;

    @FXML
    private CheckBox checkSMS;

    @FXML
    private CheckBox checkEmail;

    @FXML
    private CheckBox checkPush;

    @FXML
    private CheckBox checkPromotions;

    @FXML
    private CheckBox checkSecurityAlerts;

    @FXML
    private CheckBox checkMaintenance;

    @FXML
    private CheckBox checkProfileUpdates;

    // Sistema de notificaciones
    private NotificationSystem notificationsSystem;

    // Cliente actual
    private User currentClient;

    // Lista observable para las notificaciones
    private ObservableList<String> notificationsList;

    /**
     * Establece el cliente actual
     */
    public void setCurrentClient(User client) {
        this.currentClient = client;
    }

    /**
     * Inicializa el controlador
     */
    @FXML
    public void initialize() {
        // Verificar que el cliente esté establecido
        if (currentClient == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No se ha establecido un cliente.");
            return;
        }

        // Inicializar el sistema de notificaciones
        notificationsSystem = NotificationSystem.getInstance(null);

        // Inicializar la lista observable para notificaciones
        notificationsList = FXCollections.observableArrayList();
        ListViewNotifications.setItems(notificationsList);

        // Cargar notificaciones iniciales
        loadNotifications();

        // Configurar estado inicial de los CheckBox según las suscripciones y estrategia del cliente
        initializeCheckBoxes();
    }

    /**
     * Carga las notificaciones del cliente en la lista
     */
    private void loadNotifications() {
        notificationsList.clear();
        if (currentClient != null) {
            notificationsList.addAll(currentClient.getInbox());
        }
    }

    /**
     * Configura el estado inicial de los CheckBox según las suscripciones y estrategia del cliente
     */
    private void initializeCheckBoxes() {
        // Configurar CheckBox de estrategias de notificación
        NotificationStrategy strategy = currentClient.getStrategy();
        if (strategy instanceof SMSNotification) {
            checkSMS.setSelected(true);
        } else if (strategy instanceof EmailNotification) {
            checkEmail.setSelected(true);
        } else if (strategy instanceof PushNotification) {
            checkPush.setSelected(true);
        }

        // Configurar CheckBox de suscripciones a eventos
        checkPromotions.setSelected(notificationsSystem.getEventManager().isSubscribed(currentClient, EventType.PROMOTION));
        checkSecurityAlerts.setSelected(notificationsSystem.getEventManager().isSubscribed(currentClient, EventType.SECURITY_ALERT));
        checkMaintenance.setSelected(notificationsSystem.getEventManager().isSubscribed(currentClient, EventType.SYSTEM_MAINTENANCE));
        checkProfileUpdates.setSelected(notificationsSystem.getEventManager().isSubscribed(currentClient, EventType.PROFILE_UPDATE));
    }

    /**
     * Manejador para eliminar notificación seleccionada
     */
    @FXML
    public void onDeleteNotification() {
        int selectedIndex = ListViewNotifications.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            boolean confirmed = showConfirmDialog("Eliminar notificación",
                    "¿Está seguro de que desea eliminar esta notificación?");

            if (confirmed) {
                currentClient.deleteNotification(selectedIndex);
                notificationsList.remove(selectedIndex);
                showAlert(Alert.AlertType.INFORMATION, "Notificación eliminada",
                        "La notificación ha sido eliminada correctamente.");
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
            showAlert(Alert.AlertType.ERROR, "Error",
                    "Error al cargar la vista de inicio de sesión: " + e.getMessage());
        }
    }

    /**
     * Manejador para el CheckBox de SMS
     */
    @FXML
    public void onCheckSMS() {
        if (checkSMS.isSelected()) {
            currentClient.setStrategy(new SMSNotification());
            checkEmail.setSelected(false);
            checkPush.setSelected(false);
            showAlert(Alert.AlertType.INFORMATION, "Estrategia actualizada",
                    "Notificaciones configuradas para enviarse por SMS.");
        } else if (!checkEmail.isSelected() && !checkPush.isSelected()) {
            checkSMS.setSelected(true); // Evitar que todos los CheckBox queden desmarcados
            showAlert(Alert.AlertType.WARNING, "Selección requerida",
                    "Debe seleccionar al menos un método de notificación.");
        }
    }

    /**
     * Manejador para el CheckBox de Email
     */
    @FXML
    public void onCheckEmail() {
        if (checkEmail.isSelected()) {
            currentClient.setStrategy(new EmailNotification());
            checkSMS.setSelected(false);
            checkPush.setSelected(false);
            showAlert(Alert.AlertType.INFORMATION, "Estrategia actualizada",
                    "Notificaciones configuradas para enviarse por Email.");
        } else if (!checkSMS.isSelected() && !checkPush.isSelected()) {
            checkEmail.setSelected(true); // Evitar que todos los CheckBox queden desmarcados
            showAlert(Alert.AlertType.WARNING, "Selección requerida",
                    "Debe seleccionar al menos un método de notificación.");
        }
    }

    /**
     * Manejador para el CheckBox de Push
     */
    @FXML
    public void onCheckPush() {
        if (checkPush.isSelected()) {
            currentClient.setStrategy(new PushNotification());
            checkSMS.setSelected(false);
            checkEmail.setSelected(false);
            showAlert(Alert.AlertType.INFORMATION, "Estrategia actualizada",
                    "Notificaciones configuradas para enviarse por Push.");
        } else if (!checkSMS.isSelected() && !checkEmail.isSelected()) {
            checkPush.setSelected(true); // Evitar que todos los CheckBox queden desmarcados
            showAlert(Alert.AlertType.WARNING, "Selección requerida",
                    "Debe seleccionar al menos un método de notificación.");
        }
    }

    /**
     * Manejador para el CheckBox de Promociones
     */
    @FXML
    public void onCheckPromotions() {
        if (checkPromotions.isSelected()) {
            notificationsSystem.subscribeUserToEvent(currentClient.getEmail(), EventType.PROMOTION);
            showAlert(Alert.AlertType.INFORMATION, "Suscripción actualizada",
                    "Se ha suscrito a notificaciones de promociones.");
        } else {
            notificationsSystem.getEventManager().unsubscribe(EventType.PROMOTION, currentClient);
            showAlert(Alert.AlertType.INFORMATION, "Suscripción actualizada",
                    "Se ha desuscrito de notificaciones de promociones.");
        }
    }

    /**
     * Manejador para el CheckBox de Alertas de Seguridad
     */
    @FXML
    public void onCheckSecurityAlerts() {
        if (checkSecurityAlerts.isSelected()) {
            notificationsSystem.subscribeUserToEvent(currentClient.getEmail(), EventType.SECURITY_ALERT);
            showAlert(Alert.AlertType.INFORMATION, "Suscripción actualizada",
                    "Se ha suscrito a alertas de seguridad.");
        } else {
            notificationsSystem.getEventManager().unsubscribe(EventType.SECURITY_ALERT, currentClient);
            showAlert(Alert.AlertType.INFORMATION, "Suscripción actualizada",
                    "Se ha desuscrito de alertas de seguridad.");
        }
    }

    /**
     * Manejador para el CheckBox de Mantenimiento
     */
    @FXML
    public void onCheckMaintenance() {
        if (checkMaintenance.isSelected()) {
            notificationsSystem.subscribeUserToEvent(currentClient.getEmail(), EventType.SYSTEM_MAINTENANCE);
            showAlert(Alert.AlertType.INFORMATION, "Suscripción actualizada",
                    "Se ha suscrito a notificaciones de mantenimiento.");
        } else {
            notificationsSystem.getEventManager().unsubscribe(EventType.SYSTEM_MAINTENANCE, currentClient);
            showAlert(Alert.AlertType.INFORMATION, "Suscripción actualizada",
                    "Se ha desuscrito de notificaciones de mantenimiento.");
        }
    }

    /**
     * Manejador para el CheckBox de Actualizaciones de Perfil
     */
    @FXML
    public void onCheckProfileUpdates() {
        if (checkProfileUpdates.isSelected()) {
            notificationsSystem.subscribeUserToEvent(currentClient.getEmail(), EventType.PROFILE_UPDATE);
            showAlert(Alert.AlertType.INFORMATION, "Suscripción actualizada",
                    "Se ha suscrito a notificaciones de actualizaciones de perfil.");
        } else {
            notificationsSystem.getEventManager().unsubscribe(EventType.PROFILE_UPDATE, currentClient);
            showAlert(Alert.AlertType.INFORMATION, "Suscripción actualizada",
                    "Se ha desuscrito de notificaciones de actualizaciones de perfil.");
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