package org.example.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.App;
import org.example.dto.PlayerState;
import org.example.exceptions.InvalidArchetypeException;
import org.example.exceptions.InvalidDifficultyException;
import org.example.exceptions.InvalidNameException;

public class SecondaryController {

    @FXML
    private TextField nameID;

    @FXML
    private Button startButton;

    @FXML
    private Label gameConditions;

    @FXML
    private Label gameConditions2;

    @FXML
    private Label gameConditions3;

    @FXML
    private Rectangle errorBox;

    @FXML
    private Label errorText;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void switchToGameScreen(ActionEvent event) throws IOException {
        //This is the logic for the text field inputs
        this.hideErrorMessage();
        try {
            validatePlayerName();
            validateDifficulty();
            validateArchetype();
        } catch (InvalidNameException e) {
            this.setErrorMessage("Make sure your username is not empty.");
        } catch (InvalidDifficultyException a) {
            this.setErrorMessage("Make sure you select a difficulty.");
        } catch (InvalidArchetypeException b) {
            this.setErrorMessage("Make sure you select a character");
        }
//        boolean nameCheck = false;
//        boolean difficultyCheck = false;
//        boolean weaponCheck = false;
//        if (nameID.getText().isEmpty()
//        || nameID.getText() == null
//        || nameID.getText().trim().equals("")) {
//            gameConditions.setText("Please enter a valid username!");
//        } else {
//            String userName = nameID.getText();
//            gameConditions.setText("Your name is " + nameID.getText());
//            nameCheck = true;
//        }
//        //This is the logic for the game difficulty
//        if (gameDifficulty == null) {
//            gameConditions2.setText("Please select a difficulty!");
//        } else {
//            if (gameDifficulty.equals("Easy")) {
//                gameConditions2.setText
//                ("Your chosen difficulty is " + gameDifficulty + ", you're a baby");
//                difficultyCheck = true;
//                goldAmount = 1000;
//            }
//            if (gameDifficulty.equals("Medium")) {
//                gameConditions2.setText("Your chosen difficulty is " + gameDifficulty);
//                difficultyCheck = true;
//                goldAmount = 500;
//            }
//            if (gameDifficulty.equals("Hard")) {
//                gameConditions2.setText
//                ("Your chosen difficulty is " + gameDifficulty + ", I'm proud of you");
//                difficultyCheck = true;
//                goldAmount = 100;
//            }
//        }
//        //This is the logic for the chosen weapons, currently are placeholders
//        if (weaponChoice == null) {
//            gameConditions3.setText("Please choose a weapon!");
//        } else {
//            gameConditions3.setText("Your selected weapon " + weaponChoice);
//            weaponCheck = true;
//        }
//        if (nameCheck && difficultyCheck && weaponCheck) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
//            GameScreenController gameScreenController = loader.getController();
//            gameScreenController.displayGold(goldAmount);
//            App.setRoot("gameScreen");
    }
    }

    private void validatePlayerName() throws InvalidNameException {
        String username = nameID.getText();
        if (username.isEmpty() || username.trim().equals("")) {
            throw new InvalidNameException("");
        } else {
            PlayerState playerState = App.getPlayerState();
            playerState.setUsername(username);
            App.setPlayerState(playerState);
        }
    }

    //Not complete skeleton code
    private void validateDifficulty() throws InvalidDifficultyException {
        String difficulty = difficultySet();
        if (difficulty == null) {
            throw new InvalidDifficultyException("");
        } else {
            PlayerState playerState = App.getPlayerState();
            playerState.setDifficulty(difficulty);
            App.setPlayerState(playerState);
        }
    }

    //Not complete skeleton code
    private void validateArchetype() throws InvalidArchetypeException {
        String archetype = null;
        if (archetype == null) {
            throw new InvalidArchetypeException("");
        } else {
            PlayerState playerState = App.getPlayerState();
            playerState.setArchetype(archetype);
            App.setPlayerState(playerState);
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
    private javafx.scene.control.Button closeButton;

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}