package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.example.controllers.SecondaryController;
import org.example.services.AppService;
import org.example.services.SaveService;
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

import static org.example.exceptions.ExceptionMessages.INVALID_ARCHETYPE_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_DIFFICULTY_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_NAME_EXCEPTION_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class SecondaryControllerTest {

    private SecondaryController controller;

    private AppService appService;

    private Scene scene;

    @Start
    public void setUp(Stage stage) throws IOException {
        withMockedAppService();
        Scene mockedScene = new Scene(
                new FXMLLoader(App.class.getResource("primary.fxml")).load());
        FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        loader.setControllerFactory(SecondaryController -> new SecondaryController(
                mock(SaveService.class), mockedScene));
        Parent root = loader.load();
        this.scene = new Scene(root, 1920, 1080);
        this.controller = loader.getController();
        this.controller.setScene(this.scene);
        this.controller.setAppService(this.appService);
        stage.setScene(this.scene);
        stage.show();
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
    }

    @Test
    public void testSwitchToGameScreenGivenEmptyUsernameSetsExpectedErrorText(FxRobot robot) {
        //When
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(INVALID_NAME_EXCEPTION_MESSAGE));
    }

    @Test
    public void testSwitchToGameScreenGivenUnselectedDifficultyThrowsIDException(FxRobot robot) {
        //When
        robot.clickOn("#usernameField");
        robot.press(KeyCode.A);
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(INVALID_DIFFICULTY_EXCEPTION_MESSAGE));
    }

    @Test
    public void testSwitchToGameScreenGivenUnselectedArchetypeThrowsIAException(FxRobot robot) {
        //When
        robot.clickOn("#usernameField");
        robot.press(KeyCode.A);
        robot.clickOn("#levelButton");
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(INVALID_ARCHETYPE_EXCEPTION_MESSAGE));
    }

    @Test
    public void testSwitchToGameScreenGivenValidInputsDoesNotReportExceptions(FxRobot robot) {
        //When
        robot.clickOn("#usernameField");
        robot.press(KeyCode.A);
        robot.clickOn("#levelButton");
        robot.clickOn("#archetypeButton");
        robot.clickOn("#startButton");

        //Then - FIX!
        verifyThat("#errorText", hasText("No errors reported."));
    }

    @Test
    public void testSwitchToGameScreenGivenWhiteSpaceUsernameThrowsIAException(FxRobot robot) {
        //When
        robot.clickOn("#usernameField").type(KeyCode.SPACE);
        robot.clickOn("#levelButton");
        robot.clickOn("#archetypeButton");
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(INVALID_NAME_EXCEPTION_MESSAGE));
    }

    private void withMockedAppService() {
        this.appService = spy(AppService.class);
        doNothing().when(this.appService).setRoot(any());
    }
}
