package org.example.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import org.example.dto.PlayerState;
import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.exceptions.InvalidArchetypeException;
import org.example.exceptions.InvalidDifficultyException;
import org.example.exceptions.InvalidNameException;
import org.example.exceptions.PlayerCreationException;
import org.example.services.AppService;

import static org.example.exceptions.ExceptionMessages.INVALID_ARCHETYPE_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_DIFFICULTY_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_NAME_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.UNKNOWN_EXCEPTION_MESSAGE;

public class SecondaryController extends BaseController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private Rectangle errorBox;

    @FXML
    private Label errorText;

    private String username;

    private Difficulty difficulty;

    private Archetype archetype;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initController();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        this.appService.setRoot("primary");
    }

    @FXML
    public void switchToGameScreen(ActionEvent event) throws IOException {
        this.hideErrorMessage();
        try {
            validatePlayerName();
            validateDifficulty();
            validateArchetype();
            this.appService.setPlayerState(
                    new PlayerState(this.username, this.archetype, this.difficulty));
            this.appService.setRoot("gameScreen");
        } catch (InvalidNameException e) {
            this.setErrorMessage(INVALID_NAME_EXCEPTION_MESSAGE);
        } catch (InvalidDifficultyException e) {
            this.setErrorMessage(INVALID_DIFFICULTY_EXCEPTION_MESSAGE);
        } catch (InvalidArchetypeException e) {
            this.setErrorMessage(INVALID_ARCHETYPE_EXCEPTION_MESSAGE);
        } catch (PlayerCreationException e) {
            this.setErrorMessage(UNKNOWN_EXCEPTION_MESSAGE);
        }
    }

    private void validatePlayerName() throws InvalidNameException {
        String username = usernameField.getText();
        if (username.isEmpty() || username.trim().equals("")) {
            throw new InvalidNameException("");
        } else {
            this.username = username;
        }
    }

    //No else statement because selection is already tracked
    private void validateDifficulty() throws InvalidDifficultyException {
        if (this.difficulty == null) {
            throw new InvalidDifficultyException("");
        }
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

    public AppService getAppService() {
        return appService;
    }

    public SecondaryController setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }

    //Class builders
    public SecondaryController setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
        return this;
    }

    public SecondaryController setErrorBox(Rectangle errorBox) {
        this.errorBox = errorBox;
        return this;
    }

    public SecondaryController setErrorText(Label errorText) {
        this.errorText = errorText;
        return this;
    }

    public SecondaryController setUsername(String username) {
        this.username = username;
        return this;
    }

    public SecondaryController setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public SecondaryController setArchetype(Archetype archetype) {
        this.archetype = archetype;
        return this;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public Rectangle getErrorBox() {
        return errorBox;
    }

    public Label getErrorText() {
        return errorText;
    }

    public String getUsername() {
        return username;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Archetype getArchetype() {
        return archetype;
    }
}