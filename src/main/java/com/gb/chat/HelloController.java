package com.gb.chat;

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

public class HelloController {

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

    @FXML
    private Label userID;

    @FXML
    private ListView<String> userList;

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

        if (msgText.getText().trim() != "" && event.getCode() == KeyCode.ENTER) {
            chatList.getItems().add(msgText.getText().trim());
            msgText.setText("");
        }
    }


}
