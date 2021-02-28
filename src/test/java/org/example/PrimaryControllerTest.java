package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class PrimaryControllerTest {

    private PrimaryController controller;

    private AppService appService;

    @Start
    public void setUp(Stage stage) throws IOException {
        withMockedAppService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        this.controller = loader.getController();
        this.controller.setAppService(this.appService);
        stage.setScene(new Scene(root, 0, 0));
        stage.show();
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
    }

    @Test
    public void switchAudioToggleGivenAudioIsPlayingTurnsSoundPlayingOff(FxRobot robot) {
        //When
        robot.clickOn("#soundToggle");

        //Then
        assertThat(this.appService.getSoundPlaying(), is(false));
    }

    private void withMockedAppService() {
        this.appService = mock(AppService.class);
        doAnswer(invocationOnMock -> {
            App.setSoundPlaying(false);
            return null;
        }).when(this.appService).toggleSound(any(), any());
    }
}
