package com.gb.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("chat.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 746, 542);
        stage.setTitle("Chat++");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setX(200);
        stage.setY(200);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}