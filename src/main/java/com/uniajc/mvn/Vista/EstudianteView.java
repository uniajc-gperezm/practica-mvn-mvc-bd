package com.uniajc.mvn.Vista;

import com.uniajc.mvn.controlador.EstudianteControlador;
import com.uniajc.mvn.modelo.Estudiante;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class EstudianteView extends JFrame {

    private final EstudianteControlador controlador = new EstudianteControlador();
    private final DefaultTableModel tableModel;
    private final JTable estudianteTable;

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtEdad = new JTextField(5);
    
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnActualizar = new JButton("Actualizar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final JButton btnRegresar = new JButton("Regresar"); 
    
    private MainView mainView; 

    // [CORRECCIÓN SUPER] Constructor vacío para compatibilidad
    public EstudianteView() {
        super(); 
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Edad"}, 0);
        estudianteTable = new JTable(tableModel);
        initializeComponents(null);
    }
    
    // Constructor con MainView
    public EstudianteView(MainView mainView) {
        super("CRUD de Estudiantes - Swing"); 
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Edad"}, 0);
        estudianteTable = new JTable(tableModel);
        initializeComponents(mainView);
    }

    // Método que contiene la lógica de inicialización
    private void initializeComponents(MainView mainViewRef) {
        this.mainView = mainViewRef;
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());
        
        cargarEstudiantes(); 
        
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Gestión de Estudiante"));
        txtId.setEditable(false); 
        formPanel.add(new JLabel("ID:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Edad:"));
        formPanel.add(txtEdad);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnLimpiar);
        buttonPanel.add(btnRegresar); 

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(estudianteTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        configurarEventos();

        pack(); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void cargarEstudiantes() {
        tableModel.setRowCount(0);
        try {
            ArrayList<Estudiante> estudiantes = controlador.obtenerTodos();
            for (Estudiante e : estudiantes) {
                tableModel.addRow(new Object[]{e.getId(), e.getNombre(), e.getEdad()});
            }
        } catch (RuntimeException ex) {
            // Muestra el error de conexión o carga
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarEventos() {
        // Evento Guardar: Validación de campos
        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String edadText = txtEdad.getText().trim();

                if (nombre.isEmpty() || edadText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nombre y Edad no pueden estar vacíos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int edad = Integer.parseInt(edadText); // Puede lanzar NumberFormatException
                
                controlador.registrarEstudiante(nombre, edad);
                JOptionPane.showMessageDialog(this, "Estudiante guardado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarEstudiantes();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(this, "Error de formato: La Edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento Actualizar: Validación de ID y Edad (Resuelve "For input string: "" en Actualizar)
        btnActualizar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un estudiante o llenar el ID para actualizar.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(idText);

                String nombre = txtNombre.getText().trim();
                
                String edadText = txtEdad.getText().trim();
                if (edadText.isEmpty()) {
                    // 🚨 Este es el origen del error si se deja vacío
                    JOptionPane.showMessageDialog(this, "El campo Edad no puede estar vacío.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int edad = Integer.parseInt(edadText);

                controlador.actualizarEstudiante(id, nombre, edad);
                JOptionPane.showMessageDialog(this, "Estudiante actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarEstudiantes();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error de formato: El ID y la Edad deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento Eliminar: Validación de ID (Resuelve "For input string: "" en Eliminar)
        btnEliminar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un estudiante o llenar el ID para eliminar.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(idText); // Conversión ahora segura
                
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar el estudiante ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    controlador.eliminarEstudiante(id);
                    JOptionPane.showMessageDialog(this, "Estudiante eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarEstudiantes();
                    limpiarCampos();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // ... otros eventos (Limpiar, Seleccionar Tabla, Regresar)
        
        estudianteTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && estudianteTable.getSelectedRow() != -1) {
                int selectedRow = estudianteTable.getSelectedRow();
                txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtNombre.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtEdad.setText(tableModel.getValueAt(selectedRow, 2).toString());
            }
        });
        
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        btnRegresar.addActionListener(e -> {
            if (mainView != null) {
                this.dispose(); 
                mainView.setVisible(true); 
            } else {
                this.dispose();
            }
        });
    }
    
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        estudianteTable.clearSelection();
    }
}