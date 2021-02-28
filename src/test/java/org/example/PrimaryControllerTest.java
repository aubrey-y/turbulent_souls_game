package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controllers.PrimaryController;
import org.example.services.AppService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class PrimaryControllerTest {

    private PrimaryController controller;

    private AppService appService;

    @Start
    public void setUp(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        stage.setScene(new Scene(loader.load(), 0, 0));
        stage.show();
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
    }

    @Test
    public void switchAudioToggle_givenAudioIsPlaying(FxRobot robot) {
        //When
        robot.clickOn("#soundToggle");

        //Then
        assertThat(AppService.getSoundPlaying(), is(false));
    }

}
