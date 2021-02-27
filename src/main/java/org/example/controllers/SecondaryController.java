package org.example.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import org.example.App;
import org.example.dto.PlayerState;
import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.exceptions.InvalidArchetypeException;
import org.example.exceptions.InvalidDifficultyException;
import org.example.exceptions.InvalidNameException;
import org.example.exceptions.PlayerCreationException;

public class SecondaryController {

    @FXML
    private TextField nameID;

    @FXML
    private Rectangle errorBox;

    @FXML
    private Label errorText;

    private String username;

    private Difficulty difficulty;

    private Archetype archetype;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void switchToGameScreen(ActionEvent event) throws IOException {
        this.hideErrorMessage();
        try {
            validatePlayerName();
            validateDifficulty();
            validateArchetype();
            App.setPlayerState(new PlayerState(this.username, this.archetype, this.difficulty));
            App.setRoot("gameScreen");
        } catch (InvalidNameException e) {
            this.setErrorMessage("Make sure your username is not empty.");
        } catch (InvalidDifficultyException e) {
            this.setErrorMessage("Make sure you select a difficulty.");
        } catch (InvalidArchetypeException e) {
            this.setErrorMessage("Make sure you select a class.");
        } catch (PlayerCreationException e) {
            this.setErrorMessage("Unknown error: Contact cruft@gmail.com.");
        }
    }

    public void setNameID(String a) {
        this.nameID = new TextField();
        nameID.setText(a);
    }

    public void validatePlayerTest() throws InvalidNameException {
        validatePlayerName();
    }

    private void validatePlayerName() throws InvalidNameException {
        String username = nameID.getText();
        if (username.isEmpty() || username.trim().equals("")) {
            throw new InvalidNameException("");
        } else {
            this.username = username;
        }
    }


    public void validateDifficultyTest() throws InvalidDifficultyException {
        validateDifficulty();
    }

    //No else statement because selection is already tracked
    private void validateDifficulty() throws InvalidDifficultyException {
        if (this.difficulty == null) {
            throw new InvalidDifficultyException("");
        }
    }

    public void validateArchetypeTest() throws InvalidArchetypeException {
        validateArchetype();
    }

    private void validateArchetype() throws InvalidArchetypeException {
        if (this.archetype == null) {
            throw new InvalidArchetypeException("");
        }
    }

    @FXML
    private void setErrorMessage(String message) {
        this.errorBox.setOpacity(1.0);
        this.errorText.setText(message);
        this.errorText.setOpacity(1.0);
    }

    @FXML
    private void hideErrorMessage() {
        this.errorBox.setOpacity(0.0);
        this.errorText.setOpacity(0.0);
    }

    @FXML
    private void selectEasyDifficulty() {
        if (this.difficulty != Difficulty.EASY) {
            this.difficulty = Difficulty.EASY;
        } else {
            this.difficulty = null;
        }
    }

    @FXML
    private void selectMediumDifficulty() {
        if (this.difficulty != Difficulty.MEDIUM) {
            this.difficulty = Difficulty.MEDIUM;
        } else {
            this.difficulty = null;
        }
    }

    @FXML
    private void selectHardDifficulty() {
        if (this.difficulty != Difficulty.HARD) {
            this.difficulty = Difficulty.HARD;
        } else {
            this.difficulty = null;
        }
    }

    @FXML
    private void selectArcherArchetype() {
        if (this.archetype != Archetype.ARCHER) {
            this.archetype = Archetype.ARCHER;
        } else {
            this.archetype = null;
        }
    }

    @FXML
    private void selectMageArchetype() {
        if (this.archetype != Archetype.MAGE) {
            this.archetype = Archetype.MAGE;
        } else {
            this.archetype = null;
        }
    }

    @FXML
    private void selectWarriorArchetype() {
        if (this.archetype != Archetype.WARRIOR) {
            this.archetype = Archetype.WARRIOR;
        } else {
            this.archetype = null;
        }
    }

}