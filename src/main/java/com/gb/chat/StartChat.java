package com.gb.chat;

import com.gb.chat.controllers.ChatController;
import com.gb.chat.models.Network;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartChat extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartChat.class.getResource("chat.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 746, 542);
        stage.setTitle("Chat++");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setX(200);
        stage.setY(200);
        stage.show();
        Network network = new Network();

        ChatController chatController = fxmlLoader.getController();
        chatController.setNetwork(network);

        network.connect();
        network.waitMessage(chatController);
    }

    public static void main(String[] args) {
        launch();
    }
}