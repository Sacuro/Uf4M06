package com.company.model;

import org.bson.types.ObjectId;

public class Movie {
    public String id_sql;
    public ObjectId id_mongo;
    public String title;
    public String synopsis;

    // MySql Constructor
    public Movie(String id_sql, String title, String synopsis) {
        this.id_sql = id_sql;
        this.title = title;
        this.synopsis = synopsis;
    }

    // Mongo Constructor
    public Movie(ObjectId id_mongo, String title, String synopsis) {
        this.id_mongo = id_mongo;
        this.title = title;
        this.synopsis = synopsis;
    }

    // MySql ToString
    public String toStringSql() {
        return "Movie{" +
                "id_sql='" + id_sql + '\'' +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }

    // Mongo ToString
    public String toStringMongo() {
        return "Movie{" +
                ", id_mongo=" + id_mongo +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }

    public String getId_sql() {
        return id_sql;
    }

    public ObjectId getId_mongo() {
        return id_mongo;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }
}
