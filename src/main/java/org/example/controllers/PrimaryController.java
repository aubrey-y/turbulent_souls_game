package org.example.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.App;
import org.example.dto.Coordinate;
import org.example.dto.Item;
import org.example.dto.PlayerState;
import org.example.dto.Weapon;
import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.enums.Direction;
import org.example.services.AppService;
import org.example.services.SaveService;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class PrimaryController extends BaseController implements Initializable {

    @FXML
    private Button primaryButton;

    @FXML
    private Label startLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Button soundToggle;

    private SaveService saveService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initController();
        //Mongo Setup
        ConnectionString connectionString = new ConnectionString(
                System.getenv("MONGO_URI"));
        CodecRegistry pojoCodecRegistry = fromProviders(
                PojoCodecProvider.builder()
                        .automatic(true)
                        .build());
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry).build();
        MongoClient mongoClient = MongoClients.create(clientSettings);
        this.saveService = new SaveService(mongoClient);
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToSecondary() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        loader.setControllerFactory(SecondaryController -> new SecondaryController(
                this.saveService,
                this.appService.getScene()));
        this.appService.setRoot(loader);
    }

    @FXML
    private void switchToOptions() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("options.fxml"));
        loader.setControllerFactory(OptionsController -> new OptionsController(
                this.appService.getScene(),
                this.appService
        ));
        this.appService.setRoot(loader);
    }

    @FXML
    private void switchToLoadGame() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loadGame.fxml"));
        loader.setControllerFactory(OptionsController -> new LoadGameController(
                this.appService,
                this.saveService,
                this.appService.getScene()
        ));
        this.appService.setRoot(loader);
    }

    public AppService getAppService() {
        return appService;
    }

    public PrimaryController setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }
}
