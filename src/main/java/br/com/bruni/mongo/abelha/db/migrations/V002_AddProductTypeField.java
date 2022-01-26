package br.com.bruni.mongo.abelha.db.migrations;

import com.mongodb.client.MongoCollection;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;

@ChangeUnit(order = "002", id = "ADD-PRODUCT-TYPE-FIELD", author = "Bruno Ventura")
public class V002_AddProductTypeField {

    private final MongoTemplate mongoTemplate;

    public V002_AddProductTypeField(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void changeSet() throws Exception {
        MongoCollection<Document> order = mongoTemplate.getCollection("order");
        order.updateMany(eq("status", "NEW"), set("productType", "UNKNOWN"));
    }

    @RollbackExecution
    public void rollback() {
        MongoCollection<Document> order = mongoTemplate.getCollection("order");
        order.updateMany(new BsonDocument(), unset("productType"));
    }

}
