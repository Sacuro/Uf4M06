package com.example.uf4;

import com.company.dao.Database;
import com.company.dao.DatabaseMongo;
import com.company.dao.DatabaseMysql;
import com.company.model.Actor;
import com.company.model.Movie;
import com.company.model.Relation;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActorsController implements Database {
    static Database db = null;
    static int action = 1;

    @FXML
    private TableView<Actor> tableActor = new TableView<>();
    @FXML
    private TableColumn<Actor, Object> colIDActor = new TableColumn<>("ID");
    @FXML
    private TableColumn<Actor, String> colNombreActor = new TableColumn<>("Nombre");
    @FXML
    private TableColumn<Actor, String> colEdadActor = new TableColumn<>("Edad");
    @FXML
    private TableView<Movie> tableMovie = new TableView<>();
    @FXML
    private TableColumn<Movie, String> colIDMovie = new TableColumn<>("ID");
    @FXML
    private TableColumn<Movie, String> colTitleMovie = new TableColumn<>("Titulo");
    @FXML
    private TableColumn<Movie, String> colSinopsisMovie = new TableColumn<>("Sinopsis");
    @FXML
    private Button gobackfilm;
    @FXML
    private Button gobackactor;
    @FXML
    private RadioButton sql;
    @FXML
    private RadioButton mongo;
    @FXML
    private Button btnewactor;
    @FXML
    private Button btnewfilm;
    @FXML
    private Button btmovies;
    @FXML
    private Button btactors;
    @FXML
    private Button btndeleteactor;
    @FXML
    private TextField titletextpeli;
    @FXML
    private TextField descriptextpeli;
    @FXML
    private TextField acot;
    @FXML
    private TextField pelis;
    @FXML
    private TextField titletextactor;
    @FXML
    private TextField text;
    @FXML
    private TextField inputNomActorEliminar;
    @FXML
    private TextField inputNomPeliEliminar;
    @FXML
    private TextField deleteRelationIdActor;
    @FXML
    private TextField deleteRelationIdFilm;


    @FXML
    protected void buscarActor(){
        if (sql.isSelected()){
            db = new DatabaseMysql();
            colIDActor.setCellValueFactory(new PropertyValueFactory<>("id_sql"));
        }else{
            db = new DatabaseMongo();
            colIDActor.setCellValueFactory(new PropertyValueFactory<>("id_mongo"));
        }
        colNombreActor.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEdadActor.setCellValueFactory(new PropertyValueFactory<>("age"));
        tableActor.getColumns().clear();
        tableActor.getColumns().addAll(colIDActor, colNombreActor, colEdadActor);
        tableActor.getItems().clear();
        if (acot.getText().isBlank()){
            Stream<Actor> a = db.queryAllActors();
            tableActor.getItems().addAll(a.toList());

        }else{
            Actor actor = db.queryActor(acot.getText());
            tableActor.getItems().add(actor);
        }
    }

    @FXML
    protected void buscarPeli(){
        if (sql.isSelected()){
            db = new DatabaseMysql();
            colIDMovie.setCellValueFactory(new PropertyValueFactory<>("id_sql"));
        }else{
            db = new DatabaseMongo();
            colIDMovie.setCellValueFactory(new PropertyValueFactory<>("id_mongo"));
        }
        colTitleMovie.setCellValueFactory(new PropertyValueFactory<>("title"));
        colSinopsisMovie.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        tableMovie.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableMovie.getColumns().clear();
        tableMovie.getColumns().addAll(colIDMovie, colTitleMovie, colSinopsisMovie);
        tableMovie.getItems().clear();
        if (pelis.getText().isBlank()){
            Stream<Movie> a = db.queryAllFilms();
            tableMovie.getItems().addAll(a.toList());
        }else{
            Movie movie = db.queryFilm(pelis.getText());
            tableMovie.getItems().add(movie);
        }

    }
    @FXML
    protected void btcancelarautorclick(){
        Platform.runLater(() -> {
            titletextactor.clear();
            text.clear();
        });
    }

    @FXML
    protected void btnaddactorclick(){
        String nom = titletextactor.getText();
        int edat = Integer.parseInt(text.getText());
        if (Objects.isNull(db.queryActor(nom)) || !db.queryActor(nom).name.equals(nom)){
            insertActor(nom, edat);
        }else{
            updateActor(nom, nom, edat);
        }

    }

    @FXML
    protected void btncancelardeleteautorclick(){
        Platform.runLater(() ->{
            inputNomActorEliminar.clear();
        });
    }

    @FXML
    protected void btndeleteactorclick(){
        String nom = inputNomActorEliminar.getText();
        deleteActor(nom);
    }

    @FXML
    protected void btndeletefilmclick(){
        String nom = inputNomPeliEliminar.getText();
        deleteActor(nom);
    }

    @FXML
    protected void btncancelardeletepeliclick(){
        Platform.runLater(() ->{
            inputNomPeliEliminar.clear();
        });
    }

    @FXML
    protected void btcancelarpeliclick(){
        Platform.runLater(() -> {
            titletextpeli.clear();
            descriptextpeli.clear();
        });
    }

    @FXML
    protected void btcancelardeleterelationclick(){
        Platform.runLater(() -> {
            deleteRelationIdFilm.clear();
            deleteRelationIdActor.clear();
        });
    }

    @FXML
    protected void btdeleterelationclick(){
        int filmid = Integer.parseInt(deleteRelationIdFilm.getText());
        int actorid = Integer.parseInt(deleteRelationIdActor.getText());
        deleteRelation(actorid, filmid);
    }

    @FXML
    protected void btafegirpeliclick(){
        String title = titletextpeli.getText();
        String descrip = descriptextpeli.getText();

        if (Objects.isNull(db.queryFilm(title)) || !db.queryFilm(title).title.equals(title)){
            insertFilm(title, descrip);
        }else{
            updateFilm(title, title, descrip);
        }
    }

    @FXML
    protected void checkNumbers() throws IllegalArgumentException{
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    //text.setText(newValue.replaceAll("[^\\d]", ""));
                    Platform.runLater(() -> {
                        text.clear();
                    });
                }
            }
        });
    }
    @FXML
    protected void onbackactorbuttonclick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("actors.fxml"));
            Stage stage =(Stage) gobackactor.getScene().getWindow();
            stage.setTitle("Actors");
            Scene scene = new Scene(loader.load(), 640, 480);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onbackfilmbuttonclick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Stage stage =(Stage) gobackfilm.getScene().getWindow();
            stage.setTitle("Pantalla principal");
            Scene scene = new Scene(loader.load(), 640, 480);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onnewactorsbuttonclick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_actor.fxml"));
            Stage stage =(Stage) btnewactor.getScene().getWindow();
            stage.setTitle("Alta actor");
            Scene scene = new Scene(loader.load(), 640, 480);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onnewfilmbuttonclick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_film.fxml"));
            Stage stage =(Stage) btnewfilm.getScene().getWindow();
            stage.setTitle("Alta pelicula");
            Scene scene = new Scene(loader.load(), 640, 480);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void ondeleteactor(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteActorView.fxml"));
            Parent root = loader.load();
            Stage stage =new Stage();
            stage.setScene(new Scene(root, 300, 150));
            stage.setTitle("Delete Actor");
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void ondeletemovie(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteFilmView.fxml"));
            Parent root = loader.load();
            Stage stage =new Stage();
            stage.setScene(new Scene(root, 300, 150));
            stage.setTitle("Delete Movie");
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onactorsbuttonclick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("actors.fxml"));
            Stage stage =(Stage) btactors.getScene().getWindow();
            stage.setTitle("Actors");
            Scene scene = new Scene(loader.load(), 640, 480);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onmoviesbuttonclick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Stage stage =(Stage) btmovies.getScene().getWindow();
            stage.setTitle("Pantalla principal");
            Scene scene = new Scene(loader.load(), 640, 480);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onsqlbuttonclick(){
        mongo.setSelected(false);
    }
    @FXML
    protected void onmongobuttonclick(){
        sql.setSelected(false);
    }
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
}