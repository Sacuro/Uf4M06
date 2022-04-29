CREATE TABLE movies (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     title CHAR(255) NOT NULL,
     synopsis TEXT,
     PRIMARY KEY (id)
);

CREATE TABLE actors (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     name CHAR(255) NOT NULL,
     age int,
     PRIMARY KEY (id)
);

CREATE TABLE actorsMovies (
    id_actor MEDIUMINT NOT NULL,
    id_movie MEDIUMINT NOT NULL,
    FOREIGN KEY (id_actor) REFERENCES actors(id),
    FOREIGN KEY (id_movie) REFERENCES movies(id),
    PRIMARY KEY (id_movie, id_actor),
    insertDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);