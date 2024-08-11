package com.fm.recommender.db;

import com.fm.recommender.impl.Movie;

import java.util.List;

public interface Db {
    List<Movie> getAllMovies();
    void saveMovie(Movie movie);
}
