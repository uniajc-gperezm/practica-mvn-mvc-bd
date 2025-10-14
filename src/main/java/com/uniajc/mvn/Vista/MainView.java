package com.uniajc.mvn.Vista; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainView extends JFrame {

    public MainView() {
        super("Sistema de Gestión Académica - CRUD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300); 
        setLocationRelativeTo(null); 
        
        setLayout(new BorderLayout());
        
        JMenuBar menuBar = new JMenuBar();
        JMenu gestionMenu = new JMenu("Gestión de Entidades");
        
        JMenuItem estudianteItem = new JMenuItem("CRUD Estudiantes");
        JMenuItem docenteItem = new JMenuItem("CRUD Docentes");
        JMenuItem cursoItem = new JMenuItem("CRUD Cursos");
        
        gestionMenu.add(estudianteItem);
        gestionMenu.add(docenteItem);
        gestionMenu.add(cursoItem);
        
        menuBar.add(gestionMenu);
        setJMenuBar(menuBar);

        JLabel welcomeLabel = new JLabel("Seleccione una opción del menú 'Gestión de Entidades'", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(welcomeLabel, BorderLayout.CENTER);

        // 6. Configurar Eventos para abrir las ventanas CRUD (MODIFICADOS)
        estudianteItem.addActionListener(this::abrirEstudianteView);
        docenteItem.addActionListener(this::abrirDocenteView);
        cursoItem.addActionListener(this::abrirCursoView);
        
        setVisible(true);
    }
    
    // Métodos para abrir las vistas (MODIFICADOS)
    private void abrirEstudianteView(ActionEvent e) {
        // 1. Oculta la ventana principal
        this.setVisible(false); 
        // 2. Crea la nueva vista pasándose a sí misma (this) como parámetro
        new EstudianteView(this).setVisible(true);
    }
    
    private void abrirDocenteView(ActionEvent e) {
        this.setVisible(false); 
        new DocenteView(this).setVisible(true);
    }
    
    private void abrirCursoView(ActionEvent e) {
        this.setVisible(false); 
        new CursoView(this).setVisible(true);
    }
}