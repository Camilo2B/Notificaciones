package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.model.core.ModeratorUser;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.NotificationSystem;
import co.edu.uniquindio.poo.sistemanotificaciones.model.core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ModeratorViewController {

    // Elementos de la interfaz definidos en el FXML
    @FXML
    private Button btnDeleteNotification;

    @FXML
    private Button btnLogout;

    @FXML
    private ListView<String> ListViewNotifications;

    @FXML
    private TableView<User> TableViewUsers;

    @FXML
    private TableColumn<User, String> tbcUserName;

    @FXML
    private TableColumn<User, String> tbcUserEmail;

    @FXML
    private TableColumn<User, String> tbcUserPhone;

    @FXML
    private TableColumn<User, Boolean> tbcIsBlocked;

    @FXML
    private Button btnBlockUser;

    @FXML
    private Button btnUnblockUser;

    // Sistema de notificaciones
    private NotificationSystem notificationsSystem;

    // Moderador actual
    private ModeratorUser currentModerator;

    // Lista observable para los usuarios
    private ObservableList<User> usersList;

    // Lista observable para las notificaciones
    private ObservableList<String> notificationsList;

    /**
     * Establece el moderador actual
     */
    public void setCurrentModerator(ModeratorUser moderator) {
        this.currentModerator = moderator;
    }

    /**
     * Inicializa el controlador
     */
    @FXML
    public void initialize() {
        // Inicializar el sistema de notificaciones
        notificationsSystem = NotificationSystem.getInstance(currentModerator);

        // Configurar columnas de la tabla de usuarios
        tbcUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbcUserPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tbcIsBlocked.setCellValueFactory(new PropertyValueFactory<>("blocked"));

        // Personalizar la columna de "bloqueado" para mostrar Sí/No
        tbcIsBlocked.setCellFactory(column -> new TableCell<User, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item ? "Sí" : "No");
            }
        });

        // Inicializar las listas observables
        usersList = FXCollections.observableArrayList();
        TableViewUsers.setItems(usersList);

        notificationsList = FXCollections.observableArrayList();
        ListViewNotifications.setItems(notificationsList);

        // Cargar datos iniciales
        loadUsers();
        loadNotifications();
    }

    /**
     * Carga los usuarios en la tabla
     */
    private void loadUsers() {
        usersList.clear();
        List<User> allUsers = notificationsSystem.getAllUsers().stream().toList();
        if (allUsers != null && !allUsers.isEmpty()) {
            usersList.addAll(allUsers);
        }
    }

    /**
     * Carga las notificaciones del moderador en la lista
     */
    private void loadNotifications() {
        notificationsList.clear();
        if (currentModerator != null) {
            List<String> inbox = currentModerator.getInbox();
            if (inbox != null && !inbox.isEmpty()) {
                notificationsList.addAll(inbox);
            }
        }
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

            if (confirmed && currentModerator != null) {
                currentModerator.deleteNotification(selectedIndex);
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
     * Manejador para ordenar la lista de usuarios
     */
    @FXML
    public void ListUsers() {
        TableColumn<User, ?> sortColumn = TableViewUsers.getSortOrder().isEmpty() ?
                null : TableViewUsers.getSortOrder().get(0);

        if (sortColumn != null) {
            TableColumn.SortType sortType = sortColumn.getSortType();
            Comparator<User> comparator = null;

            if (sortColumn == tbcUserName) {
                comparator = Comparator.comparing(User::getName);
            } else if (sortColumn == tbcUserEmail) {
                comparator = Comparator.comparing(User::getEmail);
            } else if (sortColumn == tbcUserPhone) {
                comparator = Comparator.comparing(User::getPhone);
            } else if (sortColumn == tbcIsBlocked) {
                comparator = Comparator.comparing(User::isBlocked);
            }

            if (comparator != null && sortType == TableColumn.SortType.DESCENDING) {
                comparator = comparator.reversed();
            }

            if (comparator != null) {
                FXCollections.sort(usersList, comparator);
            }
        }
    }

    /**
     * Manejador para bloquear usuario seleccionado
     */
    @FXML
    public void onBlockUser() {
        User selectedUser = TableViewUsers.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            if (selectedUser.isBlocked()) {
                showAlert(Alert.AlertType.INFORMATION, "Usuario bloqueado",
                        "El usuario ya se encuentra bloqueado.");
                return;
            }

            boolean confirmed = showConfirmDialog("Bloquear usuario",
                    "¿Está seguro de que desea bloquear al usuario " + selectedUser.getName() + "?");

            if (confirmed) {
                boolean success = notificationsSystem.blockUser(selectedUser.getEmail());
                if (success) {
                    TableViewUsers.refresh();
                    showAlert(Alert.AlertType.INFORMATION, "Usuario bloqueado",
                            "El usuario ha sido bloqueado correctamente.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error",
                            "No se pudo bloquear al usuario.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Selección requerida",
                    "Por favor, seleccione un usuario para bloquear.");
        }
    }

    /**
     * Manejador para desbloquear usuario seleccionado
     */
    @FXML
    public void onUnblockUser() {
        User selectedUser = TableViewUsers.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            if (!selectedUser.isBlocked()) {
                showAlert(Alert.AlertType.INFORMATION, "Usuario desbloqueado",
                        "El usuario ya se encuentra desbloqueado.");
                return;
            }

            boolean confirmed = showConfirmDialog("Desbloquear usuario",
                    "¿Está seguro de que desea desbloquear al usuario " + selectedUser.getName() + "?");

            if (confirmed) {
                boolean success = notificationsSystem.unblockUser(selectedUser.getEmail());
                if (success) {
                    TableViewUsers.refresh();
                    showAlert(Alert.AlertType.INFORMATION, "Usuario desbloqueado",
                            "El usuario ha sido desbloqueado correctamente.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error",
                            "No se pudo desbloquear al usuario.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Selección requerida",
                    "Por favor, seleccione un usuario para desbloquear.");
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