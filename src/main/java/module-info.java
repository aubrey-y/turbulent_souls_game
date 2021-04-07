module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires com.google.api.services.gmail;
    requires com.google.api.client;
    requires google.api.client;
    requires com.google.api.client.auth;
    requires com.google.api.client.json.jackson2;
    requires java.mail;

    requires org.junit.jupiter.api;

    opens org.example to javafx.fxml;
    opens org.example.controllers to javafx.fxml;
    opens org.example.controllers.rooms to javafx.fxml;
    opens org.example.services to javafx.fxml;
    exports org.example;
    exports org.example.controllers;
    exports org.example.services;
    exports org.example.dto;
    exports org.example.enums;
    exports org.example.exceptions;
}
