package com.gb.chat.controllers;



import com.gb.chat.StartClient;
import com.gb.chat.models.Network;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class SignController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginReg;

    @FXML
    private TextField passReg;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameReg;
    private Network network;
    private StartClient startClient;


    @FXML
    void initialize() {
        signInButton.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        checkAuth();
                    }
                }
        );
        signUpButton.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        checkAuth();
                    }
                }
        );

    }


    @FXML
    void checkAuth() {
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (login.length() == 0 || password.length() == 0) {
            System.out.println("Ошибка ввода при аутентификации");
            System.out.println();
            startClient.showErrorAlert("Ошибка ввода при аутентификации", "Поля не должны быть пустыми");
            return;
        }

        if (login.length() > 32 || password.length() > 32) {
            startClient.showErrorAlert("Ошибка ввода при аутентификации", "Длина логина и пароля должны быть не более 32 символов");
            return;
        }

        String authErrorMessage = network.sendAuthMessage(login, password);

        if (authErrorMessage == null) {
            startClient.openChatDialog();
        } else {
            startClient.showErrorAlert("Ошибка аутентификации", authErrorMessage);
        }
    }

    @FXML
    void signUp(ActionEvent event) {

    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Network getNetwork() {
        return network;
    }

    public void setStartClient(StartClient startClient) {
        this.startClient = startClient;
    }

    public StartClient getStartClient() {
        return startClient;
    }
}
