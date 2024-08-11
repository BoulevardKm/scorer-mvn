package com.fm.recommender.impl;

import com.fm.recommender.RecommenderService;
import com.fm.recommender.Scorer;
import com.fm.recommender.db.Db;

import java.util.ArrayList;
import java.util.List;

public class RecommenderServiceImpl<Movie, User> implements RecommenderService<Movie, User> {
    private Scorer<Movie, User> scorer;
    private List<Movie> movies = new ArrayList<>(); // TODO: remove movie list init from here
    private final Db db;

    RecommenderServiceImpl(Scorer<Movie, User> scorer, Db db) {
        this.scorer = scorer;
        this.db = db;
    }

    @Override
    public List<Movie> getTop(User user, int limit) {
        // TODO: lazy initialization for movie list

        List<Movie> allMovies = movies;
        allMovies.sort((m1, m2) -> Double.compare(scorer.getScore(m2, user), scorer.getScore(m1, user)));
        return allMovies.subList(0, Math.min(limit, allMovies.size()));
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
        // TODO: save movie in db
    }
}
