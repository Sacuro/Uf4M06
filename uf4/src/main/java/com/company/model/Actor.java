package com.company.model;

import org.bson.types.ObjectId;

public class Actor {
    public String id_sql;
    public ObjectId id_mongo;
    public String name;
    public int age;

    // MySql Constructor
    public Actor(String id_sql, String name, int age) {
        this.id_sql = id_sql;
        this.name = name;
        this.age = age;
    }

    public String getId_sql() {
        return id_sql;
    }

    public ObjectId getId_mongo() {
        return id_mongo;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Mongo Constructor
    public Actor(ObjectId id_mongo, String name, int age) {
        this.id_mongo = id_mongo;
        this.name = name;
        this.age = age;
    }

    // MySql ToString
    public String toStringSql() {
        return "Actor{" +
                "id_sql=" + id_sql +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // Mongo ToString
    public String toStringMongo() {
        return "Actor{" +
                "id_mongo=" + id_mongo +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
