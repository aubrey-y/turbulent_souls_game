package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.example.controllers.SecondaryController;
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

import static org.example.exceptions.ExceptionMessages.INVALID_ARCHETYPE_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_DIFFICULTY_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_NAME_EXCEPTION_MESSAGE;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class SecondaryControllerTest {

    private SecondaryController controller;

    private AppService appService;

    @Start
    public void setUp(Stage stage) throws IOException {
        withMockedAppService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
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
    public void testSwitchToGameScreen_givenEmptyUsername_setsExpectedErrorText(FxRobot robot) {
        //When
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(INVALID_NAME_EXCEPTION_MESSAGE));
    }

    @Test
    public void testSwitchToGameScreen_givenUnselectedDifficulty_throwsIDException(FxRobot robot) {
        //When
        robot.clickOn("#usernameField");
        robot.press(KeyCode.A);
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(INVALID_DIFFICULTY_EXCEPTION_MESSAGE));
    }

    @Test
    public void testSwitchToGameScreen_givenUnselectedArchetype_throwsIAException(FxRobot robot) {
        //When
        robot.clickOn("#usernameField");
        robot.press(KeyCode.A);
        robot.clickOn("#levelButton");
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(INVALID_ARCHETYPE_EXCEPTION_MESSAGE));
    }

    @Test
    public void testSwitchToGameScreen_givenValidInputs_doesNotReportExceptions(FxRobot robot) {
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
    public void testSwitchToGameScreen_givenWhiteSpaceUsername_throwsIAException(FxRobot robot) {
        //When
        robot.clickOn("#usernameField").type(KeyCode.SPACE);
        robot.clickOn("#levelButton");
        robot.clickOn("#archetypeButton");
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(INVALID_NAME_EXCEPTION_MESSAGE));
    }

    private void withMockedAppService() throws IOException {
        this.appService = spy(AppService.class);
        doNothing().when(this.appService).setRoot(anyString());
    }
}
