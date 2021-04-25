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
    requires com.fasterxml.jackson.core;
    requires mongo.java.driver;

    requires org.junit.jupiter.api;
    requires com.fasterxml.jackson.databind;

    opens org.example to javafx.fxml;
    opens org.example.controllers to javafx.fxml;
    opens org.example.controllers.rooms to javafx.fxml;
    opens org.example.services to javafx.fxml;
    exports org.example;
    exports org.example.controllers;
    exports org.example.services;
    exports org.example.enums;
    exports org.example.exceptions;
    exports org.example.dto.weapons;
    exports org.example.dto.consumables;
    exports org.example.dto.util;
    exports org.example.dao;
    exports org.example.util;
    opens org.example.util to javafx.fxml;
}
