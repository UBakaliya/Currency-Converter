package com.example.currency_converter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This is the main class the runs the application.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("currency-converter-view.fxml"));
        stage.setTitle("Currency Converter");
        Image icon = new Image("file:src/images/icon.png");
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root.load()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

