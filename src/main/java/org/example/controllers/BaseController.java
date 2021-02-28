package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import org.example.services.AppService;

public abstract class BaseController implements Initializable {

    protected AppService appService;

    @FXML
    protected ImageView muteDisabled;

    @FXML
    protected ImageView muteEnabled;

    @FXML
    private void toggleSound() {
        this.appService.toggleSound(this.muteDisabled, this.muteEnabled);
    }

    protected void initController() {
        this.appService = new AppService();
        if(!this.appService.getSoundPlaying()) {
            this.muteEnabled.setOpacity(1.0);
            this.muteDisabled.setOpacity(0.0);
        }
    }
}
