package com.company.dao;

import com.company.model.Actor;
import com.company.model.Movie;
import com.company.model.Relation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DatabaseMysql implements Database{

    String uri ="jdbc:mysql://localhost/mydatabase?user=myuser&password=mypass";


    @Override
    public void insertFilm(String title, String synopsis) {
        try(Connection conn = DriverManager.getConnection(uri)){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO movies(title, synopsis) VALUES(?, ?)");
            statement.setString(1, title);
            statement.setString(2, synopsis);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Movie> queryAllFilms() {
        List<Movie> movies = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(uri)){
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM movies");
            while (resultSet.next()) {
                Movie movie = new Movie(resultSet.getString("id"), resultSet.getString("title"), resultSet.getString("synopsis"));
                movies.add(movie);
                System.out.println(movie.toStringSql());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies.stream();
    }

    @Override
    public Movie queryFilm(String title) {
        Movie movie = null;
        try(Connection conn = DriverManager.getConnection(uri)){
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM movies WHERE title = '" + title + "'");
            while (resultSet.next()) {
                movie = new Movie(resultSet.getString("id"), resultSet.getString("title"), resultSet.getString("synopsis"));
                System.out.println(movie.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public void deleteFilm(String title) {
        try(Connection conn = DriverManager.getConnection(uri)){
            PreparedStatement statement = conn.prepareStatement("DELETE FROM movies WHERE title = (?)");
            statement.setString(1, title);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFilm(String title, String newTitle, String synopsis) {
        try(Connection conn = DriverManager.getConnection(uri)){
            PreparedStatement statement = conn.prepareStatement("UPDATE movies SET title = (?), synopsis = (?) WHERE title = (?)");
            statement.setString(1, newTitle);
            statement.setString(2, synopsis);
            statement.setString(3, title);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertActor(String name, int age) {
        try(Connection conn = DriverManager.getConnection(uri)){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO actors(name, age) VALUES(?, ?)");
            statement.setString(1, name);
            statement.setString(2, String.valueOf(age));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Actor queryActor(String name) {
        Actor actor = null;
        try(Connection conn = DriverManager.getConnection(uri)){
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM actors WHERE name = '" + name + "'");
            while (resultSet.next()) {
                actor = new Actor(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("age"));
                System.out.println(actor.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;
    }

    @Override
    public Stream<Actor> queryAllActors() {
        List<Actor> actors = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(uri)){
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM actors");
            while (resultSet.next()) {
                Actor actor = new Actor(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("age"));
                actors.add(actor);
                System.out.println(actor.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors.stream();
    }

    @Override
    public void deleteActor(String name) {
        try(Connection conn = DriverManager.getConnection(uri)){
            PreparedStatement statement = conn.prepareStatement("DELETE FROM actors WHERE name = (?)");
            statement.setString(1, name);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateActor(String name, String newName, int age) {
        try(Connection conn = DriverManager.getConnection(uri)){
            PreparedStatement statement = conn.prepareStatement("UPDATE actors SET name = (?), age = (?) WHERE name = (?)");
            statement.setString(1, newName);
            statement.setString(1, String.valueOf(age));
            statement.setString(1, name);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertRelation(int id_actor, int id_movie) {
        try(Connection conn = DriverManager.getConnection(uri)){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO actorsMovies(id_actor, id_movie) VALUES(?, ?)");
            statement.setString(1, String.valueOf(id_actor));
            statement.setString(2, String.valueOf(id_movie));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRelation(int id_actor, int id_movie) {
        try(Connection conn = DriverManager.getConnection(uri)){
            PreparedStatement statement = conn.prepareStatement("DELETE FROM actorsMovies WHERE id_actor = (?) AND id_movie = (?)");
            statement.setString(1, String.valueOf(id_actor));
            statement.setString(2, String.valueOf(id_movie));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Relation queryRelation(int id_actor, int id_movie) {
        Relation relation = null;
        try(Connection conn = DriverManager.getConnection(uri)){
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM actorsMovies WHERE id_actor = '" + id_actor + "' AND id_movie = '" + id_movie + "'");
            while (resultSet.next()) {
                relation = new Relation(resultSet.getString("id_actor"), resultSet.getString("id_movie"), resultSet.getString("insertDate"));
                System.out.println(relation.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relation;
    }

    @Override
    public Stream<Relation> queryAllRelations() {
        List<Relation> relations = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(uri)){
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM actorsMovies");
            while (resultSet.next()) {
                Relation relation = new Relation(resultSet.getString("id_actor"), resultSet.getString("id_movie"), resultSet.getString("insertDate"));
                relations.add(relation);
                System.out.println(relation.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relations.stream();
    }
}
