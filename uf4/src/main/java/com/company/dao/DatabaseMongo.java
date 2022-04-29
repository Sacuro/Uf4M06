package com.company.dao;


import com.company.model.Actor;
import com.company.model.Movie;
import com.company.model.Relation;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseMongo implements Database {

    String uri = "mongodb://localhost";
    MongoDatabase database;




    public Movie queryFilm(String title) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");
            for (Document d : collection.find(eq("title", title))) {
                Movie movie = new Movie(d.getObjectId("_id"), d.getString("title"), d.getString("synopsis"));
                System.out.println(movie.toStringMongo());
                return movie;
            }
        }
        return null;
    }

    @Override
    public Stream<Movie> queryAllFilms() {
        List<Movie> movies = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");
            for (Document d : collection.find()) {
                Movie movie = new Movie(d.getObjectId("_id"), d.getString("title"), d.getString("synopsis"));
                movies.add(movie);
                System.out.println(movie.toStringMongo());
            }
        }
        return movies.stream();
    }
    public void insertFilm(String title, String synopsis) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");
            Document doc = new Document();
            doc.append("title", title);
            doc.append("synopsis", synopsis);
            collection.insertOne(doc);
        }
    }
    @Override
    public void deleteFilm(String title) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");
            Document doc = new Document();
            doc.append("title", title);
            collection.deleteOne(doc);
        }
    }

    @Override
    public void updateFilm(String title, String newTitle, String synopsis) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");
            Document doc = new Document().append("title", title);
            Bson updates = Updates.combine(
                    Updates.set("title", newTitle),
                    Updates.set("synopsis", synopsis)
            );
            UpdateOptions options = new UpdateOptions().upsert(true);
            try {
                UpdateResult result = collection.updateOne(doc, updates, options);
            } catch (MongoException e) {
                System.out.println("Unable to update due to an error: " + e);
            }
        }
    }

    @Override
    public void insertActor(String name, int age) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actors");
            Document doc = new Document();
            doc.append("name", name);
            doc.append("age", age);
            collection.insertOne(doc);
        }
    }

    @Override
    public Actor queryActor(String name) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actors");
            for (Document d : collection.find(eq("name", name))) {
                Actor actor = new Actor(d.getObjectId("_id"), d.getString("name"), d.getInteger("age"));
                System.out.println(actor.toStringMongo());
                return actor;

            }
        }
        return null;
    }

    @Override
    public Stream<Actor> queryAllActors() {
        List<Actor> actors = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actors");
            for (Document d : collection.find()) {
                Actor actor = new Actor(d.getObjectId("_id"), d.getString("name"), d.getInteger("age"));
                actors.add(actor);
                System.out.println(actor.toStringMongo());
            }
        }
        return actors.stream();
    }

    @Override
    public void deleteActor(String name) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actors");
            Document doc = new Document();
            doc.append("name", name);
            collection.deleteOne(doc);
        }
    }

    @Override
    public void updateActor(String name, String newName, int age) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actors");
            Document doc = new Document().append("name", name);
            Bson updates = Updates.combine(
                    Updates.set("name", newName),
                    Updates.set("age", age)
            );
            UpdateOptions options = new UpdateOptions().upsert(true);
            try {
                UpdateResult result = collection.updateOne(doc, updates, options);
            } catch (MongoException e) {
                System.out.println("Unable to update due to an error: " + e );
            }
        }
    }

    @Override
    public void insertRelation(int id_actor, int id_movie) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actorsMovies");
            Document doc = new Document();
            doc.append("id_actor", id_actor);
            doc.append("id_movie", id_movie);
            collection.insertOne(doc);
        }
    }

    @Override
    public void deleteRelation(int id_actor, int id_movie) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actorsMovies");
            Document doc = new Document();
            doc.append("id_actor", id_actor);
            doc.append("id_movie", id_movie);
            collection.deleteOne(doc);
        }
    }

    @Override
    public Relation queryRelation(int id_actor, int id_movie) {
        //TODO
        List<Relation> relations = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actorsMovies");
            for (Document d : collection.find()) {
                Relation relation = new Relation(d.getString("id"), d.getString("_id_movie"), d.getString("timeStamp"));
                relations.add(relation);
                System.out.println(relation.toStringMongo());
            }
        }
        return relations.get(0);
    }

    @Override
    public Stream<Relation> queryAllRelations() {
        List<Relation> relations = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("actorsMovies");
            for (Document d : collection.find()) {
                Relation relation = new Relation(d.getString("id"), d.getString("_id_movie"), d.getString("timeStamp"));
                relations.add(relation);
                System.out.println(relation.toStringMongo());
            }
        }
        return relations.stream();
    }
}