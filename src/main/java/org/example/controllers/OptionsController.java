package org.example.controllers;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.CheckBox;
import org.example.App;
import org.example.services.AppService;
import org.example.services.EmailService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Random;
import java.util.ResourceBundle;

public class OptionsController extends ErrorBaseController {

    private Scene scene;

    private AppService appService;

    private EmailService emailService;

    @FXML
    private Rectangle warningBackground;

    @FXML
    private CheckBox devMode;

    @FXML
    private TextField credentialEntry;

    @FXML
    private CheckBox login;

    private static String TWOFA_CODE;

    private static String USER_EMAIL;

    public OptionsController(Scene scene,
                             AppService appService) {
        this.scene = scene;
        this.appService = appService;
        try {
            this.emailService = new EmailService(GoogleNetHttpTransport.newTrustedTransport());
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initController();
        if (this.appService.getDevMode()) {
            this.devMode.setSelected(true);
        }
        if (this.appService.getLoggedIn()) {
            this.login.setSelected(true);
        }
    }

    @FXML
    private void verifyCredentials() {
        if (this.appService.getDevMode()) {
            this.appService.setDevMode(false);
            return;
        }
        this.disableInteraction();
        this.warningBackground.setVisible(true);
        this.credentialEntry.setPromptText("Password");
        this.credentialEntry.setVisible(true);
        this.scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                checkPassword();
            }
        });
    }

    @FXML
    private void authenticate() {
        if (this.appService.getLoggedIn()) {
            this.appService.setLoggedInEmail(null);
            return;
        }
        this.disableInteraction();
        this.credentialEntry.setPromptText("Email");
        this.credentialEntry.setVisible(true);
        this.scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                boolean success = this.sendVerificationEmail();
                if (success) {
                    USER_EMAIL = this.getCredentialInput();
                    this.setErrorMessage(String.format(
                            "2FA email sent successfully to %s.", USER_EMAIL));
                    this.credentialEntry.clear();
                    this.credentialEntry.setPromptText("2FA Code");
                    this.scene.setOnKeyReleased(e2 -> {
                        if (e2.getCode() == KeyCode.ENTER) {
                            this.check2FACode();
                        }
                    });
                } else {
                    this.setErrorMessage(
                            "Error sending email, make sure it was entered correctly.");
                }
            }
        });
    }

    private boolean sendVerificationEmail() {
        boolean success = false;
        try {
            success = this.emailService.sendEmail(
                    this.getCredentialInput(), this.generate2FACode());
        } catch (MessagingException | IOException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    private String generate2FACode() {
        TWOFA_CODE = String.format("%06d", new Random().nextInt(999999));
        return TWOFA_CODE;
    }

    private String getCredentialInput() {
        return this.credentialEntry.getText().strip();
    }

    private void acceptCredentials() {
        this.devMode.setOpacity(1.0);
        this.login.setOpacity(1.0);
        this.devMode.setDisable(false);
        this.login.setDisable(false);
        this.warningBackground.setVisible(false);
        this.credentialEntry.setVisible(false);
        this.credentialEntry.clear();
        this.scene.setOnKeyReleased(e -> { });
        this.hideErrorMessage();
    }

    private void disableInteraction() {
        this.devMode.setOpacity(0.2);
        this.login.setOpacity(0.2);
        this.devMode.setDisable(true);
        this.login.setDisable(true);
    }

    private void check2FACode() {
        if (this.getCredentialInput().equals(TWOFA_CODE)) {
            this.appService.setLoggedInEmail(USER_EMAIL);
            this.acceptCredentials();
        } else {
            this.setErrorMessage("Invalid 2FA code, retry from main menu to resend.");
        }
    }

    private void checkPassword() {
        if (this.getCredentialInput().equals(System.getenv("DEVMODE_PW"))) {
            this.appService.setDevMode(true);
            this.acceptCredentials();
        } else {
            this.setErrorMessage("Invalid password, this has been reported.");
        }
    }

    @FXML
    private void switchToPrimary() {
        this.scene.setOnKeyReleased(e -> { });
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        this.appService.setRoot(loader);
    }
}
