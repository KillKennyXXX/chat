package com.gb.chat.controllers;

import com.gb.chat.models.Network;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;



public class ChatController {

    @FXML
    private ListView<String> chatList;

    @FXML
    private VBox chatWindow;

    @FXML
    private Button clearButton;

    @FXML
    private ImageView logoImg;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField msgText;


    @FXML
    private Button sendButton;
    private Network network;

    @FXML
    private Label userID;

    @FXML
    private ListView<String> userList;

    @FXML
    public void initialize() {
        userList.setItems(FXCollections.observableArrayList("Виталий", "Олег", "Андрей", "Артем"));
        sendButton.setOnAction(event -> sendMessage());
        msgText.setOnAction(event -> sendMessage());

    }

    @FXML
    void onClear(ActionEvent event) {
        msgText.setText("");
    }

    @FXML
    void onLogout(ActionEvent event) {

    }

    @FXML
    void onSend(ActionEvent event) {
        if (msgText.getText().trim() != "") {
            chatList.getItems().add(msgText.getText().trim());
            msgText.setText("");
        }

    }
    @FXML
    void onMsgKeyPress(KeyEvent event) {

        if ( event.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }
    private void sendMessage() {
        String message = msgText.getText().trim();
        msgText.clear();

        if (message.isEmpty()) {
            return;
        }


        network.sendMessage(message);
    }

    public void appendMessage(String message) {

        message += System.lineSeparator();
        chatList.getItems().add(message);

    }

    public void setNetwork(Network network) {
        this.network = network;
    }

}
