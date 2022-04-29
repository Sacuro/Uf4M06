package com.company.dao;

import com.company.model.Actor;
import com.company.model.Movie;
import com.company.model.Relation;

import java.util.stream.Stream;

public interface Database {
    // Film Methods
    void insertFilm(String title, String synopsis);
    Movie queryFilm(String title);
    Stream<Movie> queryAllFilms();
    void deleteFilm(String title);
    void updateFilm(String title, String newTitle, String synopsis);

    // Actor Methods
    void insertActor(String name, int age);
    Actor queryActor(String name);
    Stream<Actor> queryAllActors();
    void deleteActor(String name);
    void updateActor(String name, String newName, int age);

    // Relation Methods
    void insertRelation(int id_actor, int id_movie);
    void deleteRelation(int id_actor, int id_movie);
    Relation queryRelation(int id_actor, int id_movie);
    Stream<Relation> queryAllRelations();
}
