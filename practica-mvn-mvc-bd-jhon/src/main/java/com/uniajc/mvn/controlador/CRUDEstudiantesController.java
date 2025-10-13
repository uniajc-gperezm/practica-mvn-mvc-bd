package com.uniajc.mvn.controlador;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.config.AppServices;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections; // Necesario para la tabla

public class CRUDEstudiantesController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEdad;
    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, Integer> colId;
    @FXML private TableColumn<Estudiante, String> colNombre;
    @FXML private TableColumn<Estudiante, Integer> colEdad;

    // ⭐️ Alert asume la responsabilidad de la vista ⭐️
    final Alert warningAlert = new Alert(Alert.AlertType.WARNING);
    final Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
    final Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    @FXML 
    void registrarEstudiante(ActionEvent event) {
        if (txtId.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtEdad.getText().isEmpty()) {
            try {
                AppServices.getEstudiante().registrarEstudiante(txtNombre.getText(), Integer.parseInt(txtEdad.getText()));
                
                confirmationAlert.setTitle("Éxito");
                confirmationAlert.setContentText("Registro exitoso.");
                confirmationAlert.show();
                
                limpiarCampos(null); 
                actualizarTabla();
            } catch (Exception ex) {
                errorAlert.setTitle("Error de Registro");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.show();
            }
        } else if (txtNombre.getText().isEmpty() || txtEdad.getText().isEmpty()) {
            warningAlert.setContentText("Complete la información necesaria para registrar el estudiante.");
            warningAlert.show();
        } else {
            warningAlert.setContentText("Limpie los campos para registrar el estudiante.");
            warningAlert.show();
        }
    }

    @FXML 
    void actualizarEstudiante(ActionEvent event) {
        if (!txtId.getText().isEmpty()) {
            try {
                AppServices.getEstudiante().actualizarEstudiante(Integer.parseInt(txtId.getText()), txtNombre.getText(), Integer.parseInt(txtEdad.getText()));
                confirmationAlert.setTitle("Éxito");
                confirmationAlert.setContentText("Actualización exitosa.");
                confirmationAlert.show();
                limpiarCampos(null);
                actualizarTabla();
            } catch (Exception ex) {
                errorAlert.setTitle("Error de Actualización");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.show();
            }
        } else {
            warningAlert.setContentText("Seleccione un estudiante para actualizar.");
            warningAlert.show();
        }
    }

    @FXML 
    void eliminarEstudiante(ActionEvent event) {
        if (!txtId.getText().isEmpty()) {
            try {
                AppServices.getEstudiante().eliminarEstudiantes(Integer.parseInt(txtId.getText()));
                confirmationAlert.setTitle("Éxito");
                confirmationAlert.setContentText("Eliminación exitosa.");
                confirmationAlert.show();
                limpiarCampos(null);
                actualizarTabla();
            } catch (Exception ex) {
                errorAlert.setTitle("Error de Eliminación");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.show();
            }
        } else {
            warningAlert.setContentText("Seleccione un estudiante para eliminar.");
            warningAlert.show();
        }
    }

    @FXML 
    void limpiarCampos(ActionEvent event) {
        txtId.clear();
        txtNombre.clear();
        txtEdad.clear();
        tblEstudiantes.getSelectionModel().clearSelection();
    }

    @FXML 
    void refrescarTabla(ActionEvent event) {
        actualizarTabla();
    }

    private void actualizarTabla() {
        tblEstudiantes.getItems().clear();
        try {
            tblEstudiantes.setItems(FXCollections.observableArrayList(AppServices.getEstudiante().obtenerEstudiantes()));
        } catch (Exception e) {
            errorAlert.setTitle("Error de Carga");
            errorAlert.setContentText("No se pudieron cargar los datos de estudiantes: " + e.getMessage());
            errorAlert.show();
        }
    }

    public void initialize() {
        warningAlert.setTitle("Advertencia");
        errorAlert.setTitle("Error"); // Inicializar el título por defecto

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        actualizarTabla(); 

        tblEstudiantes.getSelectionModel().selectedItemProperty().addListener((ignoredObservableValue, oldValue, newValue) -> {
            if (newValue != null) {
                txtId.setText(String.valueOf(newValue.getId()));
                txtNombre.setText(newValue.getNombre());
                txtEdad.setText(String.valueOf(newValue.getEdad()));
            }
        });
    }
}