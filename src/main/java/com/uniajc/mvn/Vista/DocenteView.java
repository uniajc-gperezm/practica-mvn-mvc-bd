package com.uniajc.mvn.Vista;

import com.uniajc.mvn.controlador.DocenteControlador;
import com.uniajc.mvn.modelo.Docente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DocenteView extends JFrame {

    private final DocenteControlador controlador = new DocenteControlador(); 
    private final DefaultTableModel tableModel;
    private final JTable docenteTable;

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtDepartamento = new JTextField(15); 
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnActualizar = new JButton("Actualizar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final JButton btnRegresar = new JButton("Regresar"); 
    
    private MainView mainView; 

    // [CORRECCIÓN SUPER] Constructor vacío para compatibilidad
    public DocenteView() {
        super();
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Departamento"}, 0);
        docenteTable = new JTable(tableModel);
        initializeComponents(null);
    }
    
    // Constructor con MainView
    public DocenteView(MainView mainView) {
        super("CRUD de Docentes - Swing");
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Departamento"}, 0);
        docenteTable = new JTable(tableModel);
        initializeComponents(mainView);
    }

    private void initializeComponents(MainView mainViewRef) {
        this.mainView = mainViewRef;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        cargarDocentes(); 
        
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Gestión de Docente"));
        
        txtId.setEditable(false);
        formPanel.add(new JLabel("ID:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Departamento:")); 
        formPanel.add(txtDepartamento);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnLimpiar);
        buttonPanel.add(btnRegresar); 

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(docenteTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        configurarEventos();

        pack(); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void cargarDocentes() {
        tableModel.setRowCount(0); 
        try {
            ArrayList<Docente> docentes = controlador.obtenerTodos();
            for (Docente d : docentes) {
                tableModel.addRow(new Object[]{d.getId(), d.getNombre(), d.getDepartamento()}); 
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarEventos() {
        // Evento: Guardar Nuevo - Validación de campos
        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String departamento = txtDepartamento.getText().trim(); 

                if (nombre.isEmpty() || departamento.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nombre y Departamento no pueden estar vacíos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                controlador.registrarDocente(nombre, departamento); 
                JOptionPane.showMessageDialog(this, "Docente guardado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDocentes();
                limpiarCampos();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error de DB", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento: Actualizar - Validación de ID (Resuelve "For input string: "" en Actualizar)
        btnActualizar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un docente o llenar el ID para actualizar.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(idText);

                String nombre = txtNombre.getText().trim();
                String departamento = txtDepartamento.getText().trim(); 

                if (nombre.isEmpty() || departamento.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nombre y Departamento no pueden estar vacíos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.actualizarDocente(id, nombre, departamento); 
                JOptionPane.showMessageDialog(this, "Docente actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDocentes();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error de formato: El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento: Eliminar - Validación de ID (Resuelve "For input string: "" en Eliminar)
        btnEliminar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un docente o llenar el ID para eliminar.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(txtId.getText());
                
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar el docente ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    controlador.eliminarDocente(id); 
                    JOptionPane.showMessageDialog(this, "Docente eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarDocentes();
                    limpiarCampos();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // ... otros eventos
        docenteTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && docenteTable.getSelectedRow() != -1) {
                int selectedRow = docenteTable.getSelectedRow();
                txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtNombre.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtDepartamento.setText(tableModel.getValueAt(selectedRow, 2).toString()); 
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
        txtDepartamento.setText("");
        docenteTable.clearSelection();
    }
}