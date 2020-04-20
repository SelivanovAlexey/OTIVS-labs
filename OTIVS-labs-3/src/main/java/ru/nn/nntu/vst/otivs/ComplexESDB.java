package ru.nn.nntu.vst.otivs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import ru.nn.nntu.vst.otivs.lab2.ComplexES;
import ru.nn.nntu.vst.otivs.lab2.KnowledgeBase;
import ru.nn.nntu.vst.otivs.model.QuestionEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class ComplexESDB {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MongoClient mongo = initMongoDBSession();

        ComplexES.startInteract(new KnowledgeBase(readValuesFromDB(mapper, mongo)));
    }

    private static AggregateIterable makeAggregationQuery(MongoCollection collection) {
        return collection.aggregate(
                Arrays.asList(
                        Aggregates.lookup("constants", "question", "_id", "question"),
                        Aggregates.unwind("$question"),
                        Aggregates.lookup("constants", "negative", "_id", "negative"),
                        Aggregates.unwind("$negative"),
                        Aggregates.lookup("constants", "positive", "_id", "positive"),
                        Aggregates.unwind("$positive")
                ));
    }

    private static MongoClient initMongoDBSession() {
        return MongoClients.create(
                "mongodb+srv://alexey:mongoognom@cluster0-mc7ye.mongodb.net/test?retryWrites=true&w=majority");
    }

    private static LinkedList<QuestionEntity> readValuesFromDB(ObjectMapper mapper, MongoClient client) throws IOException {
        LinkedList<QuestionEntity> questionsList = new LinkedList<>();
        MongoDatabase db = client.getDatabase("Questions");
        MongoCollection collection = db.getCollection("knowledge_base");

        AggregateIterable result = makeAggregationQuery(collection);

        for (Document document : (Iterable<Document>) result) {
            JsonNode node = mapper.readValue(document.toJson(), JsonNode.class);
            String id = node.get("question").get("_id").asText();
            if (id.equals("S1")) questionsList.addFirst(
                    new QuestionEntity(
                            node.get("question").get("textValue").asText(),
                            node.get("positive").get("textValue").asText(),
                            node.get("negative").get("textValue").asText()
                    ));
            else questionsList.add(
                    new QuestionEntity(
                            node.get("question").get("textValue").asText(),
                            node.get("positive").get("textValue").asText(),
                            node.get("negative").get("textValue").asText()
                    ));
        }
        return questionsList;
    }
}
