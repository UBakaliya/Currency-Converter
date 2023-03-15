package com.example.currency_converter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("currency-converter-view.fxml"));
        stage.setTitle("Currency Converter");
        Image icon = new Image("file:src/images/icon.png");
        stage.getIcons().add(icon);

        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

