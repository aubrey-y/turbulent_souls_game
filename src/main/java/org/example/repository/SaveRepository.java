package org.example.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class SaveRepository {

    private DB database;

    public static final String SAVE_COLLECTION = "saves";

    public SaveRepository(DB database) {
        this.database = database;
    }

    public boolean upsertPlayerStateSave(BasicDBObject playerState) {
        DBCollection collection = this.database.getCollection(SAVE_COLLECTION);
        return collection.insert(playerState).wasAcknowledged();
    }

    public boolean removePlayerStateSave(BasicDBObject query) {
        DBCollection collection = this.database.getCollection(SAVE_COLLECTION);
        return collection.remove(query).wasAcknowledged();
    }
}
