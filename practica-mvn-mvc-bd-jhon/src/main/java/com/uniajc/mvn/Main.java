package com.uniajc.mvn;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // ... (Tu código de carga de FXML aquí) ...
        primaryStage.setTitle("Gestión Académica UNIAJC");
        primaryStage.show();
    }
    
    // Este método main es llamado SOLO por la clase Launcher.
    public static void main(String[] args) {
        launch(args);
    }
}