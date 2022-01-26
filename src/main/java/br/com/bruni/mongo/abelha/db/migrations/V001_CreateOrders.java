package br.com.bruni.mongo.abelha.db.migrations;

import com.mongodb.client.MongoCollection;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;
import static java.util.Arrays.asList;
import static org.bson.Document.parse;

@ChangeUnit(order = "001", id = "INSERT-ORDERS", author = "Bruno Ventura")
public class V001_CreateOrders {

    private final MongoTemplate mongoTemplate;

    public V001_CreateOrders(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void changeSet() throws Exception {
        MongoCollection<Document> order = mongoTemplate.getCollection("order");
        order.insertMany(asList(
                parse("{ \"id\": \"1\", \"status\": \"NEW\", \"productName\": \"PS4\", \"value\": 123.78 }"),
                parse("{ \"id\": \"2\", \"status\": \"NEW\", \"productName\": \"XBOX\", \"value\": 123.78 }")
        ));
    }

    @RollbackExecution
    public void rollback() {
        MongoCollection<Document> order = mongoTemplate.getCollection("order");
        order.deleteMany(or(
                eq("id", "1"),
                eq("id", "2")
        ));
    }

}
