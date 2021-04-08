package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.dto.PlayerState;

import java.util.ArrayList;
import java.util.List;

public class SaveRepository {

    private MongoDatabase database;

    private MongoCollection<Document> mongoCollection;

    public static final String SAVE_COLLECTION = "saves";

    private static final FindOneAndReplaceOptions UPSERT = new FindOneAndReplaceOptions()
            .upsert(true).returnDocument(ReturnDocument.AFTER);

    public SaveRepository(MongoDatabase database) {
        this.database = database;
        this.mongoCollection = this.database.getCollection(SAVE_COLLECTION);
    }

    public boolean upsertPlayerStateSave(Document playerState, Bson query) {
        return this.mongoCollection
                .findOneAndReplace(
                        query,
                        playerState,
                        UPSERT) != null;
    }

    public Document findOnePlayerStateSave(Bson query) {
        return this.mongoCollection
                .find(query).first();
    }

    public List<Document> findManyPlayerStateSaves(Bson query) {
        List<Document> playerStates = new ArrayList<>();
        for (Document playerState : this.mongoCollection.find(query)) {
            playerStates.add(playerState);
        }
        return playerStates;
    }

    public boolean removePlayerStateSave(Bson query) {
        return this.mongoCollection
                .deleteOne(query).wasAcknowledged();
    }
}
