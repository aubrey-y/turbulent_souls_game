package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
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

import static org.example.exceptions.ExceptionMessages.invalidDifficultyExceptionMessage;
import static org.example.exceptions.ExceptionMessages.invalidNameExceptionMessage;
import static org.example.exceptions.ExceptionMessages.invalidArchetypeExceptionMessage;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class SecondaryControllerTest {


    @Start
    public void setUp(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        stage.setScene(new Scene(loader.load(), 0, 0));
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
        verifyThat("#errorText", hasText(invalidNameExceptionMessage));
    }

    @Test
    public void testSwitchToGameScreen_givenUnselectedDifficulty_throwsInvalidDifficultyException(FxRobot robot) {
        //When
        robot.clickOn("#usernameField");
        robot.press(KeyCode.A);
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(invalidDifficultyExceptionMessage));
    }

    @Test
    public void testSwitchToGameScreen_givenUnselectedArchetype_throwsInvalidArchetypeException(FxRobot robot) {
        //When
        robot.clickOn("#usernameField");
        robot.press(KeyCode.A);
        robot.clickOn("#levelButton");
        robot.clickOn("#startButton");

        //Then
        verifyThat("#errorText", hasText(invalidArchetypeExceptionMessage));
    }

    @Test
    public void testSwitchToGameScreen_givenValidInputs(FxRobot robot) {
        //When
        robot.clickOn("#usernameField");
        robot.press(KeyCode.A);
        robot.clickOn("#levelButton");
        robot.clickOn("#archetypeButton");
        robot.clickOn("#startButton");

        //Then - FIX!
        verifyThat("#errorText", hasText(""));
    }
}
