package com.uniajc.mvn;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/CRUDEstudiantes.fxml"));
        final Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Estudiantes");
        stage.show();
    }

    public static void main(String[] args)  {
        launch();
    }
}
