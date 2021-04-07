package org.example.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.dto.PlayerState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class SaveRepository {

    private MongoDatabase database;

    private MongoCollection<PlayerState> mongoCollection;

    public static final String SAVE_COLLECTION = "saves";

    private static final FindOneAndReplaceOptions UPSERT = new FindOneAndReplaceOptions()
            .upsert(true);

    public SaveRepository(MongoDatabase database) {
        this.database = database;
        this.mongoCollection = this.database.getCollection(SAVE_COLLECTION, PlayerState.class);
    }

    public boolean upsertPlayerStateSave(PlayerState playerState, Bson query) {
        return this.mongoCollection
                .findOneAndReplace(
                        query,
                        playerState,
                        UPSERT) != null;
    }

    public PlayerState findOnePlayerStateSave(Bson query) {
        return this.mongoCollection
                .find(query).first();
    }

    public List<PlayerState> findManyPlayerStateSaves(Bson query) {
        List<PlayerState> playerStates = new ArrayList<>();
        for (PlayerState playerState : this.mongoCollection.find(query)) {
            playerStates.add(playerState);
        }
        return playerStates;
    }

    public boolean removePlayerStateSave(Bson query) {
        return this.mongoCollection
                .deleteOne(query).wasAcknowledged();
    }
}
