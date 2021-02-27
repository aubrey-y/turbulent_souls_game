package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import org.example.controllers.SecondaryController;
import org.example.exceptions.InvalidArchetypeException;
import org.example.exceptions.InvalidDifficultyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.example.exceptions.ExceptionMessages.invalidNameExceptionMessage;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SecondaryControllerTest {

    private SecondaryController controller;

//    @BeforeEach
//    public void setUp() {
//        this.controller = new SecondaryController()
//                .setErrorBox(new Rectangle());
//    }
//
//    @Test
//    public void testSwitchToGameScreen_givenEmptyUsername_setsExpectedErrorText() throws IOException {
//        //Given
//        Label label = new Label();
//        label.setText("");
//        this.controller.setErrorText(label);
//
//        String expected = invalidNameExceptionMessage;
//        //When
//        this.controller.switchToGameScreen(null);
//
//        //Then
//        String actual = this.controller.getErrorText().getText();
//
//        assertThat(actual, is(equalTo(expected)));
//    }

//    @Test
//    public void testSwitchToGameScreen_givenUnselectedDifficulty_throwsInvalidDifficultyException() {
//        assertThrows(InvalidDifficultyException.class, () -> this.controller.switchToGameScreen(null));
//    }
//
//    @Test
//    public void testSwitchToGameScreen_givenUnselectedArchetype_throwsInvalidArchetypeException() {
//        assertThrows(InvalidArchetypeException.class, () -> this.controller.switchToGameScreen(null));
//    }
}
