package org.example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.dto.PlayerState;
import org.example.repository.SaveRepository;
import org.example.util.FieldComplianceUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.util.PlayerStateByLastUpdatedComparator;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class SaveService {

    private MongoClient mongoClient;

    private MongoDatabase mongoDatabase;

    private SaveRepository saveRepository;

    private static final String EMAIL_KEY = "email";

    private static final String USERNAME_KEY = "username";

    private ObjectMapper objectMapper;

    public SaveService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.mongoDatabase = this.mongoClient.getDatabase(System.getenv("ENV"));
        this.saveRepository = new SaveRepository(this.mongoDatabase);
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<PlayerState> findPlayerStates(String email) {
        List<Document> playerStateDocuments = this.saveRepository
                .findManyPlayerStateSaves(findByEmail(email));
        List<PlayerState> playerStates = new ArrayList<>();
        for (Document playerStateDocument : playerStateDocuments) {
            playerStates.add(
                    this.restorePlayerState(
                            this.convertJsonToPlayerState(playerStateDocument.toJson())));
        }
        playerStates.sort(new PlayerStateByLastUpdatedComparator());
        return playerStates;
    }

    public boolean upsertPlayerStateSave(PlayerState playerState) {
        PlayerState cleansedPlayerState = this.cleansePlayerState(new PlayerState(playerState));
        String json = this.convertPlayerStateToJson(cleansedPlayerState);
        return this.saveRepository
                .upsertPlayerStateSave(
                        Document.parse(json),
                        findByEmailAndUsername(
                                cleansedPlayerState.getEmail(),
                                cleansedPlayerState.getUsername()));
    }

    public boolean removePlayerStateSave(String email, String username) {
        return this.saveRepository
                .removePlayerStateSave(findByEmailAndUsername(email, username));
    }


    private static Bson findByEmailAndUsername(String email, String username) {
        return Filters.and(
                Filters.eq(EMAIL_KEY, email), Filters.eq(USERNAME_KEY, username));
    }

    private static Bson findByEmail(String email) {
        return Filters.eq(EMAIL_KEY, email);
    }

    private PlayerState cleansePlayerState(PlayerState playerState) {
        FieldComplianceUtility.cleanseIllegalCharacters(playerState.getWeaponInventory());
        FieldComplianceUtility.cleanseIllegalCharacters(playerState.getGeneralInventory());
        return playerState;
    }

    private PlayerState restorePlayerState(PlayerState playerState) {
        FieldComplianceUtility.restoreIllegalCharacters(playerState.getWeaponInventory());
        FieldComplianceUtility.restoreIllegalCharacters(playerState.getGeneralInventory());
        return playerState;
    }

    private String convertPlayerStateToJson(PlayerState playerState) {
        String json;
        try {
            json = this.objectMapper.writeValueAsString(playerState);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private PlayerState convertJsonToPlayerState(String json) {
        PlayerState playerState;
        try {
            playerState = this.objectMapper.readValue(json, PlayerState.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return playerState;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public SaveService setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        return this;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public SaveService setMongoDatabase(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
        return this;
    }

    public SaveRepository getSaveRepository() {
        return saveRepository;
    }

    public SaveService setSaveRepository(SaveRepository saveRepository) {
        this.saveRepository = saveRepository;
        return this;
    }
}
