module com.gb.chat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gb.chat to javafx.fxml;
    exports com.gb.chat;
    exports com.gb.chat.controllers;
    opens com.gb.chat.controllers to javafx.fxml;
}