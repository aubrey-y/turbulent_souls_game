package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;

public class EndingRoomController extends GameScreenController {

    @FXML
    private Label leaveLabel;

    public EndingRoomController(AppService appService,
                                PlayerService playerService,
                                DirectionService directionService,
                                RoomDirectionService roomDirectionService,
                                HealthService healthService,
                                Scene scene) {
        super(
                appService,
                playerService,
                directionService,
                roomDirectionService,
                healthService,
                scene);
    }
}