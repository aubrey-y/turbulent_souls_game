package org.example.services;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import static org.example.dto.HealthBarConstants.GREEN_HP_BAR;
import static org.example.dto.HealthBarConstants.RED_HP_BAR;
import static org.example.dto.HealthBarConstants.HP_BAR_THRESHOLD;
import static org.example.dto.HealthBarConstants.BAR_CSS_CLASSES;
import org.example.dto.PlayerState;

public class HealthService {

    private AppService appService;

    private ProgressBar healthBar;

    private Label healthText;

    public HealthService(AppService appService) {
        this.appService = appService;
    }

    public boolean applyHealthModifier(double modifier) {
        PlayerState currentPlayerState = this.appService.getPlayerState();
        double currentHealth = currentPlayerState.getHealth();
        currentHealth += modifier;
        currentHealth = Math.min(
                Math.max(0.0, currentHealth), currentPlayerState.getHealthCapacity());
        currentPlayerState.setHealth(currentHealth);
        this.healthBar.getStyleClass().removeAll(BAR_CSS_CLASSES);
        if (currentHealth <= HP_BAR_THRESHOLD * currentPlayerState.getHealthCapacity()) {
            this.healthBar.getStyleClass().add(RED_HP_BAR);
        } else {
            this.healthBar.getStyleClass().add(GREEN_HP_BAR);
        }
        this.healthText.setText(
                (int) currentHealth + "/" + (int) currentPlayerState.getHealthCapacity());
        this.healthBar.setProgress(currentHealth / currentPlayerState.getHealthCapacity());
        this.appService.setPlayerState(currentPlayerState);
        return currentHealth != 0.0;
    }

    public AppService getAppService() {
        return appService;
    }

    public HealthService setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }

    public ProgressBar getHealthBar() {
        return healthBar;
    }

    public HealthService setHealthBar(ProgressBar healthBar) {
        this.healthBar = healthBar;
        return this;
    }

    public Label getHealthText() {
        return healthText;
    }

    public HealthService setHealthText(Label healthText) {
        this.healthText = healthText;
        return this;
    }
}
