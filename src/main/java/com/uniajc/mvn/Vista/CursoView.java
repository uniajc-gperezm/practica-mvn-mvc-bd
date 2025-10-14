package com.uniajc.mvn.Vista;

import com.uniajc.mvn.controlador.CursoControlador;
import com.uniajc.mvn.modelo.Curso;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CursoView extends JFrame {

    private final CursoControlador controlador = new CursoControlador(); 
    private final DefaultTableModel tableModel;
    private final JTable cursoTable;

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtCreditos = new JTextField(5); 
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnActualizar = new JButton("Actualizar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final JButton btnRegresar = new JButton("Regresar"); 
    
    private MainView mainView; 

    // [CORRECCIÓN SUPER] Constructor vacío para compatibilidad
    public CursoView() {
        super();
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Notas"}, 0);
        cursoTable = new JTable(tableModel);
        initializeComponents(null);
    }
    
    // Constructor con MainView
    public CursoView(MainView mainView) {
        super("CRUD de Cursos - Swing");
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Notas"}, 0);
        cursoTable = new JTable(tableModel);
        initializeComponents(mainView);
    }
    
    private void initializeComponents(MainView mainViewRef) {
        this.mainView = mainViewRef;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        cargarCursos(); 
        
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Gestión de Curso"));
        txtId.setEditable(false);
        formPanel.add(new JLabel("ID:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Notas:")); 
        formPanel.add(txtCreditos);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnLimpiar);
        buttonPanel.add(btnRegresar); 

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(cursoTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        configurarEventos();

        pack(); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void cargarCursos() {
        tableModel.setRowCount(0); 
        try {
            ArrayList<Curso> cursos = controlador.obtenerTodos();
            for (Curso c : cursos) {
                tableModel.addRow(new Object[]{c.getId(), c.getNombre(), c.getCreditos()}); 
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarEventos() {
        // Evento: Guardar Nuevo - Validación de Notas
        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String creditosText = txtCreditos.getText().trim();
                
                if (nombre.isEmpty() || creditosText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nombre y Notas no pueden estar vacíos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int creditos = Integer.parseInt(creditosText);
                controlador.registrarCurso(nombre, creditos); 
                JOptionPane.showMessageDialog(this, "Curso guardado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarCursos();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error de formato: Las Notas deben ser un número.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error de DB", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento: Actualizar - Validación de ID y Notas (Resuelve "For input string: "" en Actualizar)
        btnActualizar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un curso o llenar el ID para actualizar.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(idText);

                String nombre = txtNombre.getText().trim();
                
                String creditosText = txtCreditos.getText().trim();
                if (creditosText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El campo Notas no puede estar vacío.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int creditos = Integer.parseInt(creditosText);
                
                controlador.actualizarCurso(id, nombre, creditos);
                JOptionPane.showMessageDialog(this, "Curso actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarCursos();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error de formato: El ID y las Notas deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento: Eliminar - Validación de ID (Resuelve "For input string: "" en Eliminar)
        btnEliminar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un curso o llenar el ID para eliminar.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(txtId.getText());
                
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar el curso ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    controlador.eliminarCurso(id); 
                    JOptionPane.showMessageDialog(this, "Curso eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarCursos();
                    limpiarCampos();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // ... otros eventos
        cursoTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && cursoTable.getSelectedRow() != -1) {
                int selectedRow = cursoTable.getSelectedRow();
                txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtNombre.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtCreditos.setText(tableModel.getValueAt(selectedRow, 2).toString());
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
        txtCreditos.setText("");
        cursoTable.clearSelection();
    }
}