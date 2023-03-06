package com.example.currency_exchanger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("currency-exchanger-view.fxml"));
        stage.setTitle("Currency Exchanger");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setScene(new Scene(root.load()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
