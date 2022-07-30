package com.gb.chat.controllers;

import com.gb.chat.StartClient;
import com.gb.chat.models.Network;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ChatController {

    @FXML
    private TextArea chatList;

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
    private String selectedRecipient;
    private StartClient startClient;

    @FXML
    private Label userID;

    @FXML
    private ListView<String> userList;

    @FXML
    void onClear(ActionEvent event) {
        msgText.clear();
    }

    @FXML
    void onLogout(ActionEvent event) {

    }


    @FXML
    public void initialize() {


        sendButton.setOnAction(event -> sendMessage());
        msgText.setOnAction(event -> sendMessage());
        msgText.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        sendMessage();
                    }
                }
        );
        userList.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = userList.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                userList.requestFocus();
                if (!cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell;
        });
    }

    private void sendMessage() {
        String message = msgText.getText().trim();
        msgText.clear();

        if (message.isEmpty()) {
            return;
        }


        if (selectedRecipient != null) {
            network.sendPrivateMessage(selectedRecipient, message);
        } else {
            network.sendMessage(message);
        }
    }

    public synchronized void appendMessage(String sender, String message) {
        String timeStamp = DateFormat.getInstance().format(new Date());

        chatList.appendText(timeStamp);
        chatList.appendText(System.lineSeparator());
        chatList.appendText(String.format("%s: %s", sender, message));
        chatList.appendText(System.lineSeparator());
        chatList.appendText(System.lineSeparator());
//        chatHistory.setText(new StringBuilder(chatHistory.getText()).insert(0, message).toString());

    }

    public synchronized void appendServerMessage(String message) {
        chatList.appendText(String.format("Внимание! %s", message));
        chatList.appendText(System.lineSeparator());
        chatList.appendText(System.lineSeparator());
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setUsernameTitle(String usernameTitleStr) {
        this.userID.setText(usernameTitleStr);
    }

    public void setStartClient(StartClient startClient) {
        this.startClient = startClient;
    }

    public StartClient getStartClient() {
        return startClient;
    }

    public synchronized void createUserList(String message) {
        // userList.setItems(FXCollections.observableArrayList("Super_Sonic", "Bender", "Super_Mario", "Гендальф_Серый"
        // , "Брюс_Уэйн", "Martin_Superstar"));
        String[] parts = message.split("\\s+");
        List<String> list = new ArrayList<>(Arrays.asList(parts));
        list.remove(0);
        userList.setItems(FXCollections.observableArrayList(list));
        System.out.println(this.userID.getText() + ": " + list.toString());
    }

}
