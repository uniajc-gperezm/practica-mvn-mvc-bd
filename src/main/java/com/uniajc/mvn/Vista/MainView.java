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

        
        estudianteItem.addActionListener(this::abrirEstudianteView);
        docenteItem.addActionListener(this::abrirDocenteView);
        cursoItem.addActionListener(this::abrirCursoView);
        
        setVisible(true);
    }
    
    
    private void abrirEstudianteView(ActionEvent e) {
        
        this.setVisible(false); 
        
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