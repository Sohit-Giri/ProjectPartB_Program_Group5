package com.example.evmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/evmanagement/fxml/MainView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Set the application title
        primaryStage.setTitle("EV Management Application");

        // Set the application logo (taskbar and top-left corner)
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/evmanagement/images/logo.png")));

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}