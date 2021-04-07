package org.example.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.example.dto.PlayerState;
import org.example.repository.SaveRepository;

import java.util.List;

public class SaveService {

    private MongoClient mongoClient;

    private MongoDatabase mongoDatabase;

    private SaveRepository saveRepository;

    private static final String EMAIL_KEY = "email";

    private static final String USERNAME_KEY = "username";

    public SaveService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.mongoDatabase = this.mongoClient.getDatabase(System.getenv("ENV"));
        this.saveRepository = new SaveRepository(this.mongoDatabase);
    }

    public List<PlayerState> findPlayerStates(String email) {
        return this.saveRepository
                .findManyPlayerStateSaves(findByEmail(email));
    }

    public boolean upsertPlayerStateSave(PlayerState playerState) {
        return this.saveRepository
                .upsertPlayerStateSave(
                        playerState,
                        findByEmailAndUsername(playerState.getEmail(), playerState.getUsername()));
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
