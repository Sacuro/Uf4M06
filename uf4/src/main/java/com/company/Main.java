package com.company;

import com.company.dao.Database;
import com.company.dao.DatabaseMongo;
import com.company.dao.DatabaseMysql;
import com.company.model.Actor;
import com.company.model.Movie;
import com.company.model.Relation;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main implements Database {
    static Database db = null;
    static Scanner sc = new Scanner(System.in);
    static int action = 1;

    @Override
    public void insertFilm(String title, String synopsis) {
        db.insertFilm(title, synopsis);
    }

    @Override
    public Movie queryFilm(String title) {
        return db.queryFilm(title);
    }

    @Override
    public Stream<Movie> queryAllFilms() {
        return db.queryAllFilms();
    }

    @Override
    public void deleteFilm(String title) {
        db.deleteFilm(title);
    }

    @Override
    public void updateFilm(String title, String newTitle, String synopsis) {
        db.updateFilm(title, newTitle, synopsis);
    }

    @Override
    public void insertActor(String name, int age) {
        db.insertActor(name, age);
    }

    @Override
    public Actor queryActor(String name) {
        return db.queryActor(name);
    }

    @Override
    public Stream<Actor> queryAllActors() {
        return db.queryAllActors();
    }

    @Override
    public void deleteActor(String name) {
        db.deleteActor(name);
    }

    @Override
    public void updateActor(String name, String newName, int age) {
        db.updateActor(name, newName, age);
    }

    @Override
    public void insertRelation(int id_actor, int id_movie) {
        db.insertRelation(id_actor, id_movie);
    }

    @Override
    public void deleteRelation(int id_actor, int id_movie) {
        db.deleteRelation(id_actor, id_movie);
    }

    @Override
    public Relation queryRelation(int id_actor, int id_movie) {
        return db.queryRelation(id_actor, id_movie);
    }

    @Override
    public Stream<Relation> queryAllRelations() {
        return db.queryAllRelations();
    }

    public static void main(String[] args) {
        Main main = new Main();

        while (action != 0) {

            if (Menu.dbMenu() == 1) {
                db = new DatabaseMysql();
                action = Menu.actionMenu();
            } else {
                db = new DatabaseMongo();
                action = Menu.actionMenu();
            }

            switch (action) {
                case 1:
                    System.out.println("FILM NAME [enter] FILM SYNOPSIS");
                    main.insertFilm(sc.next(), sc.next());
                    break;
                case 2:
                    System.out.println("FILM NAME");
                    main.queryFilm(sc.next());
                    break;
                case 3:
                    main.queryAllFilms();
                    break;
                case 4:
                    System.out.println("FILM NAME");
                    main.deleteFilm(sc.next());
                    break;
                case 5:
                    System.out.println("FILM NAME [enter] FILM NEW NAME [enter] FILM NEW SYNOPSIS");
                    main.updateFilm(sc.next(), sc.next(), sc.next());
                    break;

                case 6:
                    System.out.println("ACTOR NAME [enter] ACTOR AGE");
                    main.insertActor(sc.next(), sc.nextInt());
                    break;
                case 7:
                    System.out.println("ACTOR NAME");
                    main.queryActor(sc.next());
                    break;
                case 8:
                    main.queryAllActors();
                    break;
                case 9:
                    System.out.println("ACTOR NAME");
                    main.deleteActor(sc.next());
                    break;
                case 10:
                    System.out.println("ACTOR NAME [enter] ACTOR NEW NAME [enter] ACTOR NEW AGE");
                    main.updateActor(sc.next(), sc.next(), sc.nextInt());
                    break;
                case 11:
                    System.out.println("ACTOR_ID [enter] MOVIE_ID");
                    main.insertRelation(sc.nextInt(), sc.nextInt());
                    break;
                case 12:
                    System.out.println("ACTOR_ID [enter] MOVIE_ID");
                    main.deleteRelation(sc.nextInt(), sc.nextInt());
                    break;
                case 13:
                    System.out.println("ACTOR_ID [enter] MOVIE_ID");
                    main.queryRelation(sc.nextInt(), sc.nextInt()).toStringSql();
                    break;
                case 14:
                    main.queryAllRelations();
                    break;

                case 0:
                    System.out.println("exit");
                    break;
            }
        }

    }


}
