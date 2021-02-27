module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    requires org.junit.jupiter.api;

    opens org.example to javafx.fxml;
    opens org.example.controllers to javafx.fxml;
    exports org.example;
    exports org.example.controllers;
    exports org.example.dto;
    exports org.example.enums;
    exports org.example.exceptions;
}
